package overwatch.model.players.summary.ranked.roles;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Support {

    @JsonProperty("role_icon")
    private String roleIcon;

    @JsonProperty("skill_rating")
    private Integer skillRating;

    @JsonProperty("tier_icon")
    private String tierIcon;

}
