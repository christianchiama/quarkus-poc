package it.wefox.quarkus.petclinicapi.api;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.customerapi.request.CustomerWithPetsRequest;
import it.wefox.quarkus.petapi.request.PetWithTreatmentsRequest;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 13/12/22
 * @Time: 15:41
 */
public interface PetClinicApi {

    @GET
    @Path(("c"))
    @Produces(MediaType.APPLICATION_JSON)
    Multi<Response> listCustomers();

    @GET
    @Path(("p"))
    @Produces(MediaType.APPLICATION_JSON)
    Multi<Response> listPets();

    @GET
    @Path(("t"))
    @Produces(MediaType.APPLICATION_JSON)
    Multi<Response> listTreatments();

    /**
     * @param customer
     * @return
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/createCustomer")
    Uni<Response> createCustomer(@Valid CustomerWithPetsRequest customer);

    @POST
    @Path("/createPet")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    Uni<Response> createPet(@Valid PetWithTreatmentsRequest customer);
}
