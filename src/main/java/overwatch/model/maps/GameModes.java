package overwatch.model.maps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class GameModes {

    @JsonProperty("key")
    private String key;

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

}
