package net.unestia.skywars.game.kit.type;

import net.unestia.skywars.game.kit.Kit;
import net.unestia.skywars.storage.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * @author: Uprank
 * @date: 01.01.2021
 */

public class DefaultKit extends Kit {

    public DefaultKit() {
        super("Default",  Arrays.asList("§8§m------------------------§r", "§cAusrüstung:", "§71x IronSword", "§71x IronPickaxe", "§71x IronAxe"),0, new ItemStack(Material.IRON_PICKAXE));
    }

    @Override
    public Inventory getContent() {
        Inventory inventory = Bukkit.createInventory(null, 36);
        inventory.setItem(0, new ItemBuilder(Material.IRON_SWORD, 1, (byte) 0).build());
        inventory.setItem(1, new ItemBuilder(Material.IRON_PICKAXE, 1, (byte) 0).build());
        inventory.setItem(2, new ItemBuilder(Material.IRON_AXE, 1, (byte) 0).build());
        return inventory;
    }

    @Override
    public ItemStack[] getArmorContent() {
        return null;
    }

}
