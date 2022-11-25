package it.wefox.quarkus.repository;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.domain.Pet;
import it.wefox.quarkus.domain.Species;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Christian Chiama
 * --
 */
@ApplicationScoped
public class PetReactiveMongoRepository implements ReactivePanacheMongoRepositoryBase<Pet, ObjectId> {
    /**
     *
     * @param name
     * @return
     */
    public Uni<Pet> findByName(String name) {
        return find("name",name).firstResult();
    }

    /**
     *
     * @param ownerId
     * @return
     */
    public Uni<Pet> findByOwnerId(String ownerId) {
        return find("ownerId",ownerId).firstResult();
    }

}
