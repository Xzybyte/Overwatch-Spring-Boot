package overwatch.model.players.summary.ranked;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import overwatch.model.players.summary.ranked.roles.Damage;
import overwatch.model.players.summary.ranked.roles.Support;
import overwatch.model.players.summary.ranked.roles.Tank;

@Data
public class CompetitiveRoles {

    @JsonProperty("tank")
    private Tank tank;

    @JsonProperty("damage")
    private Damage damage;

    @JsonProperty("support")
    private Support support;

}
