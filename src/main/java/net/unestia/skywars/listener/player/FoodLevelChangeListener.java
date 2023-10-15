package net.unestia.skywars.listener.player;

import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

/**
 * @author: Uprank
 * @date: 31.12.2020
 */

public class FoodLevelChangeListener implements Listener {

    private final SkyWars skyWars;

    public FoodLevelChangeListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onFoodLevelChangeEvent(FoodLevelChangeEvent event) {

        if (this.skyWars.getUnestiaAPI().getGameState() == GameState.INGAME) {
            if (this.skyWars.getSpectator().contains((Player) event.getEntity())) {
                event.setCancelled(true);
            } else {
                event.setCancelled(false);
            }
        } else {
            event.setCancelled(true);
        }

    }

}
