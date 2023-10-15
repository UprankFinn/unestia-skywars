package net.unestia.skywars.game.kit.type;

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
 * @date: 01.01.2021
 */

public class AssassineKit extends Kit {

    public AssassineKit() {
        super("Assassine", Arrays.asList("§8§m------------------------§r", "§cAusrüstung:", "§71x DiamondSword with Sharpness 1", "§71x DiamondBoots with Protection 1"), 100000, new ItemStack(Material.DIAMOND_SWORD));
    }

    @Override
    public Inventory getContent() {
        Inventory inventory = Bukkit.createInventory(null, 36);
        inventory.setItem(0, new ItemBuilder(Material.DIAMOND_SWORD, 1, (byte) 0).addEnchant(Enchantment.DAMAGE_ALL, 1).addEnchant(Enchantment.DURABILITY, 1).build());
        return inventory;
    }

    @Override
    public ItemStack[] getArmorContent() {
        return new ItemStack[]{
                new ItemBuilder(Material.DIAMOND_BOOTS, 1, (byte) 0).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build()
        };
    }
}
