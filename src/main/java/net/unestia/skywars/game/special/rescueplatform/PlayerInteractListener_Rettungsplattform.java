package net.unestia.skywars.game.special.rescueplatform;

import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

/**
 * @author: Uprank
 * @date: 02.01.2021
 */

public class PlayerInteractListener_Rettungsplattform implements Listener {

    private final SkyWars skyWars;

    public PlayerInteractListener_Rettungsplattform(SkyWars skyWars) {
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

            if ((event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK) && event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §7Rescue platform") && event.getMaterial() == Material.BLAZE_ROD) {
                player.sendMessage(SkyWars.PREFIX + "§7You placed a Rescue platform!");
                event.setCancelled(true);
                removeItemsFromPlayer(player.getInventory(), Material.BLAZE_ROD, 1);
                Location location1 = player.getLocation().subtract(0.0, 5.0, 0.0);
                Location location2 = player.getLocation().subtract(0.0, 5.0, 1.0);
                Location location3 = player.getLocation().subtract(1.0, 5.0, 0.0);
                Location location4 = player.getLocation().subtract(0.0, 5.0, 0.0).add(0.0, 0.0, 1.0);
                Location location5 = player.getLocation().subtract(0.0, 5.0, 0.0).add(1.0, 0.0, 0.0);

                if (location1.getBlock().getType() == Material.AIR) {
                    location1.getBlock().setType(Material.SLIME_BLOCK);
                }
                if (location2.getBlock().getType() == Material.AIR) {
                    location2.getBlock().setType(Material.SLIME_BLOCK);
                }
                if (location3.getBlock().getType() == Material.AIR) {
                    location3.getBlock().setType(Material.SLIME_BLOCK);
                }
                if (location4.getBlock().getType() == Material.AIR) {
                    location4.getBlock().setType(Material.SLIME_BLOCK);
                }
                if (location5.getBlock().getType() == Material.AIR) {
                    location5.getBlock().setType(Material.SLIME_BLOCK);
                }

                Bukkit.getScheduler().scheduleSyncDelayedTask(this.skyWars, new Runnable() {
                    @Override
                    public void run() {
                        if (location1.getBlock().getType() == Material.SLIME_BLOCK) {
                            location1.getBlock().setType(Material.AIR);
                        }
                        if (location2.getBlock().getType() == Material.SLIME_BLOCK) {
                            location2.getBlock().setType(Material.AIR);
                        }
                        if (location3.getBlock().getType() == Material.SLIME_BLOCK) {
                            location3.getBlock().setType(Material.AIR);
                        }
                        if (location4.getBlock().getType() == Material.SLIME_BLOCK) {
                            location4.getBlock().setType(Material.AIR);
                        }
                        if (location5.getBlock().getType() == Material.SLIME_BLOCK) {
                            location5.getBlock().setType(Material.AIR);
                        }
                    }
                }, 100);

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
