package net.unestia.skywars.listener.entity;

import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

/**
 * @author: Uprank
 * @date: 30.12.2020
 */

public class EntityDamageByEntityListener implements Listener {

    private final SkyWars skyWars;

    public EntityDamageByEntityListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onEntityDamageByEntityEvent(EntityDamageByEntityEvent entityDamageByEntityEvent) {
        if (!(entityDamageByEntityEvent.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player) entityDamageByEntityEvent.getEntity();
        Player damager = (Player) entityDamageByEntityEvent.getDamager();
        if (this.skyWars.getUnestiaAPI().getGameState() == GameState.INGAME) {
            if (this.skyWars.getUnestiaAPI().getTeamManager().getTeam(player).getPlayers().contains(damager.getPlayer())) {
                entityDamageByEntityEvent.setCancelled(true);
            } else {
                entityDamageByEntityEvent.setCancelled(this.skyWars.getSpectator().contains(damager));
            }
        }
    }
}
