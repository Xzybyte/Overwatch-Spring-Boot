package overwatch.model.players.stats;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import overwatch.model.players.stats.career.AllHeroes;
import overwatch.model.players.stats.career.heroes.*;

import java.util.List;

@Data
public class Career {

    @JsonProperty("all-heroes")
    private List<AllHeroes> allHeroes;

    @JsonProperty("ana")
    private List<Ana> ana;

    @JsonProperty("ashe")
    private List<Ashe> ashe;

    @JsonProperty("baptiste")
    private List<Baptiste> baptiste;

    @JsonProperty("bastion")
    private List<Bastion> bastion;

    @JsonProperty("brigitte")
    private List<Brigitte> brigitte;

    @JsonProperty("cassidy")
    private List<Cassidy> cassidy;

    @JsonProperty("doomfist")
    private List<Doomfist> doomfist;

    @JsonProperty("dva")
    private List<Dva> dva;

    @JsonProperty("echo")
    private List<Echo> echo;

    @JsonProperty("genji")
    private List<Genji> genji;

    @JsonProperty("hanzo")
    private List<Hanzo> hanzo;

    @JsonProperty("junkrat")
    private List<Junkrat> junkrat;

    @JsonProperty("lucio")
    private List<Lucio> lucio;

    @JsonProperty("mei")
    private List<Mei> mei;

    @JsonProperty("mercy")
    private List<Mercy> mercy;

    @JsonProperty("moira")
    private List<Moira> moira;

    @JsonProperty("orisa")
    private List<Orisa> orisa;

    @JsonProperty("pharah")
    private List<Pharah> pharah;

    @JsonProperty("reaper")
    private List<Reaper> reaper;

    @JsonProperty("reinhardt")
    private List<Reinhardt> reinhardt;

    @JsonProperty("roadhog")
    private List<Roadhog> roadhog;

    @JsonProperty("sigma")
    private List<Sigma> sigma;

    @JsonProperty("soldier-76")
    private List<Soldier76> soldier76;

    @JsonProperty("sombra")
    private List<Sombra> sombra;

    @JsonProperty("symmetra")
    private List<Symmetra> symmetra;

    @JsonProperty("torbjorn")
    private List<Torbjorn> torbjorn;

    @JsonProperty("tracer")
    private List<Tracer> tracer;

    @JsonProperty("widowmaker")
    private List<Widowmaker> widowmaker;

    @JsonProperty("winston")
    private List<Winston> winston;

    @JsonProperty("wrecking-ball")
    private List<WreckingBall> wreckingball;

    @JsonProperty("zarya")
    private List<Zarya> zarya;

    @JsonProperty("zenyatta")
    private List<Zenyatta> zenyatta;

    public int[] getSizes() {
        return new int[]{ana != null ? ana.size() : 0,
                ashe != null ? ashe.size() : 0,
                baptiste != null ? baptiste.size() : 0,
                bastion != null ? bastion.size() : 0,
                brigitte != null ? brigitte.size() : 0,
                cassidy != null ? cassidy.size() : 0,
                dva != null ? dva.size() : 0,
                doomfist != null ? doomfist.size() : 0,
                echo != null ? echo.size() : 0,
                genji != null ? genji.size() : 0,
                hanzo != null ? hanzo.size() : 0,
                junkrat != null ? junkrat.size() : 0,
                lucio != null ? lucio.size() : 0,
                mei != null ? mei.size() : 0,
                mercy != null ? mercy.size() : 0,
                moira != null ? moira.size() : 0,
                orisa != null ? orisa.size() : 0,
                pharah != null ? pharah.size() : 0,
                reaper != null ? reaper.size() : 0,
                reinhardt != null ? reinhardt.size() : 0,
                roadhog != null ? roadhog.size() : 0,
                sigma != null ? sigma.size() : 0,
                soldier76 != null ? soldier76.size() : 0,
                sombra != null ? sombra.size() : 0,
                symmetra != null ? symmetra.size() : 0,
                torbjorn != null ? torbjorn.size() : 0,
                tracer != null ? tracer.size() : 0,
                widowmaker != null ? widowmaker.size() : 0,
                winston != null ? winston.size() : 0,
                wreckingball != null ? wreckingball.size() : 0,
                zarya != null ? zarya.size() : 0,
                zenyatta != null ? zenyatta.size() : 0,
        };
    }
}
