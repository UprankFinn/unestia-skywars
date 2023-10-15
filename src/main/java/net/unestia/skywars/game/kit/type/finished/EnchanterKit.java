package net.unestia.skywars.game.kit.type.finished;

import net.unestia.skywars.game.kit.Kit;
import net.unestia.skywars.storage.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * @author: Uprank
 * @date: 05.01.2021
 */

public class EnchanterKit extends Kit {

    public EnchanterKit() {
        super("Enchanter", Arrays.asList(""), 50000, new ItemStack(Material.ENCHANTMENT_TABLE));
    }

    @Override
    public Inventory getContent() {
        Inventory inventory = Bukkit.createInventory(null, 36);
        inventory.setItem(0, new ItemBuilder(Material.ENCHANTMENT_TABLE, 1, (byte) 0).build());
        inventory.setItem(1, new ItemBuilder(Material.EXP_BOTTLE, 32, (byte) 0).build());
        inventory.setItem(2, new ItemBuilder(Material.ANVIL, 32, (byte) 0).build());
        inventory.setItem(3, new ItemBuilder(Material.DIAMOND, 5, (byte) 0).build());
        inventory.setItem(4, new ItemBuilder(Material.IRON_INGOT, 10, (byte) 0).build());
        return inventory;
    }

    @Override
    public ItemStack[] getArmorContent() {
        return new ItemStack[0];
    }
}
