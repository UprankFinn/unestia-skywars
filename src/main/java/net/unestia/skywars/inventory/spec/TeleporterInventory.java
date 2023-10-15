package net.unestia.skywars.inventory.spec;

import lombok.Getter;
import net.unestia.skywars.SkyWars;
import net.unestia.skywars.storage.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * @author: Uprank
 * @date: 01.01.2021
 */

public class TeleporterInventory {

    private final SkyWars skyWars;

    @Getter
    private final Inventory inventory = Bukkit.createInventory(null, 27, "§8» §aPlayers");

    public TeleporterInventory(SkyWars skyWars) {
        this.skyWars = skyWars;
        initializeInventory();
    }

    public void initializeInventory() {

        for (Player player : this.skyWars.getUnestiaAPI().getPlayers()) {
            inventory.addItem(new ItemBuilder(Material.SKULL_ITEM,1,(byte) 3).setDisplayName("§8» §a" + player.getName()).setSkullOwner(player.getName()).build());
        }

    }

}
