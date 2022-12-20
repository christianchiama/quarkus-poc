package it.wefox.quarkus.petservice.repository;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.petservice.domain.Pet;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 08/12/22
 * @Time: 02:42
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

    public Uni<Void> deleteAllPet(){
        return deleteAll().replaceWithVoid();
    }
}
