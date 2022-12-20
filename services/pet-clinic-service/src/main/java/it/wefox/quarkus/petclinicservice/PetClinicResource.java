package it.wefox.quarkus.petclinicservice;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.customerapi.request.CustomerWithPetsRequest;
import it.wefox.quarkus.petapi.request.PetWithTreatmentsRequest;
import it.wefox.quarkus.petclinicapi.api.PetClinicApi;
import it.wefox.quarkus.petclinicservice.service.PetClinicService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/pet-clinic")
@RequestScoped
public class PetClinicResource implements PetClinicApi {

    @Inject
    PetClinicService petClinicService;

   @Override
    public Multi<Response> listCustomers(){
        return petClinicService
                .streamAllCustomer()
                .map(v-> Response.ok(v).build());
    }

    @Override
    public Multi<Response> listPets(){
        return petClinicService
                .streamAllPets()
                .map(v-> Response.ok(v).build());
    }

    @Override
    public Multi<Response> listTreatments(){
        return petClinicService
                .streamAllTreatments()
                .map(v-> Response.ok(v).build());
    }

    @Override
    public Uni<Response> createCustomer(@Valid CustomerWithPetsRequest customer) {
        return petClinicService.createCustomerWithPetsRequest(customer).map(v-> Response.ok(v).build());
    }

    @Override
    public Uni<Response> createPet(@Valid PetWithTreatmentsRequest customer) {
        return petClinicService.createPetWithTreatment(customer).map(v-> Response.ok(v).build());
    }
}