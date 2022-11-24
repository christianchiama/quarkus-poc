package it.wefox.quarkus.repository;

import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoRepositoryBase;
import it.wefox.quarkus.domain.Pet;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

/**
 * @author Christian Chiama
 * --
 */
@ApplicationScoped
public class PetReactiveMongoRepository implements ReactivePanacheMongoRepositoryBase<Pet, ObjectId> {
}
