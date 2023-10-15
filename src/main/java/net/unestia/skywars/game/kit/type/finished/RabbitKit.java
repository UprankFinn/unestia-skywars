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
 * @date: 05.01.2021
 */

public class RabbitKit extends Kit {

    public RabbitKit() {
        super("Rabbit", Arrays.asList(""), 90000, new ItemStack(Material.RABBIT_FOOT));
    }

    @Override
    public Inventory getContent() {
        Inventory inventory = Bukkit.createInventory(null, 36);
        inventory.setItem(0, new ItemBuilder(Material.RABBIT_FOOT, 1, (byte) 0).addEnchant(Enchantment.DAMAGE_ALL, 5).addEnchant(Enchantment.KNOCKBACK, 1).build());
        return inventory;
    }

    @Override
    public ItemStack[] getArmorContent() {
        return new ItemStack[0];
    }
}
