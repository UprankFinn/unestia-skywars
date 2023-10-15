package net.unestia.skywars.listener.inventory;

import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

/**
 * @author: Uprank
 * @date: 10.01.2021
 */

public class InventoryOpenListener implements Listener {

    private final SkyWars skyWars;

    public InventoryOpenListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onInventoryOpenEvent(InventoryOpenEvent event) {
        Player player = (Player) event.getPlayer();
        if (this.skyWars.getUnestiaAPI().getGameState() == GameState.INGAME && this.skyWars.getSpectator().contains(player) && event.getInventory().getType() == InventoryType.CHEST) {
            event.setCancelled(true);
        }

    }

}
