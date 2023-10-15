package net.unestia.skywars.listener.player;

import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.text.MessageFormat;

/**
 * @author: Uprank
 * @date: 30.12.2020
 */

public class PlayerJoinListener implements Listener {

    private final SkyWars skyWars;

    public PlayerJoinListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onPlayerJoinEvent(PlayerJoinEvent event) {

        event.setJoinMessage(null);

        Bukkit.getOnlinePlayers().forEach(players -> {
            this.skyWars.getTabList().setPlayerTabList(players, "\n§a§lUnestia §7§lNetwork\n", "\n§7Website §8» §aunestia.net\n§7Store §8» §astore.unestia.net\n§7TeamSpeak §8» §ats.unestia.net\n");
        });

        net.unestia.api.player.Player unestiaPlayer = this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(event.getPlayer().getName());

        if (this.skyWars.getUnestiaAPI().getGameState() == GameState.LOBBY) {

            event.getPlayer().getInventory().setArmorContents(null);
            event.getPlayer().getInventory().clear();
            event.getPlayer().setGameMode(GameMode.ADVENTURE);
            event.getPlayer().setHealthScale(20);
            event.getPlayer().setFoodLevel(20);
            event.getPlayer().getActivePotionEffects().clear();


            Bukkit.getOnlinePlayers().forEach(players -> {
                players.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(players.getName()).getLanguage().getTranslations().get("join_message"), SkyWars.PREFIX, event.getPlayer().getName()));
            });

            if (this.skyWars.getUnestiaAPI().getTeamManager().getTeam(event.getPlayer()) != null) {
                this.skyWars.getUnestiaAPI().getTeamManager().getTeam(event.getPlayer()).removePlayer(event.getPlayer());
            }

            this.skyWars.getCountdownType().getLobbyCountdown().checkCountdown(Bukkit.getOnlinePlayers().size());
            this.skyWars.getPlayerInventory().setPlayerItems(event.getPlayer());
            this.skyWars.getScoreboard().setScoreboard(event.getPlayer());

            if (event.getPlayer().hasPermission("permission.skywars.kit.all")) {
                //this.skyWars.getSkyWarsPlayerManager().createSkyWarsPlayer(new SkyWarsPlayer(event.getPlayer().getUniqueId(), event.getPlayer().getName(), new SkyWarsPlayer.Kits(true, true, true, true, true, true)));
            } else {
                //this.skyWars.getSkyWarsPlayerManager().createSkyWarsPlayer(new SkyWarsPlayer(event.getPlayer().getUniqueId(), event.getPlayer().getName(), new SkyWarsPlayer.Kits(false, false, false, false, false, false)));
            }
            for (Player players : Bukkit.getOnlinePlayers()) {
                event.getPlayer().hidePlayer(this.skyWars, players);
                event.getPlayer().showPlayer(this.skyWars, players);
            }

            event.getPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation());

        } else if (this.skyWars.getUnestiaAPI().getGameState() == GameState.INGAME || this.skyWars.getUnestiaAPI().getGameState() == GameState.ENDING || this.skyWars.getUnestiaAPI().getGameState() == GameState.PROTECTION) {
            this.skyWars.setSpectator(event.getPlayer());
            for (Player player : this.skyWars.getSpectator()) {
                Bukkit.getOnlinePlayers().forEach(players -> {
                    player.hidePlayer(this.skyWars, players);
                    player.showPlayer(this.skyWars, players);
                });
                this.skyWars.getScoreboard().setScoreboard(player);
            }
            event.getPlayer().getInventory().setContents(this.skyWars.getSpectatorInventory().getInventory().getContents());
        }
    }


}
