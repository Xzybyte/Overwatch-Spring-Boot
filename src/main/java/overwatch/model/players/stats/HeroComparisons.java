package overwatch.model.players.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import overwatch.model.players.stats.comparisons.*;

@Data
public class HeroComparisons {

    @JsonProperty("time_played")
    private TimePlayed timePlayed;

    @JsonProperty("games_won")
    private GamesWon gamesWon;

    @JsonProperty("weapon_accuracy")
    private WeaponAccuracy weaponAccuracy;

    @JsonProperty("win_percentage")
    private WinPercentage winPercentage;

    @JsonProperty("eliminations_per_life")
    private EliminationsPerLife eliminationsPerLife;

    @JsonProperty("critical_hit_accuracy")
    private CriticalHitAccuracy criticalHitAccuracy;

    @JsonProperty("multikill_best")
    private MultikillBest multikillBest;

    @JsonProperty("objective_kills")
    private ObjectiveKills objectiveKills;

}
