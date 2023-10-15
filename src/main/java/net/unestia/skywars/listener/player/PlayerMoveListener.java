package net.unestia.skywars.listener.player;

import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

/**
 * @author: Uprank
 * @date: 15.01.2021
 */

public class PlayerMoveListener implements Listener {

    private final SkyWars skyWars;

    public PlayerMoveListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onPlayerMoveEvent(PlayerMoveEvent event) {
        if (this.skyWars.getUnestiaAPI().getGameState() == GameState.LOBBY || this.skyWars.getUnestiaAPI().getGameState() == GameState.ENDING) {
            if (event.getPlayer().getLocation().getY() < 1) {
                event.getPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation());
            }
        }
    }

}
