package overwatch.model.players.achievements;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import overwatch.model.players.achievements.category.*;

import java.util.List;

@Data
public class Achievements {

    @JsonProperty("general")
    private List<General> general;

    @JsonProperty("damage")
    private List<Damage> damage;

    @JsonProperty("tank")
    private List<Tank> tank;

    @JsonProperty("support")
    private List<Support> support;

    @JsonProperty("maps")
    private List<Maps> maps;

    @JsonProperty("events")
    private List<Events> events;


}
