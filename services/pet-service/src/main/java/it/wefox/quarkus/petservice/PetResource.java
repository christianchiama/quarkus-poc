package it.wefox.quarkus.petservice;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.pagination.PageRequest;
import it.wefox.quarkus.petapi.api.PetApi;
import it.wefox.quarkus.petapi.request.PetRequest;
import it.wefox.quarkus.petapi.request.PetWithTreatmentsRequest;
import it.wefox.quarkus.petservice.domain.Pet;
import it.wefox.quarkus.petservice.service.PetService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.util.List;

import static javax.ws.rs.core.Response.Status.NOT_FOUND;
import static javax.ws.rs.core.Response.status;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 08/12/22
 * @Time: 02:53
 */
@RequestScoped
@Path("/pets")
public class PetResource implements PetApi {

    @Inject
    PetService petService;


    @Override
    public Multi<Response> list(){
        return petService
                .streamAllPet()
                .cache()
                .onItem()
                .transform(v-> Response.ok(v).build());
    }

    @Override
    public Uni<Response> create(@Valid PetRequest pet){
        return petService.create(pet).map(v-> Response.ok(v).build());
    }


    @Override
    public Uni<Response> createPetWithTreatments(@Valid PetWithTreatmentsRequest pet){
        return petService.createPetWithTreatment(pet).map(t-> Response.accepted().entity(t).build());
    }

    /**
     * @param id
     * @param pet
     * @return
     */
    @Override
    public Uni<Response> update(String id, @Valid PetRequest pet) {
        return petService.update(id,pet).map(v-> Response.ok(v).build());
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Uni<Response> delete(String id) {
        return petService.delete(id)
                .map(v -> Response.noContent().build());
    }

    /**
     * @return
     */
    @Override
    public Uni<Response> deleteAll() {
        return petService
                .deleteAll()
                .map(v -> Response.noContent().build());
    }


    /**
     * @param id
     * @return
     */
    @Override
    public Uni<Response> findById(String id) {
        Uni<Pet> petUni = petService.findById(id);
        return petUni
                .onItem()
                .ifNotNull()
                .transform(v -> Response.accepted(v).build())
                .onItem()
                .ifNull()
                .continueWith(status(NOT_FOUND)::build);
    }

    /**
     * @param name
     * @return
     */
    @Override
    public Uni<Response> findByName(String name) {
        Uni<Pet> petUni = petService.findByName(name);
        return petUni
                .onItem()
                .ifNotNull()
                .transform(v -> Response.accepted(v).build())
                .onItem()
                .ifNull()
                .continueWith(status(NOT_FOUND)::build);
    }

    /**
     * @param ownerId
     * @return
     */
    @Override
    public Uni<Response> findByOwnerId(String ownerId) {
        Uni<Pet> petUni = petService.findByOwnerId(ownerId);
        return petUni
                .onItem()
                .ifNotNull()
                .transform(v -> Response.accepted(v).build())
                .onItem()
                .ifNull()
                .continueWith(status(NOT_FOUND)::build);
    }

    /**
     * @param pageRequest
     * @return
     */
    @Override
    public Uni<Response> listPaged(PageRequest pageRequest) {
        Uni<List<Pet>> petUni = petService.listPaged(pageRequest);
        return petUni
                .onItem()
                .ifNotNull()
                .transform(v -> Response.accepted(v).build())
                .onItem()
                .ifNull()
                .continueWith(status(NOT_FOUND)::build);
    }
}
