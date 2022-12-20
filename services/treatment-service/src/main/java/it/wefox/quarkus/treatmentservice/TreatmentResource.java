package it.wefox.quarkus.treatmentservice;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.treatmentapi.api.TreatmentApi;
import it.wefox.quarkus.treatmentapi.request.TreatmentRequest;
import it.wefox.quarkus.treatmentservice.domain.Treatment;
import it.wefox.quarkus.treatmentservice.mapper.TreatmentMapper;
import it.wefox.quarkus.treatmentservice.service.TreatmentService;
import it.wefox.quarkus.pagination.PageRequest;
import org.eclipse.microprofile.openapi.annotations.Operation;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static javax.ws.rs.core.Response.status;

@Path("/treatments")
public class TreatmentResource implements TreatmentApi {

    @Inject
    TreatmentService treatmentService;



   @Override
    public Multi<Response> list() {
        return treatmentService.streamAllTreatment().map(v -> Response.accepted(v).build());
    }

   @Override
   public Uni<Response> create(@Valid TreatmentRequest treatment) {
        return treatmentService.create(treatment)
                .onItem()
                .ifNotNull()
                .transform(v -> Response.accepted(v).build())
                .onItem()
                .ifNull()
                .continueWith(status(BAD_REQUEST)::build);
    }

    @Override
    public Uni<Response> update(@PathParam("id") String id, @Valid TreatmentRequest treatment) {
        Uni<Treatment> treatmentUni = treatmentService.update(id, treatment);
        return treatmentUni
                .onItem()
                .ifNotNull()
                .transform(v -> Response.accepted(v).build())
                .onItem()
                .ifNull()
                .continueWith(status(BAD_REQUEST)::build);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public Uni<Response> deleteById(@PathParam("id") String id) {
        return treatmentService.delete(id)
                .onItem()
                .ifNotNull()
                .transform(v -> Response.accepted(v).build());
    }

    /**
     * @return
     */
    @Override
    public Uni<Response> deleteAll() {
        return null;
    }


    @Override
    public Uni<Response> findByPetId(@PathParam("petId") String petId) {
        Uni<Treatment> treatmentUni = treatmentService.findByPetId(petId);
        return treatmentUni
                .onItem()
                .ifNotNull()
                .transform(v -> Response.accepted(v).build());
    }

    @Override
    public Uni<Response> findByName(@PathParam("name") String name) {
        Uni<Treatment> treatmentUni = treatmentService.findByName(name);
        return treatmentUni
                .onItem()
                .ifNotNull()
                .transform(v -> Response.accepted(v).build())
                .onItem()
                .ifNull()
                .continueWith(status(BAD_REQUEST)::build);
    }

    @Override
    public Uni<Response> findById(@PathParam("id") String id) {
        Uni<Treatment> treatmentUni = treatmentService.findById(id);
        return treatmentUni
                .onItem()
                .ifNotNull()
                .transform(v -> Response.accepted(v).build())
                .onItem()
                .ifNull()
                .continueWith(status(BAD_REQUEST)::build);
    }

    /**
     * @param pageRequest
     * @return
     */
    @Override
    public Uni<Response> listPaged(PageRequest pageRequest) {
        return treatmentService.listPaged(pageRequest).map(v -> Response.accepted(v).build());
    }
}