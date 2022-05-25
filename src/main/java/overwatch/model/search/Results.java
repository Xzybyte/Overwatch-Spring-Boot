package overwatch.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.core.io.ClassPathResource;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

@Data
@Log4j2
public class Results {

    @JsonProperty("player_id")
    private String playerId;

    @JsonProperty("name")
    private String name;

    @JsonProperty("level")
    private Integer level;

    @JsonProperty("platform")
    private String platform;

    @JsonProperty("portrait")
    private String portrait;

    @JsonProperty("privacy")
    private String privacy;

    @JsonProperty("career_url")
    private String careerUrl;

    private String portraitUrl = null;

    public String getPortraitUrl() {
        if (portraitUrl != null) {
            return portraitUrl;
        }
        JSONParser jsonParser = new JSONParser();
        InputStream classPathResource;
        try {
            classPathResource = new ClassPathResource("static/json/icons.json").getInputStream();
        } catch (IOException e) {
            log.error(e.getMessage());
            return "https://d15f34w2p8l1cc.cloudfront.net/overwatch/c3090e3a1dccc58f143ff53801bc0cecb139f0eb1278f157d0b5e29db9104bed.png";
        }
        Object obj;
        try {
            obj = jsonParser.parse(new InputStreamReader(classPathResource, StandardCharsets.UTF_8));
        } catch (IOException | ParseException e) {
            log.error(e.getMessage());
            return "https://d15f34w2p8l1cc.cloudfront.net/overwatch/c3090e3a1dccc58f143ff53801bc0cecb139f0eb1278f157d0b5e29db9104bed.png";
        }

        JSONArray iconList = (JSONArray) obj;
        JSONObject iconObj = (JSONObject) iconList.get(0);
        JSONObject portraitObj = (JSONObject) iconObj.get(portrait);
        portraitUrl = (String) portraitObj.get("icon");

        return portraitUrl;
    }

}
