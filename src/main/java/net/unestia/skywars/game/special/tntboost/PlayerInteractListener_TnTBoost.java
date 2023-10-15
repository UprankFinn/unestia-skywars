package net.unestia.skywars.game.special.tntboost;

import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

import java.time.LocalDate;

/**
 * @author: Uprank
 * @date: 09.01.2021
 */

public class PlayerInteractListener_TnTBoost implements Listener {

    private final SkyWars skyWars;

    public PlayerInteractListener_TnTBoost(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (!(event.getItem().hasItemMeta())) return;
        if (event.getItem().getItemMeta().getDisplayName() == null) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) return;
        Player player = event.getPlayer();

        if (this.skyWars.getUnestiaAPI().getGameState() == GameState.INGAME) {
            if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §7tnt boost") && event.getMaterial() == Material.EXPLOSIVE_MINECART) {
                player.sendMessage(SkyWars.PREFIX + "§7You placed a tnt boost!");
                event.setCancelled(true);
                removeItemsFromPlayer(player.getInventory(), Material.EXPLOSIVE_MINECART, 1);
                Location plate = player.getLocation().subtract(0.0, 1.0, 0.0);
                Location tnt1 = player.getLocation(plate).subtract(1.0, 1.0, 0.0);
                if (player.getLocation().subtract(0.0, 1.0, 0.0).getBlock().getType() == Material.AIR) {
                    player.getLocation().subtract(0.0, 0.0, 0.0).getBlock().setType(Material.WOOD_PLATE);
                }
            }
        }
    }

    private boolean removeItemsFromPlayer(PlayerInventory inventory, Material material, int amount) {
        boolean b = false;
        ItemStack[] itemStack = inventory.getContents();
        int n = itemStack.length;
        int n2 = 0;
        while (n2 < n) {
            ItemStack is = itemStack[n2];
            if (is != null && is.getType() == material) {
                b = true;
                int newamount = is.getAmount() - amount;
                if (newamount > 0) {
                    is.setAmount(newamount);
                    break;
                }
                inventory.remove(is);
                amount = -newamount;
                if (amount == 0) break;
            }
            ++n2;
        }
        return b;
    }

}
