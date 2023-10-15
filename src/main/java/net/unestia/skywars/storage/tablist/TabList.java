package net.unestia.skywars.storage.tablist;

import net.minecraft.server.v1_12_R1.IChatBaseComponent;
import net.minecraft.server.v1_12_R1.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;
import org.bukkit.entity.Player;

import java.lang.reflect.Field;

/**
 * @author: Uprank
 * @date: 10.01.2021
 */

public class TabList {

    public void setPlayerTabList(Player player, String header, String footer) {
        if (header == null) {
            header = "";
        }
        if (footer == null) {
            footer = "";
        }
        IChatBaseComponent tabHeader = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + header + "\"}");
        IChatBaseComponent tabFooter = IChatBaseComponent.ChatSerializer.a("{\"text\":\"" + footer + "\"}");
        PacketPlayOutPlayerListHeaderFooter headerPacket = new PacketPlayOutPlayerListHeaderFooter();
        try {
            Field field1 = headerPacket.getClass().getDeclaredField("a");
            field1.setAccessible(true);
            field1.set(headerPacket, tabHeader);
            Field field = headerPacket.getClass().getDeclaredField("b");
            field.setAccessible(true);
            field.set(headerPacket, tabFooter);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        } finally {
            ((CraftPlayer) player).getHandle().playerConnection.sendPacket(headerPacket);
        }
        ((CraftPlayer) player).getHandle().playerConnection.sendPacket(headerPacket);
    }

}
