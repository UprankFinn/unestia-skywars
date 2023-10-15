package net.unestia.skywars.listener.creature;

import net.unestia.skywars.SkyWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

/**
 * @author: Uprank
 * @date: 03.01.2021
 */

public class CreatureSpawnListener implements Listener {

    private final SkyWars skyWars;

    public CreatureSpawnListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onCreatureSpawnEvent(CreatureSpawnEvent event) {
        event.setCancelled(true);
    }

}
