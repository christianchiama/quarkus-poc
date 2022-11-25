package it.wefox.quarkus.customerservice.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.customerservice.domain.Customer;
import it.wefox.quarkus.customerservice.repository.CustomerReactiveMongoRepository;
import org.bson.types.ObjectId;
import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author Christian Chiama
 * --
 */
@ApplicationScoped
public class CustomerService {

    private final Logger logger = Logger.getLogger(CustomerService.class);

    @Inject
    CustomerReactiveMongoRepository customerReactiveMongoRepository;


    /**
     * @return
     */
    public Multi<Customer> streamAllCustomer() {
        return Customer.streamAll();
    }

    /**
     * @param customer
     * @return
     */
    public Uni<Customer> create(Customer customer) {
        Uni<Customer> customerUni = Uni.createFrom().item(customer);

        return customerUni.onItem().transform(c -> {
            c.setBirthDate(customer.getBirthDate());
            c.setName(customer.getName());
            c.setAddress(customer.getAddress());
            return c;
        }).call(c -> customer.persist());
    }

    /**
     *
     * @param id
     * @param customerRequest
     * @return
     */
    public Uni<Customer> update(String id, Customer customerRequest) {
        ObjectId customerId = new ObjectId(id);
        Uni<Customer> customerUni = customerReactiveMongoRepository.findById(customerId);
        return customerUni
                .onItem().transform(customer -> {
                    customer.setName(customerRequest.getName());
                    customer.setAddress(customerRequest.getAddress());
                    customer.setBirthDate(customerRequest.getBirthDate());
                    return customer;
                }).call(c -> Customer.update(c));
    }


    /**
     *
     * @param id
     * @return
     */
    public Uni<Customer> delete(String id) {
        ObjectId customerId = new ObjectId(id);
        Uni<Customer> customerUni = customerReactiveMongoRepository.findById(customerId);
        return customerUni
                .onItem()
                .call(c -> customerReactiveMongoRepository.deleteById(customerId));
    }

    /**
     *
     * @param id
     * @return
     */
    public Uni<Customer> findById(String id) {
        ObjectId customerId = new ObjectId(id);
        return customerReactiveMongoRepository.findById(customerId);
    }

    /**
     *
     * @param name
     * @return
     */
    public Uni<Customer> findByName(String name) {
        return customerReactiveMongoRepository.findByName(name);
    }

}
