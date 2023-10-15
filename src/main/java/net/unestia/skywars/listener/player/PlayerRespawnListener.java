package net.unestia.skywars.listener.player;

import net.unestia.skywars.SkyWars;
import net.unestia.skywars.inventory.spec.SpectatorInventory;
import net.unestia.skywars.storage.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerRespawnEvent;

/**
 * @author: Uprank
 * @date: 02.01.2021
 */

public class PlayerRespawnListener implements Listener {

    private final SkyWars skyWars;

    public PlayerRespawnListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent playerRespawnEvent) {

        this.skyWars.setSpectator(playerRespawnEvent.getPlayer());

        /**/
    }
}
