package net.unestia.skywars.game.kit;

import lombok.Getter;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

/**
 * @author: Uprank
 * @date: 01.01.2021
 */

public abstract class Kit {

    @Getter
    private String name;
    @Getter
    private List<String> lore;
    @Getter
    private Integer prize;
    @Getter
    private ItemStack itemStack;

    public Kit(String name, List<String> lore, Integer prize, ItemStack itemStack) {
        this.name = name;
        this.lore = lore;
        this.prize = prize;
        this.itemStack = itemStack;
    }

    public abstract Inventory getContent();

    public abstract ItemStack[] getArmorContent();
}
