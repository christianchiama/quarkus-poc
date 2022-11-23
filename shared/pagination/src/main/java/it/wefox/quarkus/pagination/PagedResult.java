package it.wefox.quarkus.pagination;

/**
 * @author Christian Chiama
 * --
 */

import io.swagger.v3.oas.annotations.media.Schema;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@ToString
@Schema(description = "paged result of items")
public class PagedResult<I> {
        @NotNull @Schema(description = "slice of items") @JsonProperty("content") List<I> content;
        @Schema(description = "total number items") @JsonProperty("total_items") long totalElements;
        @Schema(description = "number of page") @JsonProperty("page_number") int pageNumber;
        @Schema(description = "size of page") @JsonProperty("page_size") int pageSize;
        @Schema(description = "total number pages") @JsonProperty("total_pages") int totalPages;
        @Schema(description = "list of sorted properties with relative direction") @JsonProperty("sort") List<String> sort;
    public PagedResult(Page<I> page) {
        this(
                page.getContent(),
                page.getTotalPages(),
                page.getPageable().getPageNumber(),
                page.getPageable().getPageSize(),
                page.getTotalPages(),
                page.getPageable().getSort().stream().map(PagedResult::toSortString)
        );
    }

    public PagedResult(List<I> content, int totalPages, int pageNumber, int pageSize, int totalPages1, Stream<String> stringStream) {
    }

    private static String toSortString(Sort.Order order) {
        var property = order.getProperty();
        var sign = order.isAscending() ? "+" : "-";
        return sign.concat(property);
    }

}
