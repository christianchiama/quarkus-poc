package it.wefox.quarkus.customerservice.domain;

/**
 * @author: Christian Chiama
 * @project: quarkus-poc
 * @Date: 06/12/22
 * @Time: 20:18
 */
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntity;
import io.quarkus.mongodb.panache.reactive.ReactivePanacheMongoEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
import it.wefox.quarkus.petapi.request.PetRequest;
import lombok.*;
import org.bson.BsonType;
import org.bson.codecs.pojo.annotations.*;
import org.bson.types.ObjectId;

import javax.json.bind.annotation.JsonbTransient;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(database = "customer-service", collection = "customer")
public class Customer extends ReactivePanacheMongoEntityBase {

    @BsonId
    @BsonProperty("id")
    private ObjectId id;

    private String name;

    private String address;

    private LocalDate birthDate;

    private List<? extends PetRequest> pets;


    public Customer(String substring) {
    }
}
