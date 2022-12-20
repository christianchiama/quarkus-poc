package it.wefox.quarkus.customercommons.exception;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 20/12/22
 * @Time: 01:45
 */
import org.jboss.resteasy.reactive.server.ServerExceptionMapper;
import org.jboss.resteasy.reactive.RestResponse;

import javax.ws.rs.core.Response;

public class ExceptionMappers {
    @ServerExceptionMapper
    public RestResponse<String> mapException(UnknownCustomerException x) {
        return RestResponse.status(Response.Status.NOT_FOUND, "Unknown customer: " + x.name);
    }
}