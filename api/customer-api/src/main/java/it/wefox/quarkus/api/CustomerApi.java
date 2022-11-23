package it.wefox.quarkus.api;

/**
 * @author Christian Chiama
 * --
 */

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import it.wefox.quarkus.command.CustomerCommand;
import it.wefox.quarkus.dto.CustomerDto;


import javax.validation.Valid;
import javax.ws.rs.*;
import java.awt.print.Pageable;
import java.util.Optional;

@Valid
@Tag(name = "Customers", description = "Customers API")
@Server(url = "${host.url}", description = "${host.description}")
public interface CustomerApi {

    @POST
    @Operation(summary = "Create new customer")
    CustomerDto create(@Valid CustomerCommand command);

    @PUT
    @Path("/{customerId}")
    @Operation(summary = "Update specific customer")
    Optional<CustomerDto> update(@PathParam("customerId") String customerId, @Valid CustomerCommand command);

    @GET
    @Path("/{customerId}")
    @Operation(summary = "Get specific customer")
    @ApiResponse(responseCode = "200", description = "customer found", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "404", description = "customer not found")
    Optional<CustomerDto> read(@PathParam("customerId") String customerId);

    @DELETE
    @Path("/{customerId}")
    @Operation(summary = "Delete specific customer")
    void delete(@PathParam("customerId") String customerId);

    @GET
    @Path("/{command}")
    @Operation(
            summary = "Get customers paginated",
            parameters = {
                    @Parameter(name = "page_size", description = "maximum number of customers returned per page", in = ParameterIn.QUERY, schema = @Schema(type = "integer", minimum = "0", maximum = "100"), example = "20"),
                    @Parameter(name = "page_number", description = "page number, zero based", in = ParameterIn.QUERY, schema = @Schema(type = "integer", minimum = "0"), example = "0"),
                    @Parameter(name = "sort", description = "sort and order", in = ParameterIn.QUERY, array = @ArraySchema(schema = @Schema(type = "string")), example = "+name")
            }
    )
    Class<? extends CustomerDto> page(@Schema(hidden = true) Pageable command);

}