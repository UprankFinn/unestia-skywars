package net.unestia.skywars.listener.entity;

import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.util.Vector;

/**
 * @author: Uprank
 * @date: 30.12.2020
 */

public class EntityDamageByBlockListener implements Listener {

    private final SkyWars skyWars;

    public EntityDamageByBlockListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onEntityDamageByBlockEvent(EntityDamageByBlockEvent event) {
        if (!(event.getDamager() instanceof Player)) {
            return;
        }
        Player player = (Player) event.getEntity();
        Player damager = (Player) event.getDamager();
        if (this.skyWars.getUnestiaAPI().getGameState() == GameState.INGAME) {
            if (this.skyWars.getUnestiaAPI().getTeamManager().getTeam(player).getPlayers().contains(damager.getPlayer())) {
                event.setCancelled(true);
            } else {
                event.setCancelled(this.skyWars.getSpectator().contains(damager));
            }
            if (!(event.getDamager() instanceof TNTPrimed)) {
                event.getEntity().setVelocity(new Vector(0.0, 10298, 0.0));
            }

        }
    }
}
