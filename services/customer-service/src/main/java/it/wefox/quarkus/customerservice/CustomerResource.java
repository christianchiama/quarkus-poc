package it.wefox.quarkus.customerservice;

import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import it.wefox.quarkus.customerapi.api.CustomerApi;
import it.wefox.quarkus.customerapi.request.CustomerRequest;
import it.wefox.quarkus.customerservice.domain.Customer;
import it.wefox.quarkus.customerservice.service.CustomerService;
import it.wefox.quarkus.pagination.PageRequest;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("/customers")
public class CustomerResource implements CustomerApi {

    @Inject
    CustomerService customerService;


    @Override
    public Multi<Response> list() {
        return Customer
                .streamAll()
                .onItem()
                .transform(v -> Response.ok(v).build());
    }


    @Override
    public Uni<Response> create(@Valid CustomerRequest customer) {
        return customerService
                .create(customer)
                .onItem()
                .transform(v -> Response.ok(v).build());
    }

    @Override
    public Uni<Response> update(String id, @Valid CustomerRequest customer) {
        return customerService
                .update(id, customer)
                .onItem()
                .transform(v -> Response.ok(v).build());
    }

    @Override
    public Uni<Response> delete(String id) {
        return customerService
                .delete(id)
                .onItem()
                .transform(v -> Response.ok(v).build());
    }

    @Override
    public Uni<Response> findByName(String name) {
        return customerService
                .findByName(name)
                .onItem()
                .transform(v -> Response.ok(v).build());
    }

    @Override
    public Uni<Response> findById(String id) {
        return customerService
                .findById(id)
                .onItem()
                .transform(v -> Response.ok(v).build());
    }

    /**
     * @param pageRequest
     * @return
     */
    @Override
    public Uni<Response> listPaged(PageRequest pageRequest) {
        return customerService.listPaged(pageRequest).map(v -> Response.ok(v).build());
    }
}