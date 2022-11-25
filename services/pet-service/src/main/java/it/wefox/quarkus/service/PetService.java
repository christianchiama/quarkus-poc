package it.wefox.quarkus.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.domain.Pet;
import it.wefox.quarkus.repository.PetReactiveMongoRepository;
import it.wefox.quarkus.repository.PetReactiveMongoRepository;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author Christian Chiama
 * --
 */
@ApplicationScoped
public class PetService {

    @Inject
    PetReactiveMongoRepository petReactiveMongoRepository;

    /**
     * @return
     */
    public Multi<Pet> streamAllPet() {
        return Pet.streamAll();
    }

    /**
     * @param pet
     * @return
     */
    public Uni<Pet> create(Pet pet) {
        Uni<Pet> petUni = Uni.createFrom().item(pet);

        return petUni.onItem().transform(p -> {
            p.setName(pet.getName());
            return p;
        }).call(p -> pet.persist());
    }

    /**
     *
     * @param id
     * @param pet
     * @return
     */
    public Uni<Pet> update(String id, Pet pet) {
        ObjectId petId = new ObjectId(id);
        Uni<Pet> petUni = petReactiveMongoRepository.findById(petId);
        return petUni
                .onItem().transform(p -> {
                    p.setName(pet.getName());
                    return p;
                }).call(p-> Pet.update(p));
    }


    /**
     *
     * @param id
     * @return
     */
    public Uni<Pet> delete(String id) {
        ObjectId petId = new ObjectId(id);
        Uni<Pet> petUni = petReactiveMongoRepository.findById(petId);
        return petUni
                .onItem()
                .call(c -> petReactiveMongoRepository.deleteById(petId));
    }

    /**
     *
     * @param id
     * @return
     */
    public Uni<Pet> findByOwnerId(String id) {
        ObjectId ownerId = new ObjectId(id);
        return petReactiveMongoRepository.findById(ownerId);
    }

    /**
     *
     * @param name
     * @return
     */
    public Uni<Pet> findByName(String name) {
        return petReactiveMongoRepository.findByName(name);
    }
}
