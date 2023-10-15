package net.unestia.skywars.listener.player;

import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;

import java.text.MessageFormat;

/**
 * @author: Uprank
 * @date: 31.12.2020
 */

public class PlayerLoginListener implements Listener {

    private final SkyWars skyWars;

    public PlayerLoginListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onPlayerPreLoginEvent(PlayerLoginEvent event) {
        if (this.skyWars.getUnestiaAPI().getGameState() == GameState.PROTECTION || this.skyWars.getUnestiaAPI().getGameState() == GameState.ENDING) {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(event.getPlayer().getName()).getLanguage().getTranslations().get("loginevent"), SkyWars.PREFIX));
        }
    }

}
