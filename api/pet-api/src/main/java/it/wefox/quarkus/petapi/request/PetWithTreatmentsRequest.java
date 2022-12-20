package it.wefox.quarkus.petapi.request;

import it.wefox.quarkus.treatmentapi.request.TreatmentRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 13/12/22
 * @Time: 14:54
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "pet mongodb or dto with treatments")
public class PetWithTreatmentsRequest {

    private ObjectId id;

    @NotNull(message = "pet name can't be null or empty")
    @Size(min = 3, message = "pet name should have at least 3 characters")
    @Schema(description = "name of the pet")
    String name;

    @NotNull(message = "pet birthdate can't be null or empty")
    @Schema(description = "birth date of the pet", format = "date")
    LocalDate birthDate;

    @NotNull(message = "species can't be null or empty")
    @Schema(description = "species of the pet")
    SpeciesRequest species;

    @NotNull(message = "owner id name can't be null or empty")
    @Schema(description = "the id of the owner of the pet")
    String ownerId;

    List<TreatmentRequest> treatments;
}
