package net.unestia.skywars.inventory.spec;

import lombok.Getter;
import net.unestia.skywars.storage.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

/**
 * @author: Uprank
 * @date: 01.01.2021
 */

public class SpectatorInventory {

    @Getter
    private final Inventory inventory;

    public SpectatorInventory() {
        this.inventory = Bukkit.createInventory(null, 36);
        initializeInventory();
    }

    public void setPlayerItems(Player player) {
        player.getInventory().setContents(getInventory().getContents());
    }

    private void initializeInventory() {

        this.inventory.setItem(0, new ItemBuilder(Material.COMPASS, 1, (byte) 0).setDisplayName("§8» §aTeleporter").build());

        this.inventory.setItem(8, new ItemBuilder(Material.MAGMA_CREAM, 1, (byte) 0).setDisplayName("§8» §cLobby").build());

    }

}
