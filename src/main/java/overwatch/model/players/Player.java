package overwatch.model.players;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import overwatch.model.players.achievements.Achievements;
import overwatch.model.players.quickplay.QuickPlay;
import overwatch.model.players.ranked.Competitive;
import overwatch.model.players.summary.Summary;

@Data
public class Player {

    @JsonProperty("summary")
    private Summary summary;

    @JsonProperty("quickplay")
    private QuickPlay quickPlay;

    @JsonProperty("competitive")
    private Competitive competitive;

    @JsonProperty("achievements")
    private Achievements achievements;

}
