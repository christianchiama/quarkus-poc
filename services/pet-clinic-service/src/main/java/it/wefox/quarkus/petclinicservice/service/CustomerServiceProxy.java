package it.wefox.quarkus.petclinicservice.service;

import it.wefox.quarkus.api.CustomerApi;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

/**
 * @author Christian Chiama
 * --
 */
@RegisterRestClient(configKey = "customer-service.proxy")
@Path(value = "/customers")
public interface CustomerServiceProxy extends CustomerApi {

    @GET
    public default void hello(){}
}
