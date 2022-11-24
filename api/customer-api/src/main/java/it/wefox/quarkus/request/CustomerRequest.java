package it.wefox.quarkus.request;

/**
 * @author Christian Chiama
 * --
 */
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Schema(description = "customer create or update data")
public interface CustomerRequest {

    @NotNull
    @NotBlank
    @Schema(description = "name of the customer")
    String getName();

    @NotNull
    @NotBlank
    @Schema(description = "address of the customer")
    String getAddress();

    @NotNull
    @Schema(description = "birth date of the customer", type = "string", format = "date")
    LocalDate getBirthDate();

}
