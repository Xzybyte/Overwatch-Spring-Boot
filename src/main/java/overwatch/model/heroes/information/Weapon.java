package overwatch.model.heroes.information;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Weapon {

    @JsonProperty("icon")
    private String icon;

    @JsonProperty("primary_fire")
    private PrimaryFire primaryFire;

    @JsonProperty("secondary_fire")
    private SecondaryFire secondaryFire;

}
