package overwatch.model.heroes.information;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class Media {

    @JsonProperty("title")
    private String title;

    @JsonProperty("type")
    private String type;

    @JsonProperty("thumbnail")
    private String thumbnail;

    @JsonProperty("link")
    private String link;

    public String getEmbedLink() {
        link += "?origin=http://localhost";
        return type.equals("video") ? link.replace("https://www.youtube.com/watch?v=", "https://www.youtube.com/embed/") : "";
    }

}
