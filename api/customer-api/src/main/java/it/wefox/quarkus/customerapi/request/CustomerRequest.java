package it.wefox.quarkus.customerapi.request;

import io.quarkus.runtime.annotations.RegisterForReflection;
import it.wefox.quarkus.petapi.request.PetRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.media.Schema;
import org.hibernate.annotations.Parameter;
import org.jboss.logging.annotations.Field;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
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
@Schema(name = "CustomerRequest", description = "customer data")
public class CustomerRequest {

    @Schema(description = "Id of the customer")
    private ObjectId id;

    @NotNull(message = "customer name can't be null or empty")
    @Schema(description = "Name of the customer")
    @Size(min = 3, message = "customer name should have at least 3 characters")
    private String name;

    @NotNull(message = "customer address can't be null or empty")
    @Schema(description = "Address of the customer")
    private String address;

    @NotNull(message = "customer birthdate can't be null or empty")
    @Schema(description = "Birthdate of the customer")
    private LocalDate birthDate;


    private List<? extends PetRequest> pets;


    public CustomerRequest(String name, String address, LocalDate birthDate) {
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
    }
}
