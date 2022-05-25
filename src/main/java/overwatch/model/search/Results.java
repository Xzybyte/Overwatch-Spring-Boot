package overwatch.model.search;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

@Data
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
        InputStream classPathResource = null;
        try {
            classPathResource = new ClassPathResource("static/json/icons.json").getInputStream();
        } catch (IOException e) {
            return "";
        }
        Object obj = null;
        try {
            obj = jsonParser.parse(new InputStreamReader(classPathResource, StandardCharsets.UTF_8));
        } catch (IOException | ParseException e) {
            return "";
        }

        JSONArray iconList = (JSONArray) obj;
            JSONObject iconObj = (JSONObject) iconList.get(0);
            JSONObject portraitObj = (JSONObject) iconObj.get(portrait);
            portraitUrl = (String) portraitObj.get("icon");

        return portraitUrl;
    }

}
