package it.wefox.quarkus.dto;

/**
 * @author Christian Chiama
 * --
 */
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
public class CustomerResult implements CustomerDto {

    private final String id;
    private final String name;
    private final String address;
    private final LocalDate birthDate;

    @JsonCreator
    public CustomerResult(@JsonProperty("id") String id, @JsonProperty("name") String name, @JsonProperty("address") String address, @JsonProperty("birth_date") LocalDate birthDate) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.birthDate = birthDate;
    }

    public CustomerResult(CustomerDto customer) {
        this(customer.getId(), customer.getName(), customer.getAddress(), customer.getBirthDate());
    }

}

