package overwatch.model.players.stats.career.heroes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import overwatch.model.players.stats.career.substats.Stats;

import java.util.List;

@Data
public class Soldier76 {

    @JsonProperty("category")
    private String category;

    @JsonProperty("label")
    private String label;

    @JsonProperty("stats")
    private List<Stats> stats;

}
