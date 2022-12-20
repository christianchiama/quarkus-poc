package it.wefox.quarkus.petapi.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.codecs.pojo.annotations.BsonId;
import org.bson.codecs.pojo.annotations.BsonProperty;
import org.bson.types.ObjectId;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author Christian Chiama
 * --
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "species create or update data")
public class SpeciesRequest {

    private ObjectId id;

    @NotNull(message = "species name can't be null or empty")
    @Size(min = 3, message = "species name should have at least 3 characters")
    @Schema(description = "name of the species")
    private String name;
}
