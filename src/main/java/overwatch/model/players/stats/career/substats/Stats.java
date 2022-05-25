package overwatch.model.players.stats.career.substats;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Stats {

    @JsonProperty("key")
    private String key;

    @JsonProperty("label")
    private String label;

    @JsonProperty("value")
    private Integer value;

}
