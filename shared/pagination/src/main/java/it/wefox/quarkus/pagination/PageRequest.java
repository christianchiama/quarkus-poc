package it.wefox.quarkus.pagination;

/**
 * @author Christian Chiama
 * --
 */

import lombok.*;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.QueryParam;

@ToString
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Schema(description = "paged result of items")
public class PageRequest {
    @QueryParam("pageNum")
    @DefaultValue("0")
    private int pageNum;

    @QueryParam("pageSize")
    @DefaultValue("10")
    private int pageSize;
}
