package net.unestia.skywars.listener.server;

import net.unestia.skywars.SkyWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

/**
 * @author: Uprank
 * @date: 31.12.2020
 */

public class ServerListPingListener implements Listener {

    private final SkyWars skyWars;

    public ServerListPingListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onServerListPingEvent(ServerListPingEvent event) {
        event.setMotd(this.skyWars.getUnestiaAPI().getWorld().getName().substring(0, 1).toUpperCase() + this.skyWars.getUnestiaAPI().getWorld().getName().substring(1));
    }

}
