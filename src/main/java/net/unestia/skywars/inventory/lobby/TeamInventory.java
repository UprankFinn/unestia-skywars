package net.unestia.skywars.inventory.lobby;

import net.unestia.api.UnestiaAPI;
import net.unestia.api.team.Team;
import net.unestia.skywars.storage.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: Uprank
 * @date: 28.01.2021
 */

public class TeamInventory {

    public TeamInventory() {
    }

    private Inventory inventory;

    public Inventory getInventory() {
        this.inventory = Bukkit.createInventory(null, 3 * 9, "§8» §eTeams");

        for (Team team : UnestiaAPI.getInstance().getTeamManager().getTeams()) {
            List<String> playerList = new ArrayList<>();
            for (Player players : team.getPlayers()) {
                playerList.add(players.getName());
            }
            this.inventory.addItem(new ItemBuilder(Material.PAPER, 1, (byte) 0).setDisplayName("§8» §e#" + team.getName()).setLore(playerList).build());
        }
        return inventory;
    }

}
