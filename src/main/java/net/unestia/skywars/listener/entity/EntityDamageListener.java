package net.unestia.skywars.listener.entity;

import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * @author: Uprank
 * @date: 30.12.2020
 */

public class EntityDamageListener implements Listener {

    private final SkyWars skyWars;

    public EntityDamageListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onEntityDamageEvent(EntityDamageEvent entityDamageEvent) {
        if (!(this.skyWars.getUnestiaAPI().getGameState() == GameState.INGAME)) {
            entityDamageEvent.setCancelled(true);
        } else {
            Player player = (Player) entityDamageEvent.getEntity();
            if (this.skyWars.getSpectator().contains(player)) {
                entityDamageEvent.setCancelled(true);
            }
        }
    }
}
