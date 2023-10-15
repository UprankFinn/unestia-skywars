package net.unestia.skywars;

import com.google.gson.Gson;
import lombok.Getter;
import lombok.SneakyThrows;
import net.unestia.api.UnestiaAPI;
import net.unestia.api.game.GameState;
import net.unestia.api.world.World;
import net.unestia.skywars.command.*;
import net.unestia.skywars.countdown.CountdownType;
import net.unestia.skywars.game.chestloot.ChestLootManager;
import net.unestia.skywars.game.special.rescueplatform.PlayerInteractListener_Rettungsplattform;
import net.unestia.skywars.game.special.tntboost.PlayerInteractListener_TnTBoost;
import net.unestia.skywars.game.kit.KitManager;
import net.unestia.skywars.inventory.lobby.*;
import net.unestia.skywars.inventory.spec.*;
import net.unestia.skywars.listener.block.*;
import net.unestia.skywars.listener.craft.*;
import net.unestia.skywars.listener.creature.*;
import net.unestia.skywars.listener.entity.*;
import net.unestia.skywars.listener.inventory.*;
import net.unestia.skywars.listener.player.*;
import net.unestia.skywars.listener.server.*;
import net.unestia.skywars.listener.team.*;
import net.unestia.skywars.listener.weather.*;
import net.unestia.skywars.storage.scoreboard.*;
import net.unestia.skywars.storage.tablist.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.block.*;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @author: Uprank
 * @date: 30.12.2020
 */

public class SkyWars extends JavaPlugin {

    @Getter
    private UnestiaAPI unestiaAPI;

    @Getter
    private final List<Player> spectator = new ArrayList<>();

    public static final String PREFIX = "§8» §aSkyWars §8❘ §r";

    @Getter
    private Gson gson;

    @Getter
    private String template;

    @Getter
    private Scoreboard scoreboard;

    // =============================================================================================================== //

    @Getter
    private CountdownType countdownType;

    // =============================================================================================================== //

    @Getter
    private InventoryClickListener inventoryClickListener;

    // =============================================================================================================== //

    @Getter
    private KitInventory kitInventory;
    @Getter
    private PlayerInventory playerInventory;
    @Getter
    private SettingsInventory settingsInventory;
    @Getter
    private WorldsInventory worldsInventory;

    @Getter
    private SpectatorInventory spectatorInventory;
    @Getter
    private TeleporterInventory teleporterInventory;

    // =============================================================================================================== //

    @Getter
    public KitManager kitManager;

    @Getter
    private ChestLootManager chestLootManager;

    @Getter
    private TabList tabList;

    // =============================================================================================================== //

    @Getter
    private static SkyWars instance;

    public SkyWars() {
        this.gson = new Gson();
    }

    @Override
    @SneakyThrows
    public void onEnable() {
        instance = this;
        File[] dependencies = new File("/home/minecraft/cloud/dependency/").listFiles();
        for (File dependency : dependencies) {
            Method method = URLClassLoader.class.getDeclaredMethod("addURL", URL.class);
            method.setAccessible(true);
            method.invoke(Thread.currentThread().getContextClassLoader(), dependency.toURI().toURL());
        }

        this.unestiaAPI = UnestiaAPI.getInstance();
        this.unestiaAPI.setCloudServer(true);
        this.unestiaAPI.setGroupName("skywars");
        this.unestiaAPI.setTemplateName("null");
        this.unestiaAPI.initialize();

        this.template = this.unestiaAPI.getTemplateName();

        this.tabList = new TabList();

        this.scoreboard = new Scoreboard(this);
        this.countdownType = new CountdownType(this);

        initializeGame();

        /*if (this.getTemplate().equalsIgnoreCase("2x2") || this.getTemplate().equalsIgnoreCase("2x1")) {
            this.unestiaAPI.getTeamManager().createTeams(2);
        } else if (this.getTemplate().equalsIgnoreCase("4x1") || this.getTemplate().equalsIgnoreCase("4x2")) {
            this.unestiaAPI.getTeamManager().createTeams(4);
        } else if (this.getTemplate().equalsIgnoreCase("8x1") || this.getTemplate().equalsIgnoreCase("8x2")) {
            this.unestiaAPI.getTeamManager().createTeams(8);
        }*/

        World world = this.getUnestiaAPI().getWorldManager().getWorld("arena");
        this.unestiaAPI.setWorld(world.getType(), world.getName());

        for (net.unestia.api.world.World world1 : this.unestiaAPI.getWorldManager().getWorlds()) {
            for (Chunk chunk : Bukkit.getWorld(world1.getName()).getLoadedChunks()) {
                int blockX = chunk.getX() << 4;
                int blockZ = chunk.getZ() << 4;
                for (int chunkX = blockX; chunkX < blockX + 16; chunkX++) {
                    for (int chunkZ = blockZ; chunkZ < blockZ + 16; chunkZ++) {
                        for (int chunkY = 0; chunkY < 128; chunkY++) {
                            Block block = Bukkit.getWorld(world1.getName()).getBlockAt(chunkX, chunkY, chunkZ);
                            if (block.getType().equals(Material.CHEST)) {
                                Chest chest = (Chest) block.getState();
                                chest.getInventory().clear();
                                for (int i = 0; i < this.random(10, 13); ++i) {
                                    chest.getInventory().setItem(this.random(0, chest.getInventory().getSize() - 1), this.chestLootManager.getItemStack().get(new Random().nextInt(this.chestLootManager.getItemStack().size())));
                                }
                            }
                        }
                    }
                }
            }
        }

        initializeCommands();
        initializeListener();
        initializeInventory();

    }

