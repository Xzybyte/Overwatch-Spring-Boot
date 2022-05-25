package overwatch.model.players.summary.level;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Level {

    @JsonProperty("value")
    private Integer value;

    @JsonProperty("border")
    private String border;

    @JsonProperty("rank")
    private String rank;

}
