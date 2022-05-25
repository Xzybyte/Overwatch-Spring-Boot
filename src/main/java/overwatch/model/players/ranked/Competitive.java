package overwatch.model.players.ranked;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import overwatch.model.players.stats.Career;
import overwatch.model.players.stats.HeroComparisons;

@Data
public class Competitive {

    @JsonProperty("heroes_comparisons")
    private HeroComparisons heroStats;

    @JsonProperty("career_stats")
    private Career career;

}
