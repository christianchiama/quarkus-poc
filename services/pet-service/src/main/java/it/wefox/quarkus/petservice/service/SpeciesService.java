package it.wefox.quarkus.petservice.service;

import io.quarkus.panache.common.Page;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.petservice.mapper.SpeciesMapper;
import it.wefox.quarkus.pagination.PageRequest;
import it.wefox.quarkus.petservice.domain.Species;
import it.wefox.quarkus.petservice.repository.SpeciesReactiveMongoRepository;
import it.wefox.quarkus.petapi.request.SpeciesRequest;
import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 08/12/22
 * @Time: 17:47
 */
@ApplicationScoped
public class SpeciesService {

    @Inject
    SpeciesReactiveMongoRepository speciesReactiveMongoRepository;

    @Inject
    SpeciesMapper speciesMapper;

    /**
     * @return
     */
    public Multi<Species> streamAllSpecies() {
        return Species.streamAll();
    }

    /**
     * @param speciesRequest
     * @return
     */
    public Uni<Species> create(SpeciesRequest speciesRequest) {
        Uni<SpeciesRequest> speciesUni = Uni.createFrom().item(speciesRequest);

        return speciesUni.onItem().transform(p -> {
            p.setName(speciesRequest.getName());
            return p;
        }).map(p -> speciesMapper.toEntity().apply(p)).call(p -> p.persist());
    }

    /**
     *
     * @param id
     * @param speciesRequest
     * @return
     */
    public Uni<Species> update(String id, SpeciesRequest speciesRequest) {
        ObjectId speciesId = new ObjectId(id);
        return speciesReactiveMongoRepository.findById(speciesId)
                .onItem().transform(p -> {
                    p.setId(new ObjectId(id));
                    p.setName(speciesRequest.getName());
                    return p;
                }).call(p -> p.update());
    }


    /**
     *
     * @param id
     * @return
     */
    public Uni<Void> delete(String id) {
        ObjectId SpeciesId = new ObjectId(id);
        return speciesReactiveMongoRepository.deleteById(SpeciesId).replaceWithVoid();
    }


    /**
     *
     * @param id
     * @return
     */
    public Uni<Species> findById(String id) {
        ObjectId SpeciesId = new ObjectId(id);
        return speciesReactiveMongoRepository
                .findById(SpeciesId);
    }

    /**
     *
     * @param name
     * @return
     */
    public Uni<Species> findByName(String name) {
        return speciesReactiveMongoRepository.findByName(name);
    }

    public Uni<List<Species>> listPaged(PageRequest pageRequest) {
        return speciesReactiveMongoRepository.findAll()
                .page(Page.of(pageRequest.getPageNum(), pageRequest.getPageSize()))
                .list();
    }

    public Uni<Void> deleteAll() {
        return speciesReactiveMongoRepository.deleteAllSpecies();
    }
}

