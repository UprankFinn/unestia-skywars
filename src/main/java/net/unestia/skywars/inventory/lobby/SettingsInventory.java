package net.unestia.skywars.inventory.lobby;

import lombok.Getter;
import net.unestia.skywars.SkyWars;
import net.unestia.skywars.storage.items.Base64;
import net.unestia.skywars.storage.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

/**
 * @author: Uprank
 * @date: 31.12.2020
 */

public class SettingsInventory {

    private final SkyWars skyWars;

    @Getter
    private final Inventory inventory = Bukkit.createInventory(null, 27, "§8» §4Settings");

    public SettingsInventory(SkyWars skyWars) {
        this.skyWars = skyWars;
        initializeInventory();
    }

    public void initializeInventory() {
        for (int i = 0; i < 27; ++i) {
            inventory.setItem(i, new ItemBuilder(Material.STAINED_GLASS_PANE,1,(byte) 15).build());
        }
        inventory.setItem(11, new ItemBuilder(Material.REDSTONE_TORCH_ON).setDisplayName("§8» §cForceStart").build());

        inventory.setItem(15, new ItemBuilder(Base64.getSkull("http://textures.minecraft.net/texture/1289d5b178626ea23d0b0c3d2df5c085e8375056bf685b5ed5bb477fe8472d94")).setDisplayName("§8» §cForceMap").build());

    }

}
