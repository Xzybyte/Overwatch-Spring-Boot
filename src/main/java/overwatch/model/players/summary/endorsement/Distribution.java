package overwatch.model.players.summary.endorsement;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Distribution {

    @JsonProperty("shotcaller")
    private Double shotcaller;

    @JsonProperty("teammate")
    private Double teammate;

    @JsonProperty("sportsmanship")
    private Double sportsmanship;

}
