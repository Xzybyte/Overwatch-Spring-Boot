package overwatch.model.heroes;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Heroes {

    @JsonProperty("key")
    private String key;

    @JsonProperty("name")
    private String name;

    @JsonProperty("portrait")
    private String portrait;

    @JsonProperty("role")
    private String role;

}
