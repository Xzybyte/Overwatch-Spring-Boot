package overwatch.model.players.stats.comparisons;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ComparisonValues {

    @JsonProperty("hero")
    private String hero;

    @JsonProperty("value")
    private Integer value;
}
