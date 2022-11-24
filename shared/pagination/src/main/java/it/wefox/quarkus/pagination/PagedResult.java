package it.wefox.quarkus.pagination;

/**
 * @author Christian Chiama
 * --
 */

import io.quarkus.panache.common.Page;
import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ToString
@Schema(description = "paged result of items")
public class PagedResult<I> {

}
