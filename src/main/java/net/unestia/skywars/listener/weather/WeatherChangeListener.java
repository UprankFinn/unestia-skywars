package net.unestia.skywars.listener.weather;

import net.unestia.skywars.SkyWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

/**
 * @author: Uprank
 * @date: 30.12.2020
 */

public class WeatherChangeListener implements Listener {

    private final SkyWars skyWars;

    public WeatherChangeListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onWeatherChangeEvent(WeatherChangeEvent event) {
        event.setCancelled(true);
    }


}
