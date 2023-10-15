package net.unestia.skywars.listener.player;

import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.text.MessageFormat;

/**
 * @author: Uprank
 * @date: 30.12.2020
 */

public class PlayerQuitListener implements Listener {

    private final SkyWars skyWars;

    public PlayerQuitListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onPlayerQuitEvent(PlayerQuitEvent event) {
        event.setQuitMessage(null);
        if (this.skyWars.getUnestiaAPI().getGameState() == GameState.LOBBY) {
            Bukkit.getOnlinePlayers().forEach(players -> {
                players.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(players.getName()).getLanguage().getTranslations().get("quit_message"), SkyWars.PREFIX, event.getPlayer().getName()));
            });
            this.skyWars.getCountdownType().getLobbyCountdown().checkCountdown(Bukkit.getOnlinePlayers().size() - 1);
            this.skyWars.getUnestiaAPI().getPlayers().remove(event.getPlayer());
        } else if (this.skyWars.getUnestiaAPI().getGameState() == GameState.PROTECTION || this.skyWars.getUnestiaAPI().getGameState() == GameState.INGAME) {

            this.skyWars.setSpectator(event.getPlayer());

            //==========================================================================================

            if(this.skyWars.getUnestiaAPI().getPlayers().contains(event.getPlayer())) {
                Bukkit.getOnlinePlayers().forEach(players -> {
                    players.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(players.getName()).getLanguage().getTranslations().get("death_message"), SkyWars.PREFIX, event.getPlayer().getName()));
                });
            }
            event.getPlayer().getInventory().clear();
            if (this.skyWars.getUnestiaAPI().getPlayers().contains(event.getPlayer())) {
                this.skyWars.getUnestiaAPI().getPlayers().remove(event.getPlayer());
            } else if (this.skyWars.getSpectator().contains(event.getPlayer())) {
                this.skyWars.getSpectator().remove(event.getPlayer());
            }
        }

    }


}
