package overwatch.model.players.achievements.category;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Maps {

    @JsonProperty("title")
    private String title;

    @JsonProperty("description")
    private String description;

    @JsonProperty("image")
    private String image;

}
