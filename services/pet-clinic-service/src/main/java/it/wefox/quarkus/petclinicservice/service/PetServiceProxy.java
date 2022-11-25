package it.wefox.quarkus.petclinicservice.service;

import it.wefox.quarkus.petapi.api.PetApi;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Path;

/**
 * @author Christian Chiama
 * --
 */
@RegisterRestClient(configKey = "pet-service.proxy")
@Path(value = "/pets")
public interface PetServiceProxy extends PetApi {}

