package overwatch.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import overwatch.model.heroes.Hero;
import overwatch.model.heroes.Heroes;
import overwatch.model.maps.GameModes;
import overwatch.model.maps.Maps;
import overwatch.model.players.Player;
import overwatch.model.search.Search;

import java.util.*;

@Controller
public class OverwatchController {

    private RestTemplate restTemplate;
    private Heroes[] heroes;
    private Maps[] maps;
    private GameModes[] gameModes;
    private final List<Hero> heroList = new ArrayList<>();
    private final String url = "http://owapi.xzybyte.ca";

    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);

        restTemplate.getMessageConverters().add(0, converter);

        ResponseEntity<Heroes[]> heroesEntity = restTemplate.getForEntity(url + "/heroes", Heroes[].class);
        heroes = heroesEntity.getBody();

        ResponseEntity<Maps[]> mapsEntity =  restTemplate.getForEntity(url + "/maps", Maps[].class);
        maps = mapsEntity.getBody();

        ResponseEntity<GameModes[]> modesEntity =  restTemplate.getForEntity(url + "/maps/gamemodes", GameModes[].class);
        gameModes = modesEntity.getBody();
    }

    @GetMapping("/")
    public ModelAndView getIndex() {
        ModelAndView mv = new ModelAndView("index");
        mv.addObject("mapsList", Arrays.stream(maps).toList());
        return mv;
    }

    @GetMapping("/heroes")
    public ModelAndView getHeroes() {
        ModelAndView mv = new ModelAndView("heroes");
        Map<String, List<Heroes>> role = new HashMap<>();
        Map<String, String> roleDescriptions = new HashMap<>();

        role.put("damage", Arrays.stream(heroes).filter(h -> h.getRole().equals("damage")).toList());
        role.put("tank", Arrays.stream(heroes).filter(h -> h.getRole().equals("tank")).toList());
        role.put("support", Arrays.stream(heroes).filter(h -> h.getRole().equals("support")).toList());

        roleDescriptions.put("damage", "Damage heroes are responsible for seeking out, engaging, and defeating the enemy.");
        roleDescriptions.put("tank", "Tanks specialize in protecting allies, disrupting enemies, and occupying the front line.");
        roleDescriptions.put("support", "The objective of support is to heal, buff and provide utility.");

        mv.addObject("role", role);
        mv.addObject("roleDescriptions", roleDescriptions);
        return mv;
    }

    @GetMapping("/heroes/{name}")
    public ModelAndView getHero(@PathVariable String name) {
        ModelAndView mv = new ModelAndView("heroes");

        Hero hero = heroList.stream().filter(h -> h.getName().toLowerCase().equals(name)).findFirst().orElse(null);
        if (hero == null) {
            ResponseEntity<Hero> heroEntity = restTemplate.getForEntity(url + "/heroes/" + name, Hero.class);
            hero = heroEntity.getBody();
            heroList.add(hero);
        }
        Heroes h = Arrays.stream(heroes).filter(her -> her.getKey().equals(name)).findFirst().orElse(null);
        mv.addObject("heroImage", h != null ? h.getPortrait() : "");
        mv.addObject("hero", hero);
        mv.addObject("videos", Objects.requireNonNull(hero).getMedias().stream().filter(m -> m.getType().equals("video")).toList());
        mv.addObject("images", Objects.requireNonNull(hero).getMedias().stream().filter(m -> m.getType().equals("image")).toList());
        mv.addObject("pdfs", Objects.requireNonNull(hero).getMedias().stream().filter(m -> m.getType().equals("pdf")).toList());
        return mv;
    }

    @GetMapping("/maps")
    public ModelAndView getMaps() {
        ModelAndView mv = new ModelAndView("maps");
        mv.addObject("mapsList", Arrays.stream(maps).toList());
        mv.addObject("modes", Arrays.stream(gameModes).toList());
        return mv;
    }

    @GetMapping("/maps/{name}")
    public ModelAndView getMap(@PathVariable String name) {
        ModelAndView mv = new ModelAndView("maps");

        Maps map = Arrays.stream(maps).filter(m -> m.getName().equals(name.replaceAll("-", " "))).findFirst().orElse(null);
        if (map == null) {
            return mv;
        }
        List<GameModes> modes = new ArrayList<>();
        Arrays.stream(gameModes).toList().forEach(mode -> map.getGameModes().stream().filter(key -> mode.getKey().equals(key)).map(key -> mode).forEach(modes::add));

        mv.addObject("modes", modes);
        mv.addObject("map", map);
        return mv;
    }

    @PostMapping("/search")
    public ModelAndView searchPlayer(@RequestParam String player) {
        ModelAndView mv = new ModelAndView("search");
        ResponseEntity<Search> searchEntity = restTemplate.getForEntity(url + "/players?name=" + player, Search.class);

        mv.addObject("searchTerm", player);
        mv.addObject("total", Objects.requireNonNull(searchEntity.getBody()).getTotal());
        mv.addObject("results", Objects.requireNonNull(searchEntity.getBody()).getResults());
        return mv;
    }

    @GetMapping("/players/{platform}/{id}")
    public ModelAndView getPlayer(@PathVariable String platform, @PathVariable String id) {
        // do battletag empty
        ModelAndView mv = new ModelAndView("players");
        ResponseEntity<Player> playerEntity = restTemplate.getForEntity(url + "/players/" + platform + "/" + id, Player.class);

        Player player = playerEntity.getBody();

        if (player == null) {
            return mv;
        }
        mv.addObject("player", player);
        mv.addObject("heroes", Arrays.stream(heroes).toList());
        return mv;
    }

}
