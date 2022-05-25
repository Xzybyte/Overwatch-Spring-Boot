document.addEventListener('DOMContentLoaded', () => {
    if (document.getElementsByClassName("data-container").item(0) != null) {
        changePlayerNav("profile"); // Load default profile view
    }
});

window.onclick = function(event) {
    if (event.target === document.getElementById("maps-modal")) {
        document.getElementById("maps-modal").style.display = "none";
    }
}

let currentNav = ""; // Current navigation variable (Profile, Heroes, Achievements)
let currentView = "default"; // Current view of hero (Heroes)
let currentModeView = "qp"; // Current mode view in Heroes tab (Quickplay, Competitive)
let quickplay; // Quickplay div
let competitive; // Competitive div
let playerObj; // Player stats
let heroesObj; // Heroes information
let loadDefault = false;

let gameModesObj;
let mapsObj;
/***
 * @param player Object of player stats
 * @param heroes Object of hero stats
 * @description Save the player and heroes object
 */
function saveObjects(player, heroes) {
    playerObj = player;
    heroesObj = heroes;
}

function saveMapObjects(modes, maps) {
    gameModesObj = modes;
    mapsObj = maps;
}
/***
 * @param nav
 */
function changePlayerNav(nav) {
    if (nav === currentNav) {
        return;
    } else if (currentNav.length > 0 && nav !== currentNav) {
        const navElement = document.getElementById("player-nav-" + currentNav);
        navElement.style.backgroundColor = "";
        document.getElementById("player-" + currentNav).innerHTML = "";
    }
    currentNav = nav;
    const navElement = document.getElementById("player-nav-" + currentNav);
    navElement.style.backgroundColor = "#ffffff";
    switch (nav) {
        case "profile":
            displayProfile();
            break;
        case "heroes":
            displayHeroes();
            break;
        case "achievements":
            displayAchievements();
            break;
    }
}

function displayProfile() {
    const profile = document.getElementById("player-profile");

    const title = document.getElementById("player-container-title");
    title.innerHTML = "SKILL RATINGS";
    const competitiveInfo = playerObj["summary"]["competitive"];

    let tiers = ["/img/unranked-icon.webp", "/img/unranked-icon.webp", "/img/unranked-icon.webp"];
    let skillRatings = [0, 0, 0];
    let icons = ["https://static.playoverwatch.com/img/pages/career/icon-offense-6267addd52.png", "https://static.playoverwatch.com/img/pages/career/icon-support-46311a4210.png", "https://static.playoverwatch.com/img/pages/career/icon-tank-8a52daaf01.png"];
    let roles = ["damage", "support", "tank"];

    if (competitiveInfo != null) {
        const damage = competitiveInfo["damage"];
        const support = competitiveInfo["support"];
        const tank = competitiveInfo["tank"];
        if (damage != null) {
            icons[0] = damage["role_icon"];
            skillRatings[0] = damage["skill_rating"];
            tiers[0] = damage["tier_icon"];
        }
        if (support != null) {
            icons[1] = support["role_icon"];
            skillRatings[1] = support["skill_rating"];
            tiers[1] = support["tier_icon"];
        }
        if (tank != null) {
            icons[2] = tank["role_icon"];
            skillRatings[2] = tank["skill_rating"];
            tiers[2] = tank["tier_icon"];
        }
    }

    for (const [index, element] of roles.entries()) {
        const titleSpan = document.createElement("span");
        titleSpan.setAttribute("id", element + "-title");
        titleSpan.innerHTML = element.toUpperCase();

        const roleDiv = document.createElement("div");
        roleDiv.setAttribute("id", element + "-rank");

        const skillSpan = document.createElement("span");
        skillSpan.setAttribute("id", element + "-rating");
        skillSpan.innerHTML += skillRatings[index] === 0 ? "Unranked" : skillRatings[index];

        const tierImg = document.createElement("img");
        tierImg.setAttribute("id", element + "-tier");
        tierImg.setAttribute("width", "128px");
        tierImg.setAttribute("src", tiers[index]);
        tierImg.setAttribute("alt", element + "-tier");

        const iconImg = document.createElement("img");
        iconImg.setAttribute("id", element + "-icon");
        iconImg.setAttribute("src", icons[index]);
        iconImg.setAttribute("alt", element + "-icon");

        roleDiv.appendChild(titleSpan);
        roleDiv.appendChild(tierImg);
        roleDiv.appendChild(iconImg);
        roleDiv.innerHTML += "<br>";
        roleDiv.appendChild(skillSpan);
        profile.appendChild(roleDiv);
    }
}

