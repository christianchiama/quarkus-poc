package it.wefox.quarkus.treatmentapi.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Christian Chiama
 * --
 */
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TreatmentRequest {

    @BsonId
    private ObjectId id;

    @NotNull(message = "treatment name can't be null or empty")
    @Size(min = 3, message = "treatment name should have at least 3 characters")
    private String name;

    @NotNull(message = "treatment petId can't be null or empty")
    private String petId;

    @NotNull(message = "treatment createdAt can't be null or empty")
    private LocalDate createdAt;

    @NotNull(message = "treatment price can't be null or empty")
    private BigDecimal price;

    @NotNull(message = "treatment result can't be null")
    private TreatmentResult result;


}
