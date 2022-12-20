package it.wefox.quarkus.petclinicservice.service;

import it.wefox.quarkus.petapi.api.PetApi;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Path;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 08/12/22
 * @Time: 22:35
 */
@RegisterRestClient(configKey = "pet-api")
@Path("/species")
public interface SpeciesServiceProxy extends PetApi {
}
