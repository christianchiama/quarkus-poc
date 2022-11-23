package it.wefox.quarkus.dto;

/**
 * @author Christian Chiama
 * --
 */
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

@Schema(name = "Customer", description = "customer data")
public interface CustomerDto {

    @Schema(description = "ID of the customer")
    String getId();

    @Schema(description = "name of the customer")
    String getName();

    @Schema(description = "address of the customer")
    String getAddress();

    @Schema(description = "birth date of the customer", type = "string", format = "date")
    LocalDate getBirthDate();

}
