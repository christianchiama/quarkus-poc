package it.wefox.quarkus.api;

/**
 * @author Christian Chiama
 * --
 */

import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

import javax.validation.Valid;

@Valid
@Tag(name = "Customers", description = "Customers API")
@Server(url = "${host.url}", description = "${host.description}")
public interface CustomerApi {



}