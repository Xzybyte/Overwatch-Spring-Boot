package overwatch.model.maps;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class Maps {

    @JsonProperty("name")
    private String name;

    @JsonProperty("gamemodes")
    private List<String> gameModes;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("flag")
    private String flag;
    
}
