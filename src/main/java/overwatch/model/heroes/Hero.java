package overwatch.model.heroes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import overwatch.model.heroes.information.Ability;
import overwatch.model.heroes.information.Media;
import overwatch.model.heroes.information.Story;
import overwatch.model.heroes.information.Weapon;

import java.util.List;

@Data
public class Hero {

    @JsonProperty("name")
    private String name;

    @JsonProperty("role")
    private String role;

    @JsonProperty("difficulty")
    private Integer difficulty;

    @JsonProperty("description")
    private String description;

    @JsonProperty("weapons")
    private List<Weapon> weapons = null;

    @JsonProperty("abilities")
    private List<Ability> abilities = null;

    @JsonProperty("story")
    private Story story;

    @JsonProperty("medias")
    private List<Media> medias = null;

}
