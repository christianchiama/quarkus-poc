package it.wefox.quarkus.treatmentapi.api;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 08/12/22
 * @Time: 21:34
 */
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.treatmentapi.request.TreatmentRequest;
import it.wefox.quarkus.pagination.PageRequest;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Tag(name = "treatment", description = "Treatment Operations")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public interface TreatmentApi {

    @GET
    @Operation(operationId = "getAllTreatment", summary = "get all treatment",
            description = "get all treatment present in a db")
    @APIResponse(
            responseCode = "200",
            description = "Get All Treatment"
    )
    Multi<Response> list();

    @Operation(operationId = "createTreatment", summary = "create treatment",
            description = "create new treatment")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "400", description = "Bad request")

    })
    @POST
    Uni<Response> create(@Valid TreatmentRequest treatment);


    @Operation(operationId = "updateTreatment", summary = "update treatment",
            description = "update existing treatment")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "400", description = "Bad request"),
            @APIResponse(responseCode = "404", description = "Treatment not found with the given id")

    })
    @PUT
    @Path("/{id}")
    Uni<Response> update(@PathParam("id") String id, @Valid TreatmentRequest treatment);


    @Operation(operationId = "deleteTreatmentById", summary = "delete treatment by id",
            description = "delete existing treatment")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "404", description = "Treatment not found with the given id")

    })
    @DELETE
    @Path("/{id}")
    Uni<Response> deleteById(@PathParam("id") String id);


    @POST
    @Path("/deleteAll")
    @Operation(operationId = "deleteAllTreatment", summary = "delete all treatment",
            description = "delete all existing treatment")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok")
    })
    Uni<Response> deleteAll();

    @GET
    @Path("/pet/{petId}")
    @Operation(operationId = "findByPetId", summary = "find treatment by petId",
            description = "find treatment by petId")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "404", description = "Treatment not found with the given petId")

    })
    Uni<Response> findByPetId(@PathParam("petId") String petId);

    @GET
    @Path("/name/{name}")
    @Operation(operationId = "findByName", summary = "find treatment by name",
            description = "find treatment by name")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "404", description = "Treatment not found with the given name")

    })
    Uni<Response> findByName(@PathParam("name") String name);

    @GET
    @Path("/{id}")
    @Operation(operationId = "findById", summary = "find treatment by id",
            description = "find treatment by id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "404", description = "Treatment not found with the given id")

    })
    Uni<Response> findById(@PathParam("id") String id);


    @GET
    @Path("/paged")
    @Operation(operationId = "listPaged", summary = "find treatment in paged",
            description = "find treatment in page")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "400", description = "Bad request"),

    })
    Uni<Response> listPaged(PageRequest pageRequest);

}

