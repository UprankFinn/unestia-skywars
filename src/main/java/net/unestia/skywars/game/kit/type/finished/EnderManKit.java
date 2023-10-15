package net.unestia.skywars.game.kit.type.finished;

import net.unestia.skywars.game.kit.Kit;
import net.unestia.skywars.storage.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * @author: Uprank
 * @date: 04.01.2021
 */

public class EnderManKit extends Kit {

    public EnderManKit() {
        super("Enderman", Arrays.asList(""), 80000, new ItemStack(Material.ENDER_PEARL));
    }

    @Override
    public Inventory getContent() {
        Inventory inventory = Bukkit.createInventory(null, 36);
        inventory.setItem(0, new ItemBuilder(Material.ENDER_PEARL, 1, (byte) 0).build());
        return inventory;
    }

    @Override
    public ItemStack[] getArmorContent() {
        return new ItemStack[]{null, null,
                new ItemBuilder(Material.LEATHER_CHESTPLATE, 1, (byte) 0).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build()
        };
    }
}
