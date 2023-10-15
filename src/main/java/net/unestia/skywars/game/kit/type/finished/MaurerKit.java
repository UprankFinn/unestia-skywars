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
 * @date: 04.01.2021
 */

public class MaurerKit extends Kit {

    public MaurerKit() {
        super("Maurer", Arrays.asList(""), 30000, new ItemStack(Material.BRICK));
    }

    @Override
    public Inventory getContent() {
        Inventory inventory = Bukkit.createInventory(null, 36);
        inventory.setItem(0, new ItemBuilder(Material.IRON_SWORD, 1, (byte) 0).build());
        inventory.setItem(1, new ItemBuilder(Material.BRICK, 64, (byte) 0).build());
        inventory.setItem(2, new ItemBuilder(Material.BRICK, 64, (byte) 0).build());
        inventory.setItem(3, new ItemBuilder(Material.BRICK, 64, (byte) 0).build());
        inventory.setItem(4, new ItemBuilder(Material.STONE, 64, (byte) 0).build());
        inventory.setItem(5, new ItemBuilder(Material.STONE, 64, (byte) 0).build());
        inventory.setItem(6, new ItemBuilder(Material.STONE, 64, (byte) 0).build());
        return inventory;
    }

    @Override
    public ItemStack[] getArmorContent() {
        return new ItemStack[0];
    }
}
