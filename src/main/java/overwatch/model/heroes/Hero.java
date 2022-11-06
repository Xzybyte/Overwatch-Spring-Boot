package overwatch.model.heroes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import overwatch.model.heroes.information.Ability;
import overwatch.model.heroes.information.Media;

import java.util.List;

@Data
public class Hero {

    @JsonProperty("name")
    private String name;

    @JsonProperty("role")
    private String role;

    @JsonProperty("location")
    private String location;

    @JsonProperty("description")
    private String description;

    @JsonProperty("abilities")
    private List<Ability> abilities = null;

    @JsonProperty("story")
    private String story;

    @JsonProperty("media")
    private Media medias = null;

}
