package it.wefox.quarkus.domain;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import io.smallrye.common.constraint.*;
import lombok.*;


import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author Christian Chiama
 * --
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(
        database = "treatment-service",
        collection = "treatment")
public class Treatment extends ReactivePanacheMongoEntityBase {

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String petId;

    private LocalDate treatedAt;

    @NotNull
    @Min(0)
    private BigDecimal price;


}
