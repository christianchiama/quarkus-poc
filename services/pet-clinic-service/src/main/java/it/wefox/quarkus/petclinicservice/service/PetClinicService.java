package it.wefox.quarkus.petclinicservice.service;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.customerapi.mapper.CustomerWithPetsMapper;
import it.wefox.quarkus.customerservice.domain.Customer;
import it.wefox.quarkus.customerapi.request.CustomerWithPetsRequest;
import it.wefox.quarkus.petapi.mapper.PetWithTreatmentMapper;
import it.wefox.quarkus.petapi.request.PetWithTreatmentsRequest;
import it.wefox.quarkus.petservice.domain.Pet;
import it.wefox.quarkus.treatmentservice.domain.Treatment;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 08/12/22
 * @Time: 03:25
 */
@ApplicationScoped
public class PetClinicService {

    @RestClient
    CustomerServiceProxy customerServiceProxy;

    @RestClient
    PetServiceProxy petServiceProxy;

    @RestClient
    TreatmentServiceProxy treatmentServiceProxy;

    @Inject
    CustomerWithPetsMapper customerWithPetsMapper;

    @Inject
    PetWithTreatmentMapper petWithTreatmentMapper;

    public Multi<Customer> streamAllCustomer(){
        return customerServiceProxy.list().cache().onItem().transform(p-> (Customer) p.getEntity());
    }

    public Multi<Pet> streamAllPets(){
        return petServiceProxy.list().cache().onItem().transform(p-> (Pet) p.getEntity());
    }

    public Multi<Treatment> streamAllTreatments(){
        return treatmentServiceProxy.list().cache().onItem().transform(p-> (Treatment) p.getEntity());
    }

    public Uni<CustomerWithPetsRequest> createCustomerWithPetsRequest(CustomerWithPetsRequest customer) {
        return customerServiceProxy
                .create(customerWithPetsMapper.toEntity().apply(customer)).map(z -> (CustomerWithPetsRequest) z.getEntity());
    }

    public Uni<PetWithTreatmentsRequest> createPetWithTreatment(PetWithTreatmentsRequest pet) {
        return petServiceProxy.createPetWithTreatments(pet).map(z -> (PetWithTreatmentsRequest) z.getEntity());
    }
}
