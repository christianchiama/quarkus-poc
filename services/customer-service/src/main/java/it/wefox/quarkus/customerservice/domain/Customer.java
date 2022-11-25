package it.wefox.quarkus.customerservice.domain;

/**
 * @author Christian Chiama
 * --
 */

import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;


@Getter
@Setter
@ToString
@AllArgsConstructor
@MongoEntity(database = "customer-service", collection="customer")
@RegisterForReflection
public class Customer extends ReactivePanacheMongoEntityBase {

    @BsonProperty("id")
    @BsonId
    private ObjectId id;

    @NotNull
    @NotBlank
    private String name;

    @NotNull
    @NotBlank
    private String address;

    @NotNull
    @BsonProperty("birthDate")
    private LocalDate birthDate;

    public Customer() {}
}

