package overwatch.controllers;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import overwatch.model.heroes.Hero;
import overwatch.model.heroes.Heroes;
import overwatch.model.maps.GameModes;
import overwatch.model.maps.Maps;
import overwatch.model.players.Player;
import overwatch.model.players.summary.Platform;
import overwatch.model.search.Search;

import java.util.*;

@Controller
@Log4j2
public class OverwatchController {

    /**
     * Maps and player searching currently disabled.
     */


    /**
     * Rest template
     */
    private RestTemplate restTemplate;

    /**
     * Heroes loaded from the API
     */
    private Heroes[] heroes;

    /**
     * Maps loaded from the API
     */
    private Maps[] maps;

    /**
     * Gamemodes loaded from the API
     */
    private GameModes[] gameModes;

    /**
     * Heroes looked up by the users visiting the website
     */
    private final List<Hero> heroList = new ArrayList<>();

    /**
     * API Url
     */
    private final String url = "http://owapi.xzybyte.ca";

    /**
     * Load heroes, maps, and game modes
     */
    @EventListener(ApplicationReadyEvent.class)
    public void loadData() {
        restTemplate = new RestTemplate();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_ARRAY_AS_NULL_OBJECT, true);

        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setObjectMapper(objectMapper);

        restTemplate.getMessageConverters().add(0, converter);

        ResponseEntity<Heroes[]> heroesEntity;
        ResponseEntity<Maps[]> mapsEntity;
        ResponseEntity<GameModes[]> modesEntity;
        try {
            heroesEntity = restTemplate.getForEntity(url + "/heroes", Heroes[].class);
           // mapsEntity =  restTemplate.getForEntity(url + "/maps", Maps[].class);
           // modesEntity =  restTemplate.getForEntity(url + "/maps/gamemodes", GameModes[].class);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        heroes = heroesEntity.getBody();
      //  maps = mapsEntity.getBody();
      //  gameModes = modesEntity.getBody();
    }

    /**
     *
     * @return Index page
     */
    @GetMapping("/")
    public ModelAndView getIndex() {
        return new ModelAndView("index");
    }

    /**
     *
     * @return Hero page
     */
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

    /**
     *
     * @param name - Name of hero
     * @return Hero page
     */
    @GetMapping("/heroes/{name}")
    public ModelAndView getHero(@PathVariable String name) {
        ModelAndView mv = new ModelAndView("heroes");

        Hero hero = heroList.stream().filter(h -> h.getName().toLowerCase().equals(name)).findFirst().orElse(null);
        if (hero == null) {
            ResponseEntity<Hero> heroEntity;
            try {
                heroEntity = restTemplate.getForEntity(url + "/heroes/" + name, Hero.class);
            } catch (Exception e) {
                log.error(e.getMessage());
                return new ModelAndView("error");
            }
            hero = heroEntity.getBody();
            heroList.add(hero);
        }
        Heroes h = Arrays.stream(heroes).filter(her -> her.getKey().equals(name)).findFirst().orElse(null);
        mv.addObject("heroImage", h != null ? h.getPortrait() : "");
        mv.addObject("hero", hero);
        mv.addObject("videos", Objects.requireNonNull(hero).getMedias());
      //  mv.addObject("images", Objects.requireNonNull(hero).getMedias().stream().filter(m -> m.getType().equals("image")).toList());
      //  mv.addObject("pdfs", Objects.requireNonNull(hero).getMedias().stream().filter(m -> m.getType().equals("pdf")).toList());
        return mv;
    }

    /**
     *
     * @return Map page
     */
    @GetMapping("/maps")
    public ModelAndView getMaps() {
        ModelAndView mv = new ModelAndView("maps");
        mv.addObject("mapsList", Arrays.stream(maps).toList());
        mv.addObject("modes", Arrays.stream(gameModes).toList());
        return mv;
    }

    /**
     *
     * @param player - Player to search
     * @return Search page
     */
    @PostMapping("/search")
    public ModelAndView searchPlayer(@RequestParam String player) {
        ModelAndView mv = new ModelAndView("search");

        ResponseEntity<Search> searchEntity;
        try {
            searchEntity = restTemplate.getForEntity(url + "/players?name=" + player, Search.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ModelAndView("error");
        }

        mv.addObject("searchTerm", player);
        mv.addObject("total", Objects.requireNonNull(searchEntity.getBody()).getTotal());
        mv.addObject("results", Objects.requireNonNull(searchEntity.getBody()).getResults());
        return mv;
    }

    /**
     *
     * @param platform - Platform of player
     * @param id - ID of player
     * @return Player page
     */
    @GetMapping("/players/{platform}/{id}")
    public ModelAndView getPlayer(@PathVariable String platform, @PathVariable String id) {

        try {
            Platform.valueOf(platform);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ModelAndView("error");
        }

        ModelAndView mv = new ModelAndView("players");

        ResponseEntity<Player> playerEntity;
        try {
            playerEntity = restTemplate.getForEntity(url + "/players/" + platform + "/" + id, Player.class);
        } catch (Exception e) {
            log.error(e.getMessage());
            return new ModelAndView("error");
        }

        Player player = playerEntity.getBody();

        if (player == null) {
            return new ModelAndView("error");
        }
        mv.addObject("player", player);
        mv.addObject("heroes", Arrays.stream(heroes).toList());
        return mv;
    }

}
