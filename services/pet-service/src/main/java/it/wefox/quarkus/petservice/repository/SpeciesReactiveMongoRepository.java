package it.wefox.quarkus.petservice.repository;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.petservice.domain.Species;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 08/12/22
 * @Time: 17:45
 */

@ApplicationScoped
public class SpeciesReactiveMongoRepository implements ReactivePanacheMongoRepositoryBase<Species, ObjectId> {

    /**
     *
     * @param name
     * @return
     */
    public Uni<Species> findByName(String name) {
        return find("name",name).firstResult();
    }

    public Uni<Void> deleteAllSpecies(){
        return deleteAll().replaceWithVoid();
    }
}
