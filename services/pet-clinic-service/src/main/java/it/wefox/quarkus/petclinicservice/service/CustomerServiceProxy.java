package it.wefox.quarkus.petclinicservice.service;

import it.wefox.quarkus.customerapi.api.CustomerApi;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Path;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 06/12/22
 * @Time: 19:55
 */

@RegisterRestClient(configKey = "customer-api")
@Path("/customers")
public interface CustomerServiceProxy extends CustomerApi {
}
