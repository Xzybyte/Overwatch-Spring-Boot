package overwatch.model.players.summary;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Platform {

    @JsonProperty("pc")
    PC,
    @JsonProperty("psn")
    PSN,
    @JsonProperty("xbl")
    XBL,
    @JsonProperty("nintendo-switch")
    NINTENDO_SWITCH

}
