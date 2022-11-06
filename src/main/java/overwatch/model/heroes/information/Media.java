package overwatch.model.heroes.information;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Media {

    @JsonProperty("type")
    private String type;

    @JsonProperty("link")
    private String link;

    public String getEmbedLink() {
        link += "?origin=http://localhost";
        return type.equals("video") ? link.replace("https://youtu.be/", "https://www.youtube.com/embed/") : "";
    }

}
