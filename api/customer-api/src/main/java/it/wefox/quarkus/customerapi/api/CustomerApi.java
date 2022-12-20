package it.wefox.quarkus.customerapi.api;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.customerapi.request.CustomerRequest;
import it.wefox.quarkus.pagination.PageRequest;
import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponse;
import org.eclipse.microprofile.openapi.annotations.responses.APIResponses;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;
import org.jboss.resteasy.reactive.ResponseStatus;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 05/12/22
 * @Time: 12:25
 */
@Tag(name = "Customers", description = "Customers API")
public interface CustomerApi {


    @Operation(operationId = "listCustomer", summary = "list of  customers",
            description = "get list of customers")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok")
    })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    Multi<Response> list();


    @Operation(operationId = "createCustomer", summary = "create new  customers",
            description = "create new customers")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "400", description = "Bad request")
    })
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Uni<Response> create(@Valid CustomerRequest customer);


    @Operation(operationId = "updateCustomer", summary = "update existing  customers",
            description = "update existing customers")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "400", description = "Bad request"),
            @APIResponse(responseCode = "404", description = "Not Found")
    })
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    Uni<Response> update(@PathParam(value = "id") String id, @Valid CustomerRequest customer);

    @Operation(operationId = "deleteCustomer", summary = "delete existing  customers by id",
            description = "delete existing customers by id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "404", description = "Not Found")
    })

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    Uni<Response> delete(@PathParam(value = "id") String id);


    @Operation(operationId = "findByName", summary = "find customer by name",
            description = "find customer by name")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "404", description = "Not Found")
    })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/name/{name}")
    Uni<Response> findByName(@PathParam(value = "name") String name);

    @Operation(operationId = "findById", summary = "find customer by id",
            description = "find customer by id")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "404", description = "Not Found")
    })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    Uni<Response> findById(@PathParam(value = "id") String id);

    @Operation(operationId = "listPaged", summary = "find customers with page",
            description = "find customers and result is paged")
    @APIResponses(value = {
            @APIResponse(responseCode = "200", description = "Ok"),
            @APIResponse(responseCode = "400", description = "Bad request")
    })
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/page")
    Uni<Response> listPaged(@BeanParam PageRequest pageRequest);
}
