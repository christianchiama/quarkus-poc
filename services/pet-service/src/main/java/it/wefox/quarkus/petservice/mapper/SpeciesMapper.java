package it.wefox.quarkus.petservice.mapper;

import it.wefox.quarkus.petservice.domain.Species;
import it.wefox.quarkus.petapi.request.SpeciesRequest;

import javax.inject.Singleton;

/**
 * @author Christian Chiama
 * --
 */
@Singleton
public class SpeciesMapper {

    public java.util.function.Function<Species, SpeciesRequest> toRequest() {
        return species -> new SpeciesRequest(
                species.getId(),
                species.getName());
    }

    public java.util.function.Function<SpeciesRequest, Species> toEntity() {
        return speciesRequest -> new Species(
                speciesRequest.getId(),
                speciesRequest.getName());
    }
}
