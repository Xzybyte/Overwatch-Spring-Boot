package overwatch.model.heroes.information;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Story {

    @JsonProperty("biography")
    private Biography biography;

    @JsonProperty("catch_phrase")
    private String catchPhrase;

    @JsonProperty("back_story")
    private String backStory;

}
