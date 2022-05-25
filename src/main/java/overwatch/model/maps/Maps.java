package overwatch.model.maps;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

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
