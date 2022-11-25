package it.wefox.quarkus.domain;

/**
 * @author Christian Chiama
 * --
 */
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@ToString
@NoArgsConstructor
@MongoEntity(
        database = "pet-service",
        collection="species")
public class Species extends ReactivePanacheMongoEntityBase {

    @BsonProperty("id")
    @BsonId
    private ObjectId id;

    @NotNull
    @NotBlank
    @Column(unique = true)
    private String name;



}