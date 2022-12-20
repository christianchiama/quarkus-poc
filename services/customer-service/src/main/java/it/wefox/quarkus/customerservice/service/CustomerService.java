package it.wefox.quarkus.customerservice.service;

import io.quarkus.panache.common.Page;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.customerapi.request.CustomerRequest;
import it.wefox.quarkus.customerservice.domain.Customer;
import it.wefox.quarkus.customerservice.kafka.CustomerKafkaProducer;
import it.wefox.quarkus.customerservice.mapper.CustomerMapper;
import it.wefox.quarkus.customerservice.repository.CustomerReactiveRepository;
import it.wefox.quarkus.pagination.PageRequest;
import it.wefox.quarkus.redis.service.RedisService;
import org.bson.types.ObjectId;
import org.redisson.Redisson;
import org.redisson.api.RedissonClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 05/12/22
 * @Time: 11:28
 */
@ApplicationScoped
public class CustomerService {

    @Inject
    CustomerReactiveRepository customerReactiveRepository;

    @Inject
    CustomerMapper customerMapper;

    @Inject
    CustomerKafkaProducer customerKafkaProducer;

    @Inject
    RedisService<String,String> redisService;

    /**
     * @param customer
     * @return
     */
    public Uni<Customer> create(CustomerRequest customer) {
        Uni<Customer> customerUni = Uni.createFrom().item(customerMapper.toEntity().apply(customer));
        return customerUni
                .onItem()
                .transform(c -> {
            c.setBirthDate(customer.getBirthDate());
            c.setName(customer.getName());
            c.setAddress(customer.getAddress());
            return c;
        }).call(r -> r.persist());
    }

    /**
     *
     * @param id
     * @param customerRequest
     * @return
     */
    public Uni<Customer> update(String id, CustomerRequest customerRequest) {
        Uni<Customer> customerUni = findById(id);
        return customerUni
                .onItem().transform(c -> {
                    c.setName(customerRequest.getName());
                    c.setAddress(customerRequest.getAddress());
                    c.setBirthDate(customerRequest.getBirthDate());
                    return c;
                }).call(c -> c.update());
    }

    /**
     *
     * @param id
     * @return
     */
    public Uni<Customer> delete(String id) {
        Uni<Customer> customerUni = findById(id);
        customerKafkaProducer.onCustomerDeleted(id);
        redisService.set("id",id);
        return customerUni
                .onItem()
                .call(c -> customerReactiveRepository.deleteById(new ObjectId(id)));
    }

    /**
     *
     * @param name
     * @return
     */
    public Uni<Customer> findByName(String name) {
        return customerReactiveRepository.findByName(name);
    }

    /**
     *
     * @param id
     * @return
     */
    public Uni<Customer> findById(String id) {
        return customerReactiveRepository.findById(new ObjectId(id));
    }

    public Uni<List<Customer>> listPaged(PageRequest pageRequest) {
        return customerReactiveRepository.findAll()
                .page(Page.of(pageRequest.getPageNum(), pageRequest.getPageSize()))
                .list();
    }
}
