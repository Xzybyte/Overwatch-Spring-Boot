package overwatch.model.players.stats.comparisons;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class CriticalHitAccuracy {

    @JsonProperty("label")
    private String label;

    @JsonProperty("values")
    private List<ComparisonValues> values;

}