    @Override
    public void onDisable() {

        /*Bukkit.getOnlinePlayers().forEach(players -> {
            players.kickPlayer(MessageFormat.format(this.getUnestiaAPI().getPlayerManager().getPlayer(players.getUniqueId()).getLanguage().getTranslations().get("restart_message"), SkyWars.PREFIX));
        });*/

    }

    private void initializeCommands() {

        ForcemapCommand forcemapCommand = new ForcemapCommand(this);
        StartCommand startCommand = new StartCommand(this);

    }

    private void initializeListener() {

        BlockBreakListener blockBreakListener = new BlockBreakListener(this);
        BlockPlaceListener blockPlaceListener = new BlockPlaceListener(this);

        // =============================================================================================================== //

        CraftItemListener craftItemListener = new CraftItemListener(this);

        // =============================================================================================================== //

        CreatureSpawnListener creatureSpawnListener = new CreatureSpawnListener(this);

        // =============================================================================================================== //

        EntityDamageByBlockListener entityDamageByBlockListener = new EntityDamageByBlockListener(this);
        EntityDamageByEntityListener entityDamageByEntityListener = new EntityDamageByEntityListener(this);
        EntityDamageListener entityDamageListener = new EntityDamageListener(this);

        // =============================================================================================================== //

        this.inventoryClickListener = new InventoryClickListener(this);
        InventoryOpenListener inventoryOpenListener = new InventoryOpenListener(this);

        // =============================================================================================================== //

        FoodLevelChangeListener foodLevelChangeListener = new FoodLevelChangeListener(this);
        PlayerDeathListener playerDeathListener = new PlayerDeathListener(this);
        PlayerInteractListener playerInteractListener = new PlayerInteractListener(this);
        PlayerItemDropListener playerItemDropListener = new PlayerItemDropListener(this);
        PlayerJoinListener playerJoinListener = new PlayerJoinListener(this);
        PlayerLoginListener playerLoginListener = new PlayerLoginListener(this);
        PlayerMoveListener playerMoveListener = new PlayerMoveListener(this);
        PlayerPickUpItemListener playerPickUpItemListener = new PlayerPickUpItemListener(this);
        PlayerQuitListener playerQuitListener = new PlayerQuitListener(this);
        PlayerRespawnListener playerRespawnListener = new PlayerRespawnListener(this);

        // =============================================================================================================== //

        ServerListPingListener serverListPingListener = new ServerListPingListener(this);

        // =============================================================================================================== //

        TeamWinningListener teamWinningListener = new TeamWinningListener(this);

        // =============================================================================================================== //

        WeatherChangeListener weatherChangeListener = new WeatherChangeListener(this);

        // =============================================================================================================== //

        PlayerInteractListener_Rettungsplattform playerInteractListener_rettungsplattform = new PlayerInteractListener_Rettungsplattform(this);
        PlayerInteractListener_TnTBoost playerInteractListener_tnTBoost = new PlayerInteractListener_TnTBoost(this);

    }

