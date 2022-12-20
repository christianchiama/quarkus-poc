package it.wefox.quarkus.petapi.mapper;

import it.wefox.quarkus.petapi.request.PetRequest;
import it.wefox.quarkus.petapi.request.PetWithTreatmentsRequest;

import javax.inject.Singleton;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 13/12/22
 * @Time: 15:28
 */
@Singleton
public class PetWithTreatmentMapper {


    public java.util.function.Function<PetRequest, PetWithTreatmentsRequest> toRequest() {
        return pet -> new PetWithTreatmentsRequest(
                pet.getId(),
                pet.getName(),
                pet.getBirthDate(),
                pet.getSpecies(),
                pet.getOwnerId(),
                pet.getTreatments());
    }

    public java.util.function.Function<PetWithTreatmentsRequest, PetRequest> toEntity() {
        return pet -> new PetRequest(
                pet.getId(),
                pet.getName(),
                pet.getBirthDate(),
                pet.getSpecies(),
                pet.getOwnerId(),
                pet.getTreatments());
    }
}
