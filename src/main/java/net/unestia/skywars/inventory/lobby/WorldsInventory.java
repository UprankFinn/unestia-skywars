package net.unestia.skywars.inventory.lobby;

import lombok.Getter;
import net.unestia.api.world.World;
import net.unestia.skywars.SkyWars;
import net.unestia.skywars.storage.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import java.util.Arrays;

/**
 * @author: Uprank
 * @date: 31.12.2020
 */

public class WorldsInventory {

    private final SkyWars skyWars;

    @Getter
    private final Inventory inventory = Bukkit.createInventory(null, 27, "§8» §9Worlds");

    public WorldsInventory(SkyWars skyWars) {
        this.skyWars = skyWars;
        initializeInventory();
    }

    public void initializeInventory() {

        for (World world : this.skyWars.getUnestiaAPI().getWorldManager().getWorlds()) {
            inventory.addItem(new ItemBuilder(Material.PAPER, 1, (byte) 0).setDisplayName("§8» §9" + world.getName()).setLore(Arrays.asList("§c", "§8» §7Rating§8: §cnull", "§b")).build());

        }

    }

}
