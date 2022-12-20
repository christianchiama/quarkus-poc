package it.wefox.quarkus.petclinicservice.service;

import it.wefox.quarkus.treatmentapi.api.TreatmentApi;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.Path;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 08/12/22
 * @Time: 22:30
 */

@RegisterRestClient(configKey = "treatment-api")
@Path("/treatments")
public interface TreatmentServiceProxy extends TreatmentApi {
}
