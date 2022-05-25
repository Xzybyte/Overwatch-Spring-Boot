package overwatch.model.players.quickplay;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import overwatch.model.players.stats.Career;
import overwatch.model.players.stats.HeroComparisons;

@Data
public class QuickPlay {

    @JsonProperty("heroes_comparisons")
    private HeroComparisons heroComparisons;

    @JsonProperty("career_stats")
    private Career career;

}
