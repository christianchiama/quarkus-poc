package it.wefox.quarkus.petapi.request;

import io.smallrye.common.constraint.NotNull;
import io.swagger.v3.oas.annotations.media.Schema;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * @author Christian Chiama
 * --
 */
@Schema(description = "pet create or update data")
public class PetRequest {

    @NotNull
    @NotBlank
    @Schema(description = "name of the pet")
    String name;

    @NotNull
    @Schema(description = "birth date of the pet", type = "string", format = "date")
    LocalDate birthDate;

    @NotNull
    @NotBlank
    @Schema(description = "ID of the species of the pet")
    String speciesId;

    @NotNull
    @NotBlank
    @Schema(description = "the customer ID of the owner of the pet")
    String ownerId;

}
