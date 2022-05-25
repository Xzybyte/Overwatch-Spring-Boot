package overwatch.model.heroes.information;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Biography {

    @JsonProperty("real_name")
    private String realName;

    @JsonProperty("age")
    private String age;

    @JsonProperty("occupation")
    private String occupation;

    @JsonProperty("base_of_operations")
    private String baseOfOperations;

    @JsonProperty("affiliation")
    private String affiliation;

}
