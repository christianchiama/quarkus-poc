package it.wefox.quarkus.repository;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.domain.Treatment;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Christian Chiama
 * --
 */
@ApplicationScoped
public class TreatmentReactiveMongoRepository implements ReactivePanacheMongoRepositoryBase<Treatment, ObjectId> {
    public Uni<Treatment> findByName(String name) {
        return find("name",name).firstResult();
    }
}
