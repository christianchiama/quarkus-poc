package it.wefox.quarkus.petservice.mapper;

import it.wefox.quarkus.petservice.domain.Pet;
import it.wefox.quarkus.petapi.request.PetRequest;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * @author Christian Chiama
 * --
 */
@Singleton
public class PetMapper {

    @Inject
    SpeciesMapper speciesMapper;

/*    public java.util.function.Function<Pet, PetRequest> toRequest() {
        return pet -> new PetRequest(
                pet.getId(),
                pet.getName(),
                pet.getBirthDate(),
                speciesMapper.toRequest().apply(pet.getSpecies()),
                pet.getOwnerId());
    }*/
    public java.util.function.Function<PetRequest, Pet> toEntity() {
        return petRequest -> new Pet(
                petRequest.getId(),
                petRequest.getName(),
                petRequest.getBirthDate(),
                speciesMapper.toEntity().apply(petRequest.getSpecies()),
                petRequest.getOwnerId());
    }
}
