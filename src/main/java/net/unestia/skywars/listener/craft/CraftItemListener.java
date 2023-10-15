package net.unestia.skywars.listener.craft;

import net.unestia.skywars.SkyWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.CraftItemEvent;

/**
 * @author: Uprank
 * @date: 05.01.2021
 */

public class CraftItemListener implements Listener {

    private final SkyWars skyWars;

    public CraftItemListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onCraftItemEvent(CraftItemEvent event) {



    }

}
