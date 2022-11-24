package it.wefox.quarkus.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.domain.Species;
import it.wefox.quarkus.repository.SpeciesReactiveMongoRepository;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author Christian Chiama
 * --
 */
@ApplicationScoped
public class SpeciesService {

    @Inject
    SpeciesReactiveMongoRepository speciesReactiveMongoRepository;

    /**
     * @return
     */
    public Multi<Species> streamAllSpecies() {
        return Species.streamAll();
    }

    /**
     * @param species
     * @return
     */
    public Uni<Species> create(Species species) {
        Uni<Species> speciesUni = Uni.createFrom().item(species);

        return speciesUni.onItem().transform(s -> {
            s.setName(species.getName());
            return s;
        }).call(s -> species.persist().chain(s::persistOrUpdate));
    }

    /**
     *
     * @param id
     * @param species
     * @return
     */
    public Uni<Species> update(String id, Species species) {
        ObjectId speciesId = new ObjectId(id);
        Uni<Species> speciesUni = speciesReactiveMongoRepository.findById(speciesId);
        return speciesUni
                .onItem().transform(s -> {
                    s.setName(species.getName());
                    return s;
                }).call(c -> Species.update(c));
    }


    /**
     *
     * @param id
     * @return
     */
    public Uni<Species> delete(String id) {
        ObjectId speciesId = new ObjectId(id);
        Uni<Species> speciesUni = speciesReactiveMongoRepository.findById(speciesId);
        return speciesUni
                .onItem()
                .call(c -> speciesReactiveMongoRepository.deleteById(speciesId));
    }

    /**
     *
     * @param id
     * @return
     */
    public Uni<Species> findById(String id) {
        ObjectId speciesId = new ObjectId(id);
        return speciesReactiveMongoRepository.findById(speciesId);
    }

    /**
     *
     * @param name
     * @return
     */
    public Uni<Species> findByName(String name) {
        return speciesReactiveMongoRepository.findByName(name);
    }
}
