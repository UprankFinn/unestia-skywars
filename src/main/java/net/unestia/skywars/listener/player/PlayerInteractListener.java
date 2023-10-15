package net.unestia.skywars.listener.player;

import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import net.unestia.skywars.inventory.lobby.TeamInventory;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.text.MessageFormat;

/**
 * @author: Uprank
 * @date: 30.12.2020
 */

public class PlayerInteractListener implements Listener {

    private final SkyWars skyWars;

    public PlayerInteractListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);

        //this.inventory = this.skyWars.getUnestiaAPI().getInventoryManager().createTeamInventory();
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        if (event.getItem() == null) return;
        if (!(event.getItem().hasItemMeta())) return;
        if (event.getItem().getItemMeta().getDisplayName() == null) return;
        if (event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) return;

        if (this.skyWars.getUnestiaAPI().getGameState() == GameState.LOBBY) {

            switch (event.getItem().getItemMeta().getDisplayName()) {
                case "§8» §7Kits":
                    event.getPlayer().openInventory(this.skyWars.getKitInventory().getInventory());
                    break;

                case "§8» §aTeams":
                    event.getPlayer().openInventory(new TeamInventory().getInventory());
                    break;

                case "§8» §4Settings":
                    event.getPlayer().openInventory(this.skyWars.getSettingsInventory().getInventory());
                    break;

                case "§8» §cLobby":
                    event.getPlayer().kickPlayer(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(event.getPlayer().getName()).getLanguage().getTranslations().get("left_message"), SkyWars.PREFIX));
                    break;

            }
        } else if (this.skyWars.getUnestiaAPI().getGameState() == GameState.INGAME || this.skyWars.getUnestiaAPI().getGameState() == GameState.PROTECTION || this.skyWars.getUnestiaAPI().getGameState() == GameState.ENDING) {
            if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aTeleporter")) {
                if (!(this.skyWars.getUnestiaAPI().getPlayers().size() == 0)) {
                    event.getPlayer().openInventory(this.skyWars.getTeleporterInventory().getInventory());
                } else {
                    event.getPlayer().sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(event.getPlayer().getName()).getLanguage().getTranslations().get("skywars_no_players_in_teleporter"), SkyWars.PREFIX));
                }
            }
            if (event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §cLobby")) {
                event.getPlayer().kickPlayer(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(event.getPlayer().getName()).getLanguage().getTranslations().get("left_message"), SkyWars.PREFIX));
            }
        }
    }
}
