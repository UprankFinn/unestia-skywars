package net.unestia.skywars.listener.player;

import net.unestia.skywars.SkyWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPickupItemEvent;

/**
 * @author: Uprank
 * @date: 03.01.2021
 */

public class PlayerPickUpItemListener implements Listener {

    private final SkyWars skyWars;

    public PlayerPickUpItemListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onPlayerPickUpItemEvent(PlayerPickupItemEvent event) {
        if (this.skyWars.getSpectator().contains(event.getPlayer())) {
            event.setCancelled(true);
        } else {
            event.setCancelled(false);
        }
    }

}