    private void initializeInventory() {
        this.kitInventory = new KitInventory(this);
        this.playerInventory = new PlayerInventory(this);
        this.settingsInventory = new SettingsInventory(this);
        this.worldsInventory = new WorldsInventory(this);

        this.spectatorInventory = new SpectatorInventory();
        this.teleporterInventory = new TeleporterInventory(this);
    }

    private void initializeGame() {

        String[] templateId = template.split("x");
        if (templateId[0].equals("4")) {
            this.unestiaAPI.getTeamManager().createTeam("blue", ChatColor.BLUE, true, Integer.parseInt(templateId[1]), new ArrayList<>());
            this.unestiaAPI.getTeamManager().createTeam("red", ChatColor.RED, true, Integer.parseInt(templateId[1]), new ArrayList<>());
            this.unestiaAPI.getTeamManager().createTeam("green", ChatColor.GREEN, true, Integer.parseInt(templateId[1]), new ArrayList<>());
            this.unestiaAPI.getTeamManager().createTeam("yellow", ChatColor.YELLOW, true, Integer.parseInt(templateId[1]), new ArrayList<>());

        } else if (templateId[0].equals("8")) {
            this.unestiaAPI.getTeamManager().createTeam("blue", ChatColor.BLUE, true, Integer.parseInt(templateId[1]), new ArrayList<>());
            this.unestiaAPI.getTeamManager().createTeam("red", ChatColor.RED, true, Integer.parseInt(templateId[1]), new ArrayList<>());
            this.unestiaAPI.getTeamManager().createTeam("green", ChatColor.GREEN, true, Integer.parseInt(templateId[1]), new ArrayList<>());
            this.unestiaAPI.getTeamManager().createTeam("yellow", ChatColor.YELLOW, true, Integer.parseInt(templateId[1]), new ArrayList<>());
            this.unestiaAPI.getTeamManager().createTeam("orange", ChatColor.GOLD, true, Integer.parseInt(templateId[1]), new ArrayList<>());
            this.unestiaAPI.getTeamManager().createTeam("black", ChatColor.BLACK, true, Integer.parseInt(templateId[1]), new ArrayList<>());
            this.unestiaAPI.getTeamManager().createTeam("pink", ChatColor.LIGHT_PURPLE, true, Integer.parseInt(templateId[1]), new ArrayList<>());
            this.unestiaAPI.getTeamManager().createTeam("gray", ChatColor.GRAY, true, Integer.parseInt(templateId[1]), new ArrayList<>());

        } else if (templateId[0].equals("2")) {
            this.unestiaAPI.getTeamManager().createTeam("blue", ChatColor.BLUE, true, Integer.parseInt(templateId[1]), new ArrayList<>());
            this.unestiaAPI.getTeamManager().createTeam("red", ChatColor.RED, true, Integer.parseInt(templateId[1]), new ArrayList<>());
        }

        this.getUnestiaAPI().setGameState(GameState.LOBBY);

        this.chestLootManager = new ChestLootManager(this);
        this.kitManager = new KitManager();
        this.chestLootManager.initialize();
        this.kitManager.registerKits();

    }

    public void setSpectator(Player player) {
        player.setAllowFlight(true);
        if (!this.getSpectator().contains(player)) {
            for (Player alive : this.getUnestiaAPI().getPlayers()) {
                alive.hidePlayer(this, player);
            }
            this.getSpectator().add(player);

            //((CraftServer) Bukkit.getServer()).getServer().getPlayerList().moveToWorld(((CraftPlayer) player).getHandle(), 0, false);
        }
    }

    public int random(int minimum, int maximum) {
        Random random = new Random();
        return random.nextInt(maximum - minimum + 1) + minimum;
    }

    public void startChangeBoarderTask() {
        Bukkit.getOnlinePlayers().forEach(players -> {
            players.sendMessage(MessageFormat.format(this.getUnestiaAPI().getPlayerManager().getPlayer(players.getName()).getLanguage().getTranslations().get("pvp_boarder_change_message"), SkyWars.PREFIX));
        });
        //this.world.getWorldBorder().setSize(this.world.getConfig().getJsonElement().getAsJsonObject().get("border").getAsJsonObject().get("minimum").getAsInt(), 500L);
    }

}
