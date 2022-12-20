package it.wefox.quarkus.treatmentservice.domain;

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import it.wefox.quarkus.treatmentapi.request.TreatmentResult;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.types.ObjectId;


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

    @BsonId
    private ObjectId id;

    private String name;

    private String petId;

    private LocalDate createdAt;

    private BigDecimal price;

    private TreatmentResult result;


}
