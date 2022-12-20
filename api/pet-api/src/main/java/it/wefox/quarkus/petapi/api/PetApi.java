package it.wefox.quarkus.petapi.api;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.pagination.PageRequest;
import it.wefox.quarkus.petapi.request.PetRequest;
import it.wefox.quarkus.petapi.request.PetWithTreatmentsRequest;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.enterprise.context.RequestScoped;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Christian Chiama
 * --
 */

@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Pet", description = "Pet API")
public interface PetApi {


    @Operation(operationId = "getAllPet", summary = "get all pets",
            description = "get all pets present in a db")
    @APIResponse(
            responseCode = "200",
            description = "Get All pets"
    )
    @GET
    Multi<Response> list();

    @Operation(operationId = "createPet", summary = "create pet",
            description = "create new pet")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "400", description = "Bad request")

    })
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Uni<Response> create(@Valid PetRequest pet);

    @POST
    @Path("/treatment")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Uni<Response> createPetWithTreatments(@Valid PetWithTreatmentsRequest pet);


    @Operation(operationId = "updatePet", summary = "update pet",
            description = "update existing pet")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "400", description = "Bad request"),
            @APIResponse(responseCode = "404", description = "Pet not found with the given id")

    })
    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Uni<Response> update(@PathParam("id") String id, @Valid PetRequest pet);


    @Operation(operationId = "deletePetById", summary = "delete pet by id",
            description = "delete existing pet")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "404", description = "Pet not found with the given id")

    })
    @DELETE
    @Path("/{id}")
    Uni<Response> delete(@PathParam("id") String id);

    @Operation(operationId = "deleteAllPets", summary = "delete pets",
            description = "delete all existing pet")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "404", description = "Pet not found with the given id")

    })
    @POST
    @Path("/deleteAll")
    Uni<Response> deleteAll();


    @Operation(operationId = "findById", summary = "find pet by id",
            description = "find pet by id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "404", description = "Pet not found with the given id")

    })
    @GET
    @Path("/{id}")
    Uni<Response> findById(@PathParam("id") String id);

    @Operation(operationId = "findByName", summary = "find pet by name",
            description = "find pet by name")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "404", description = "Pet not found with the given name")

    })
    @GET
    @Path("/name/{name}")
    Uni<Response> findByName(@PathParam("name") String name);


    @Operation(operationId = "findByOwnerId", summary = "find pet by ownerId",
            description = "find pet by ownerId")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "404", description = "Pet not found with the given ownerId")

    })
    @GET
    @Path("/owner/{ownerId}")
    Uni<Response> findByOwnerId(@PathParam("ownerId") String ownerId);

    @Operation(operationId = "listPaged", summary = "find pet in paged",
            description = "find pet in page")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "400", description = "Bad request"),

    })
    @GET
    @Path("/paged")
    Uni<Response> listPaged(@BeanParam PageRequest pageRequest);
}