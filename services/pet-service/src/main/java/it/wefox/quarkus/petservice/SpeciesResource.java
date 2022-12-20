package it.wefox.quarkus.petservice;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.petservice.service.SpeciesService;
import it.wefox.quarkus.pagination.PageRequest;
import it.wefox.quarkus.petapi.api.SpeciesApi;
import it.wefox.quarkus.petapi.request.SpeciesRequest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 08/12/22
 * @Time: 17:50
 */
@Path("/species")
@RequestScoped
public class SpeciesResource implements SpeciesApi {

    @Inject
    SpeciesService speciesService;
    /**
     * @return
     */
    @Override
    public Multi<Response> list() {
        return speciesService
                .streamAllSpecies()
                .cache()
                .onItem()
                .transform(v-> Response.ok(v).build());
    }

    /**
     * @param species
     * @return
     */
    @Override
    public Uni<Response> create(SpeciesRequest species) {
        return speciesService
                .create(species)
                .map(v-> Response.ok(v).build());
    }

    /**
     * @param id
     * @param species
     * @return
     */
    @Override
    public Uni<Response> update(String id, SpeciesRequest species) {
        return speciesService
                .update(id, species)
                .map(v-> Response.ok(v).build());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Uni<Response> delete(String id) {
        return speciesService
                .delete(id)
                .map(v-> Response.ok(v).build());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Uni<Response> findById(String id) {
        return speciesService
                .findById(id)
                .map(v-> Response.ok(v).build());
    }

    /**
     * @param name
     * @return
     */
    @Override
    public Uni<Response> findByName(String name) {
        return speciesService
                .findByName(name)
                .map(v-> Response.ok(v).build());
    }

    /**
     * @param pageRequest
     * @return
     */
    @Override
    public Uni<Response> listPaged(PageRequest pageRequest) {
        return speciesService
                .listPaged(pageRequest)
                .map(v-> Response.ok(v).build());
    }

    /**
     * @return
     */
    @Override
    public Uni<Response> deleteAll() {
        return speciesService
                .deleteAll()
                .map(v-> Response.ok(v).build());
    }
}
