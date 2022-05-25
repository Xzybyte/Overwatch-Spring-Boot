package overwatch.model.players.summary.endorsement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Endorsement {

    @JsonProperty("level")
    private Integer level;

    @JsonProperty("frame")
    private String frame;

    @JsonProperty("distribution")
    private Distribution distribution;

}