function displayHeroes() {
    const ul = document.createElement("ul");

    const title = document.getElementById("player-container-title");
    title.innerHTML = "HERO INFORMATION";

    const quickplayLi = document.createElement("li");
    quickplayLi.setAttribute("id", "qp");
    quickplayLi.setAttribute("onclick", "changeMode('qp')");
    quickplayLi.innerHTML += "QUICKPLAY";

    const competitiveLi = document.createElement("li");
    competitiveLi.setAttribute("id", "comp");
    competitiveLi.setAttribute("onclick", "changeMode('comp')");
    competitiveLi.innerHTML += "COMPETITIVE";

    quickplay = document.createElement("div");
    quickplay.setAttribute("id", "quickplay");

    competitive = document.createElement("div");
    competitive.setAttribute("id", "competitive");

    ul.appendChild(quickplayLi);
    ul.appendChild(competitiveLi);

    document.getElementById("player-heroes").appendChild(ul);
    document.getElementById("player-heroes").appendChild(quickplay);
    document.getElementById("player-heroes").appendChild(competitive);

    changeMode("qp");
}

function displayAchievements() {
    const title = document.getElementById("player-container-title");
    title.innerHTML = "ACHIEVEMENTS";
    const achievementTypes = ["general", "damage", "tank", "support", "maps", "events"];
    for (let achievement in achievementTypes) {
        let name = achievementTypes[achievement];
        const a = playerObj["achievements"][name];
        if (a != null && a.length > 0) {
            loadAchievementType(name, a);
        }
    }
}

function loadAchievementType(name, type) {
    const div = document.createElement("div");
    div.setAttribute("id", "player-achievements-container");
    const span = document.createElement("span");
    const ul = document.createElement("ul");
    span.innerHTML = name.toUpperCase();
    for (let key in type) {

        const li = document.createElement("li");

        const img = document.createElement("img");
        img.setAttribute("src", type[key]["image"]);
        img.setAttribute("alt", "Achievement Image");

        const achievementDiv = document.createElement("div");
        achievementDiv.setAttribute("id", "achievement-container")

        const achievementTitle = document.createElement("span");
        achievementTitle.setAttribute("id", "achievement-title");
        achievementTitle.innerHTML = type[key]["title"];

        const achievementDescription = document.createElement("span");
        achievementDescription.setAttribute("id", "achievement-description");
        achievementDescription.innerHTML = type[key]["description"];

        achievementDiv.appendChild(achievementTitle);
        achievementDiv.innerHTML += "<br>";
        achievementDiv.appendChild(achievementDescription);

        li.appendChild(img);
        li.appendChild(achievementDiv);
        ul.appendChild(li);
        //console.log(type[key]);
    }
    div.appendChild(span);
    div.appendChild(ul);
    document.getElementById("player-achievements").appendChild(div);
}

function changeMode(mode) {
    // Create modes

    document.getElementById(currentModeView).style.backgroundColor = "";
    if (currentModeView !== mode) {
        loadDefault = true;
    }
    currentModeView = mode;
    document.getElementById(mode).style.backgroundColor = "#ffffff";
    generateViews(mode === "qp" ? playerObj["quickplay"]["career_stats"] : playerObj["competitive"]["career_stats"])
}

function generateViews(stats) {
    if (currentModeView === "qp") {
        competitive.innerHTML = "";
        generateMode(quickplay, stats);
        generateGeneral(quickplay, "all-heroes", stats["all-heroes"]);
    } else if (currentModeView === "comp") {
        quickplay.innerHTML = "";
        generateMode(competitive, stats);
        generateGeneral(competitive, "all-heroes", stats["all-heroes"]);
    }
}

function generateMode(doc, career) {
    const ul = document.createElement("ul");
    ul.setAttribute("id", "hero-categories")
    for (let key in career) {
        if (career.hasOwnProperty(key)) {
            if (!key.includes("sizes")) {
                if (career[key] != null) {
                    const li = document.createElement("li");
                    const name = key.toString();
                    li.setAttribute("id", name + "-cat");
                    li.innerHTML = name.toUpperCase();
                    ul.appendChild(li);
                    li.addEventListener("click", function () {
                        generateGeneral(doc, name, career[key]);
                    });
                }
            }
        }
    }
    doc.appendChild(ul);
    document.getElementById("player-heroes").appendChild(doc);
}

function generateGeneral(doc, name, hero) {
    if (name === currentView && !loadDefault) {
        return;
    }
    loadDefault = false;
    const oldView = document.getElementById(currentView);
    if (oldView != null) {
        document.getElementById(currentView + "-cat").style.backgroundColor = "";
        oldView.remove();
    }
    document.getElementById(name + "-cat").style.backgroundColor = "#ffffff";
    currentView = name;
    const heroDiv = document.createElement("div");
    heroDiv.setAttribute("id", name);

    const heroName = document.createElement("span");
    heroName.setAttribute("id", "hero-name");
    heroName.innerHTML = name.toUpperCase();

    let portrait = getHeroPortrait(name);
    if (portrait != null) {
        const img = document.createElement("img");
        img.setAttribute("src", portrait);
        img.setAttribute("alt", "Hero Portrait Image")
        img.style.backgroundColor = "#ffffff";
        img.style.border = "1px solid #f99e1a";
        heroDiv.appendChild(img);
        heroDiv.innerHTML += "<br>";
    }

    heroDiv.appendChild(heroName);
    doc.appendChild(heroDiv);

    for (let key in hero) {

        const categoryDiv = document.createElement("div");
        categoryDiv.style.paddingTop = "1%";

        const span = document.createElement("span");
        span.setAttribute("id", "category-name");
        span.innerHTML = hero[key]["label"];

        const ul = document.createElement("ul");
        ul.style.border = "2px solid #f99e1a";
        ul.style.backgroundColor = "#d3ced1";

        generateInformation(ul, hero[key]["stats"]);

        categoryDiv.appendChild(span);
        categoryDiv.appendChild(ul);
        heroDiv.appendChild(categoryDiv);
    }
    generateRecord(doc, name);
}

