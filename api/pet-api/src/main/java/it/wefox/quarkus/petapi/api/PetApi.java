package it.wefox.quarkus.petapi.api;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.wefox.quarkus.petapi.request.PetRequest;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Christian Chiama
 * --
 */

@Valid
@Tag(name = "Customers", description = "Pet API")
@Server(url = "localhost", description = "pet api")
public interface PetApi {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<PetRequest> list();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> create(PetRequest pet);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> update(@PathParam("id") String id, PetRequest pet);

    @DELETE
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> delete(@PathParam("id") String id);

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> findByName(@PathParam("name") String name);

    @GET
    @Path("/owner/{ownerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> findByOwnerId(@PathParam("ownerId") String ownerId);

}