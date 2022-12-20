package it.wefox.quarkus.customerservice.mapper;

import it.wefox.quarkus.customerapi.request.CustomerRequest;
import it.wefox.quarkus.customerservice.domain.Customer;

import javax.inject.Singleton;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 05/12/22
 * @Time: 11:33
 */
@Singleton
public class CustomerMapper {

    public java.util.function.Function<Customer, CustomerRequest> toRequest() {
        return customer -> new CustomerRequest(
                customer.getId(),
                customer.getName(),
                customer.getAddress(),
                customer.getBirthDate(),
                customer.getPets());
    }

    public java.util.function.Function<CustomerRequest, Customer> toEntity() {
        return customerRequest -> new Customer(
                customerRequest.getId(),
                customerRequest.getName(),
                customerRequest.getAddress(),
                customerRequest.getBirthDate(),
                customerRequest.getPets()
        );
    }
}

