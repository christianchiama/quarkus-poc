package it.wefox.quarkus.petservice.domain;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 08/12/22
 * @Time: 02:40
 */
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(
        database = "pet-service",
        collection="species")
public class Species extends ReactivePanacheMongoEntityBase {

    @BsonProperty("id")
    @BsonId
    private ObjectId id;

    @NotNull(message = "species name can't be null or empty")
    @Size(min = 3, message = "species name should have at least 3 characters")
    @Column(unique = true)
    private String name;


}