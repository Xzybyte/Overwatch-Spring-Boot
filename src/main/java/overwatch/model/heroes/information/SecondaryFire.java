package overwatch.model.heroes.information;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class SecondaryFire {

    @JsonProperty("name")
    private String name;

    @JsonProperty("description")
    private String description;

}
