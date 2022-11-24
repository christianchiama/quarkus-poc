package it.wefox.com;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.com.domain.Customer;
import it.wefox.com.service.CustomerService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/customers")
public class CustomerResource {

    @Inject
    CustomerService customerService;


    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Multi<Customer> list() {
        return customerService.streamAllCustomer();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> create(Customer customer) {
        return customerService.create(customer).map(v -> Response.accepted(v).build());
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> update(@PathParam("id") String id, Customer customerRequest) {
        Uni<Customer> customer = customerService.update(id,customerRequest);
        return customer.map(v -> Response.accepted(v).build());
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> delete(@PathParam("id") String id) {
        Uni<Customer> customer = customerService.delete(id);
        return customer.map(v -> Response.accepted(v).build());
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> findById(@PathParam("id") String id) {
        Uni<Customer> customer = customerService.findById(id);
        return customer.map(v -> Response.accepted(v).build());
    }

    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> findByName(@PathParam("name") String name) {
        Uni<Customer> customer = customerService.findByName(name);
        return customer.map(v -> Response.accepted(v).build());
    }
}