function generateInformation(doc, stats) {
    for (let key in stats) {

        const li = document.createElement("li")

        const label = document.createElement("span");
        label.style.color = "#bb420c";
        label.style.textDecoration = "underline";
        label.innerHTML = stats[key]["label"];
        label.innerHTML += "<br>";

        const value = document.createElement("span");
        value.style.color = "#bb420c";
        value.innerHTML = stats[key]["value"];

        li.appendChild(label);
        li.appendChild(value);

        doc.appendChild(li);
    }
}

function generateRecord(mode, name) {
    if (name === "all-heroes") {
        const records = document.createElement("div");
        records.setAttribute("id", "records")
        records.style.paddingTop = "1%";

        const recordsSpan = document.createElement("span");
        recordsSpan.setAttribute("id", "records");
        recordsSpan.innerHTML = "RECORDS";

        const ul = document.createElement("ul");
        ul.style.border = "2px solid #f99e1a";
        ul.style.backgroundColor = "#d3ced1";

        let obj = playerObj[mode.id]["heroes_comparisons"];
        for (let key in obj) {
            if (obj[key] != null) {
                generateRecordInformation(ul, obj[key]);
            }
        }
        records.appendChild(recordsSpan);
        records.appendChild(ul);
        mode.appendChild(records);
    } else {
        if (document.getElementById("records") != null) {
            document.getElementById("records").remove();
        }
    }
}

function generateRecordInformation(ul, comparison) {
    const li = document.createElement("li");

    const label = document.createElement("span");
    label.innerHTML = comparison["label"];
    label.innerHTML += "<br>";
    label.style.color = "#bb420c";
    label.style.textDecoration = "underline";

    const img = document.createElement("img");
    img.setAttribute("src", getHeroPortrait(comparison["values"][0]["hero"]));
    img.setAttribute("width", "30");
    img.style.backgroundColor = "#ffffff";
    img.style.border = "1px solid #f99e1a";

    const hero = document.createElement("span");
    hero.innerHTML = "<br>";
    hero.innerHTML += comparison["values"][0]["hero"].toUpperCase();
    hero.innerHTML += "<br>";
    hero.style.color = "#bb420c";

    const value = document.createElement("span");
    value.innerHTML = comparison["values"][0]["value"];
    value.innerHTML += "<br>";
    value.style.color = "#bb420c";

    li.appendChild(label);
    li.appendChild(img);
    li.appendChild(hero);
    li.appendChild(value);
    ul.appendChild(li);
}

function getHeroPortrait(name) {
    let portrait;
    for (let key in heroesObj) {
        if (heroesObj[key]["key"] === name) {
            portrait = heroesObj[key]["portrait"];
            break;
        }
    }
    return portrait;
}

function openModal(name) {

    const mapObj = getMapObject(name);
    if (mapObj != null) {
        let mapName = mapObj["name"];
        let thumbnail = mapObj["thumbnail"];
        let flag = mapObj["flag"];
        let gameModes = getGameModes(mapObj["gamemodes"]);
        document.getElementById("modal-name").innerHTML = mapName;
        document.getElementById("modal-flag").setAttribute("src", flag);
        document.getElementById("modal-thumbnail").setAttribute("src", thumbnail);
        const modalGameMode = document.getElementById("modal-gamemodes-descriptions");
        modalGameMode.innerHTML = "";
        for (let [index, mode] of gameModes.entries()) {
            modalGameMode.innerHTML += mode[0];
            modalGameMode.innerHTML += "<br>";
            modalGameMode.innerHTML += mode[1];
            modalGameMode.innerHTML += "<br><br>";
        }
    }

    document.getElementById("maps-modal").style.display = "block";
}

function closeModal() {
    document.getElementById("maps-modal").style.display = "none";
}

function getMapObject(name) {
    let mapObj;
    for (let key in mapsObj) {
        if (mapsObj[key]["name"] === name) {
            mapObj = mapsObj[key];
        }
    }
    return mapObj;
}

function getGameModes(modes) {
    let modeInfo = [];
    for (let key in modes) {

        for (let newKey in gameModesObj) {
            if (gameModesObj[newKey]["key"] === modes[key]) {
                modeInfo.push([gameModesObj[newKey]["name"], gameModesObj[newKey]["description"]]);
            }
        }
       // console.log(modes[key]);
    }
    return modeInfo;
}