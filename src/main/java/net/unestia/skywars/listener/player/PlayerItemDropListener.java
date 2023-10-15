package net.unestia.skywars.listener.player;

import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;

/**
 * @author: Uprank
 * @date: 30.12.2020
 */

public class PlayerItemDropListener implements Listener {

    private final SkyWars skyWars;

    public PlayerItemDropListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onPlayerItemDropEvent(PlayerDropItemEvent event){
        if (this.skyWars.getUnestiaAPI().getGameState() == GameState.INGAME && this.skyWars.getUnestiaAPI().getPlayers().contains(event.getPlayer())) {
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
        }

    }


}
