package it.wefox.quarkus;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.domain.Pet;
import it.wefox.quarkus.service.PetService;
import it.wefox.quarkus.service.PetService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/pets")
public class PetResource {

    @Inject
    PetService petService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Pet> list() {
        return petService.streamAllPet();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> create(Pet pet) {
        return petService.create(pet).map(v -> Response.accepted(v).build());
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> update(@PathParam("id") String id, Pet pet) {
        Uni<Pet> petUni = petService.update(id, pet);
        return petUni.map(v -> Response.accepted(v).build());
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> delete(@PathParam("id") String id) {
        Uni<Pet> petUni = petService.delete(id);
        return petUni.map(v -> Response.accepted(v).build());
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> findByName(@PathParam("name") String name) {
        Uni<Pet> petUni = petService.findByName(name);
        return petUni.map(v -> Response.accepted(v).build());
    }

    @GET
    @Path("/owner/{ownerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> findByOwnerId(@PathParam("ownerId") String ownerId) {
        Uni<Pet> petUni = petService.findByOwnerId(ownerId);
        return petUni.map(v -> Response.accepted(v).build());
    }
}