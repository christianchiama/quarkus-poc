package it.wefox.quarkus.customerapi.request;

import com.mongodb.lang.Nullable;
import it.wefox.quarkus.petapi.request.PetRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 05/12/22
 * @Time: 11:31
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Schema(name = "CustomerWithPetsRequest", description = "customer data with pets")
public class CustomerWithPetsRequest {

    @Schema(description = "Id of the customer")
    private ObjectId id;

    @NotNull(message = "customer name can't be null or empty")
    @Schema(description = "Name of the customer")
    private String name;

    @NotNull(message = "customer address can't be null or empty")
    @Schema(description = "Address of the customer")
    private String address;

    @NotNull(message = "customer birthdate can't be null or empty")
    @Schema(description = "Birthdate of the customer")
    private LocalDate birthDate;

    @Nullable
    @Schema(description = "customer pets")
    List<? extends PetRequest> pets;
}
