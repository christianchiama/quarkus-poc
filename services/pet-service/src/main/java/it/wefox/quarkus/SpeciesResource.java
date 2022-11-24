package it.wefox.quarkus;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.domain.Species;
import it.wefox.quarkus.service.SpeciesService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Christian Chiama
 * --
 */
@Path("/species")
public class SpeciesResource {

    @Inject
    SpeciesService speciesService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Species> list() {
        return speciesService.streamAllSpecies();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> create(Species species) {
        return speciesService.create(species).map(v -> Response.accepted(v).build());
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> update(@PathParam("id") String id, Species species) {
        Uni<Species> speciesUni = speciesService.update(id,species);
        return speciesUni.map(v -> Response.accepted(v).build());
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> delete(@PathParam("id") String id) {
        Uni<Species> speciesUni = speciesService.delete(id);
        return speciesUni.map(v -> Response.accepted(v).build());
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> findByName(@PathParam("name") String name) {
        Uni<Species> speciesUni = speciesService.findByName(name);
        return speciesUni.map(v -> Response.accepted(v).build());
    }
}
