package it.wefox.quarkus.repository;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.domain.Species;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Christian Chiama
 * --
 */
@ApplicationScoped
public class SpeciesReactiveMongoRepository implements ReactivePanacheMongoRepositoryBase<Species, ObjectId> {
    public Uni<Species> findByName(String name) {
        return find("name",name).firstResult();
    }
}
