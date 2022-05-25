package overwatch.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Search {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("results")
    private List<Results> results;

}
