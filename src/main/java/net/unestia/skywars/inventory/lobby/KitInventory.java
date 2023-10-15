package net.unestia.skywars.inventory.lobby;

import lombok.Getter;
import net.unestia.skywars.SkyWars;
import net.unestia.skywars.storage.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

/**
 * @author: Uprank
 * @date: 31.12.2020
 */

public class KitInventory {

    private final SkyWars skyWars;

    @Getter
    private final Inventory inventory = Bukkit.createInventory(null, 27, "§8» §7Kits");

    public KitInventory(SkyWars skyWars) {
        this.skyWars = skyWars;
        initializeInventory();
    }

    public void initializeInventory() {

        this.skyWars.kitManager.getKits().forEach((integer, kit) -> {
            this.inventory.setItem(integer, new ItemBuilder(kit.getItemStack()).setDisplayName("§8» §7" + kit.getName()).setLore(kit.getLore()).build());
        });

    }

}
