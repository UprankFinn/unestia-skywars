package net.unestia.skywars.inventory.lobby;

import lombok.Getter;
import net.unestia.skywars.SkyWars;
import net.unestia.skywars.storage.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * @author: Uprank
 * @date: 30.12.2020
 */

public class PlayerInventory {

    @Getter
    private SkyWars skyWars;

    @Getter
    private final Inventory inventory;

    public PlayerInventory(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.inventory = Bukkit.createInventory(null, 36);
        initializeInventory();
    }

    public void setPlayerItems(Player player) {
        if (player.hasPermission("permission.skywars.vip")) {
            this.inventory.setItem(4, new ItemBuilder(Material.REDSTONE_COMPARATOR, 1, (byte) 0).setDisplayName("§8» §4Settings").build());
        }
        player.getInventory().setContents(getInventory().getContents());
    }

    private void initializeInventory() {

        this.inventory.setItem(0, new ItemBuilder(Material.CHEST, 1, (byte) 3).setDisplayName("§8» §7Kits").build());

        this.inventory.setItem(1, new ItemBuilder(Material.BED, 1, (byte) 0).setDisplayName("§8» §aTeams").build());

        this.inventory.setItem(8, new ItemBuilder(Material.MAGMA_CREAM, 1, (byte) 0).setDisplayName("§8» §cLobby").build());

    }

}
