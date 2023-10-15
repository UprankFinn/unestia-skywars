package net.unestia.skywars.game.kit.type;

import net.unestia.skywars.game.kit.Kit;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;

/**
 * @author: Uprank
 * @date: 01.01.2021
 */

public class TankKit extends Kit {

    public TankKit() {
        super("Tank", Arrays.asList("§8§m------------------------§r", "§cAusrüstung:"), 100000, new ItemStack(Material.IRON_CHESTPLATE));
    }

    @Override
    public Inventory getContent() {
        return null;
    }

    @Override
    public ItemStack[] getArmorContent() {
        return null;
    }
}
