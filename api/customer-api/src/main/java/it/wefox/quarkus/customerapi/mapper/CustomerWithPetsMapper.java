package it.wefox.quarkus.customerapi.mapper;

import it.wefox.quarkus.customerapi.request.CustomerRequest;
import it.wefox.quarkus.customerapi.request.CustomerWithPetsRequest;

import javax.inject.Singleton;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 05/12/22
 * @Time: 11:33
 */
@Singleton
public class CustomerWithPetsMapper {

    public java.util.function.Function<CustomerRequest, CustomerWithPetsRequest> toRequest() {
        return customer -> new CustomerWithPetsRequest(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getBirthDate(),
                customer.getPets());
    }

    public java.util.function.Function<CustomerWithPetsRequest, CustomerRequest> toEntity() {
        return customerRequest -> new CustomerRequest(
                customerRequest.getId(),
                customerRequest.getName(),
                customerRequest.getAddress(),
                customerRequest.getBirthDate(),
                customerRequest.getPets()
        );
    }
}

