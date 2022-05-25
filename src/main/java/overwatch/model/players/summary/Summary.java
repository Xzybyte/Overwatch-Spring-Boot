package overwatch.model.players.summary;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import overwatch.model.players.summary.endorsement.Endorsement;
import overwatch.model.players.summary.level.Level;
import overwatch.model.players.summary.ranked.CompetitiveRoles;

import java.util.List;

@Data
public class Summary {

    @JsonProperty("username")
    private String username;

    @JsonProperty("avatar")
    private String avatar;

    @JsonProperty("level")
    private Level level;

    @JsonProperty("competitive")
    private CompetitiveRoles competitiveRoles;

    @JsonProperty("endorsement")
    private Endorsement endorsement;

    @JsonProperty("games_won")
    private Integer gamesWon;

    @JsonProperty("platforms")
    private List<Platform> platform;

    @JsonProperty("privacy")
    private String privacy;

}
