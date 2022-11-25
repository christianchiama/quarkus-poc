package it.wefox.quarkus;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.domain.Treatment;
import it.wefox.quarkus.service.TreatmentService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/treatments")
public class TreatmentResource {

    @Inject
    TreatmentService treatmentService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Treatment> list() {
        return treatmentService.streamAllTreatment();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> create(Treatment treatment) {
        return treatmentService.create(treatment).map(v -> Response.accepted(v).build());
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> update(@PathParam("id") String id, Treatment treatment) {
        Uni<Treatment> treatmentUni = treatmentService.update(id, treatment);
        return treatmentUni.map(v -> Response.accepted(v).build());
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> delete(@PathParam("id") String id) {
        Uni<Treatment> treatmentUni = treatmentService.delete(id);
        return treatmentUni.map(v -> Response.accepted(v).build());
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> findByName(@PathParam("name") String name) {
        Uni<Treatment> treatmentUni = treatmentService.findByName(name);
        return treatmentUni.map(v -> Response.accepted(v).build());
    }
}