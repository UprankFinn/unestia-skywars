package net.unestia.skywars.game.chestloot;

import lombok.Getter;
import net.unestia.skywars.SkyWars;
import net.unestia.skywars.storage.items.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Uprank
 * @date: 03.01.2021
 */

public class ChestLootManager {

    private final SkyWars skyWars;

    @Getter
    private List<ItemStack> itemStack;

    public ChestLootManager(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.itemStack = new ArrayList<>();
    }

    public void initialize() {
        this.itemStack.clear();
        this.itemStack.add(new ItemStack(Material.STONE, this.skyWars.random(32, 64)));
        this.itemStack.add(new ItemStack(Material.STONE, this.skyWars.random(32, 64)));
        this.itemStack.add(new ItemStack(Material.STONE, this.skyWars.random(32, 64)));
        this.itemStack.add(new ItemStack(Material.STONE, this.skyWars.random(32, 64)));
        this.itemStack.add(new ItemStack(Material.STONE, this.skyWars.random(32, 64)));
        this.itemStack.add(new ItemStack(Material.STONE, this.skyWars.random(32, 64)));
        this.itemStack.add(new ItemStack(Material.BRICK, this.skyWars.random(32, 64)));
        this.itemStack.add(new ItemStack(Material.BRICK, this.skyWars.random(32, 64)));
        this.itemStack.add(new ItemStack(Material.BRICK, this.skyWars.random(32, 64)));
        this.itemStack.add(new ItemStack(Material.BRICK, this.skyWars.random(32, 64)));
        this.itemStack.add(new ItemStack(Material.BRICK, this.skyWars.random(32, 64)));
        this.itemStack.add(new ItemStack(Material.BRICK, this.skyWars.random(32, 64)));
        this.itemStack.add(new ItemStack(Material.BREAD, this.skyWars.random(8, 12)));
        this.itemStack.add(new ItemStack(Material.FISHING_ROD, 1));
        this.itemStack.add(new ItemStack(Material.DIAMOND, this.skyWars.random(1, 5)));
        this.itemStack.add(new ItemStack(Material.IRON_INGOT, this.skyWars.random(1, 10)));
        this.itemStack.add(new ItemStack(Material.CAKE, this.skyWars.random(1, 2)));
        this.itemStack.add(new ItemStack(Material.COBBLESTONE, this.skyWars.random(1, 64)));
        this.itemStack.add(new ItemStack(Material.GOLD_INGOT, this.skyWars.random(1, 12)));
        this.itemStack.add(new ItemStack(Material.GOLDEN_APPLE, 1));
        this.itemStack.add(new ItemStack(Material.EXP_BOTTLE, this.skyWars.random(1, 12)));
        this.itemStack.add(new ItemStack(Material.INK_SACK, this.skyWars.random(1, 1), (byte) 4));
        this.itemStack.add(new ItemStack(Material.BOW, 1));
        this.itemStack.add(new ItemStack(Material.ARROW, this.skyWars.random(1, 4)));
        this.itemStack.add(new ItemStack(Material.CARROT, this.skyWars.random(1, 12)));
        this.itemStack.add(new ItemStack(Material.COMPASS, 1));
        this.itemStack.add(new ItemStack(Material.DIAMOND_AXE, 1));
        this.itemStack.add(new ItemStack(Material.LAVA_BUCKET, 1));
        this.itemStack.add(new ItemStack(Material.WATER_BUCKET, 1));
        this.itemStack.add(new ItemStack(Material.TNT, 1));
        this.itemStack.add(new ItemStack(Material.FLINT_AND_STEEL, 1));
        this.itemStack.add(new ItemStack(Material.MELON, this.skyWars.random(1, 12)));
        this.itemStack.add(new ItemStack(Material.ENDER_PEARL, 1));
        this.itemStack.add(new ItemStack(Material.WEB, this.skyWars.random(1, 4)));
        this.itemStack.add(new ItemStack(Material.AIR, this.skyWars.random(1, 2)));
        this.itemStack.add(new ItemStack(Material.BOAT, this.skyWars.random(1, 1)));
        this.itemStack.add(new ItemStack(Material.SNOW_BALL, this.skyWars.random(1, 9)));
        this.itemStack.add(new ItemStack(Material.EGG, this.skyWars.random(1, 9)));

        this.itemStack.add(new ItemStack(Material.ELYTRA, 1));
        this.itemStack.add(new ItemStack(Material.SHIELD, 1));

        //================== ATTACK EQUIPMENT =========================================================================/

        this.itemStack.add(new ItemStack(Material.DIAMOND_SWORD, 1));
        this.itemStack.add(new ItemStack(Material.DIAMOND_PICKAXE, 1));

        this.itemStack.add(new ItemStack(Material.IRON_SWORD, 1));
        this.itemStack.add(new ItemStack(Material.IRON_AXE, 1));
        this.itemStack.add(new ItemStack(Material.IRON_PICKAXE, 1));

        this.itemStack.add(new ItemBuilder(Material.STONE_SWORD, 1, (byte) 0).addEnchant(Enchantment.DAMAGE_ALL, 2).build());
        this.itemStack.add(new ItemBuilder(Material.WOOD_SWORD, 1, (byte) 0).addEnchant(Enchantment.DAMAGE_ALL, 3).build());

        //================== DIA EQUIPMENT =========================================================================/

        this.itemStack.add(new ItemBuilder(Material.DIAMOND_HELMET, 1, (byte) 0).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
        this.itemStack.add(new ItemBuilder(Material.DIAMOND_CHESTPLATE, 1, (byte) 0).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
        this.itemStack.add(new ItemBuilder(Material.DIAMOND_LEGGINGS, 1, (byte) 0).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
        this.itemStack.add(new ItemBuilder(Material.DIAMOND_BOOTS, 1, (byte) 0).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());

        //================== IRON EQUIPMENT =========================================================================/

        this.itemStack.add(new ItemBuilder(Material.IRON_HELMET, 1, (byte) 0).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
        this.itemStack.add(new ItemBuilder(Material.IRON_CHESTPLATE, 1, (byte) 0).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
        this.itemStack.add(new ItemBuilder(Material.IRON_LEGGINGS, 1, (byte) 0).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
        this.itemStack.add(new ItemBuilder(Material.IRON_BOOTS, 1, (byte) 0).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());

        //================== CHAIN EQUIPMENT =========================================================================/

        this.itemStack.add(new ItemBuilder(Material.CHAINMAIL_HELMET, 1, (byte) 0).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
        this.itemStack.add(new ItemBuilder(Material.CHAINMAIL_CHESTPLATE, 1, (byte) 0).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());
        this.itemStack.add(new ItemBuilder(Material.CHAINMAIL_LEGGINGS, 1, (byte) 0).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
        this.itemStack.add(new ItemBuilder(Material.CHAINMAIL_BOOTS, 1, (byte) 0).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).build());

        //================== SPECIAL EQUIPMENT =========================================================================/

        this.itemStack.add(new ItemBuilder(Material.BLAZE_ROD, this.skyWars.random(1, 1), (byte) 0).setDisplayName("§8» §7Rescue platform").build());
        this.itemStack.add(new ItemBuilder(Material.EXPLOSIVE_MINECART, this.skyWars.random(1, 1), (byte) 0).setDisplayName("§8» §7tnt boost").build());

        //================== POTION EQUIPMENT =========================================================================/

        ItemStack regenerationPotionItemStack = new ItemStack(Material.POTION, 1, (byte) 8225);
        ItemStack speedPotionItemStack = new ItemStack(Material.POTION, 1, (byte) 8194);
        ItemStack direktheilungPotionItemStack = new ItemStack(Material.POTION, 1, (byte) 16421);
        ItemStack langsamkeitPotionItemStack = new ItemStack(Material.POTION, 1, (short) 16426);
        ItemStack schwächePotionItemStack = new ItemStack(Material.POTION, 1, (short) 16424);
        ItemStack direktschadenPotionItemStack = new ItemStack(Material.POTION, 1, (short) 16460);
        ItemStack vergiftungPotionItemStack = new ItemStack(Material.POTION, 1, (short) 16388);

        this.itemStack.add(regenerationPotionItemStack);
        this.itemStack.add(speedPotionItemStack);
        this.itemStack.add(direktheilungPotionItemStack);
        this.itemStack.add(langsamkeitPotionItemStack);
        this.itemStack.add(schwächePotionItemStack);
        this.itemStack.add(direktschadenPotionItemStack);
        this.itemStack.add(vergiftungPotionItemStack);

    }

}
