package it.wefox.quarkus.customerservice.repository;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.customerservice.domain.Customer;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 05/12/22
 * @Time: 11:26
 */
@ApplicationScoped
public class CustomerReactiveRepository implements ReactivePanacheMongoRepositoryBase<Customer, ObjectId> {

    /**
     *
     * @param name
     * @return
     */
    public Uni<Customer> findByName(String name) {
        return find("name", name).firstResult();
    }


}
