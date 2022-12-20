package it.wefox.quarkus.petapi.api;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.pagination.PageRequest;
import it.wefox.quarkus.petapi.request.SpeciesRequest;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Christian Chiama
 * --
 */

@Produces(MediaType.APPLICATION_JSON)
@Tag(name = "Species", description = "Species API")
@Server(url = "localhost", description = "Species api")
public interface SpeciesApi {

    @GET
    Multi<Response> list();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    Uni<Response> create(@Valid SpeciesRequest species);

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    Uni<Response> update(@PathParam("id") String id, @Valid SpeciesRequest species);

    @DELETE
    @Path("/{id}")
     Uni<Response> delete(@PathParam("id") String id);

    @GET
    @Path("/{id}")
    Uni<Response> findById(@PathParam("id") String id);

    @GET
    @Path("/name/{name}")
     Uni<Response> findByName(@PathParam("name") String name);

    @GET
    @Path("/page")
    Uni<Response> listPaged(PageRequest pageRequest);

    @POST
    @Path("/delete")
    Uni<Response> deleteAll();
}
