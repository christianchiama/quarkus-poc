package it.wefox.com.repository;

/**
 * @author Christian Chiama
 * --
 */

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
import io.smallrye.mutiny.Uni;
import it.wefox.com.domain.Customer;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CustomerReactiveMongoRepository implements ReactivePanacheMongoRepositoryBase<Customer, ObjectId> {

    /**
     *
     * @param name
     * @return
     */
    public Uni<Customer> findByName(String name) {
        return find("name", name).firstResult();
    }


}

