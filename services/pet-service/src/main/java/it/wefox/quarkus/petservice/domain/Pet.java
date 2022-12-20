package it.wefox.quarkus.petservice.domain;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 07/12/22
 * @Time: 14:19
 */

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import it.wefox.quarkus.treatmentapi.request.TreatmentRequest;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(
        database = "pet-service",
        collection = "pet")
public class Pet extends ReactivePanacheMongoEntityBase {

    @BsonProperty("id")
    @BsonId
    private ObjectId id;

    private String name;

    private LocalDate birthDate;

    private Species species;

    private String ownerId;

}