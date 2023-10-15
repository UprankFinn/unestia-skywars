package net.unestia.skywars.listener.inventory;

import lombok.Getter;
import net.unestia.api.game.GameState;
import net.unestia.api.world.World;
import net.unestia.skywars.SkyWars;
import net.unestia.skywars.storage.items.ItemBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * @author: Uprank
 * @date: 30.12.2020
 */

public class InventoryClickListener implements Listener {

    @Getter
    private boolean mapChange = true;
    private final SkyWars skyWars;

    public InventoryClickListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {

        Player player = (Player) event.getWhoClicked();

        if (this.skyWars.getUnestiaAPI().getGameState() == GameState.INGAME || this.skyWars.getUnestiaAPI().getGameState() == GameState.PROTECTION) {
            if (this.skyWars.getSpectator().contains(player) && !this.skyWars.getUnestiaAPI().getPlayers().contains(player)) {

                if (event.getClickedInventory().getName().contains("§8» §aPlayers")) {
                    if (event.getCurrentItem().getItemMeta().getDisplayName().startsWith("§8» §a")) {
                        String name = event.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§8» §a", "");
                        player.closeInventory();
                        player.teleport(Bukkit.getPlayer(name).getLocation());
                        player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getLanguage().getTranslations().get("pvp_specator_view_player_message"), SkyWars.PREFIX, name));
                    }
                    event.setCancelled(true);
                }

            }
            event.setCancelled(false);
        } else {
            event.setCancelled(true);
            if (event.getCurrentItem() == null) {
                return;
            }
            if (!event.getCurrentItem().hasItemMeta()) {
                return;
            }

            // =========================================================================================================================================== //

            if (event.getClickedInventory().getName().equals("§8» §7Kits")) {
                event.setCancelled(true);
                if (event.getCurrentItem().getItemMeta().getDisplayName().startsWith("§8» §7") && event.getClick().isLeftClick()) {
                    String kit = event.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§8» §7", "");
                    player.closeInventory();

                    switch (kit) {

                        case "Default":
                            this.skyWars.getKitManager().removePlayerFromKit(player);
                            this.skyWars.getKitManager().getDefaultKitPlayers().add(player);
                            player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getLanguage().getTranslations().get("lobby_kit_select_message"), SkyWars.PREFIX, kit));
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            this.skyWars.getScoreboard().setScoreboard(player);
                            break;

                        case "Maurer":
                            //if (this.skyWars.getSkyWarsPlayerManager().getSkyWarsPlayer(player.getUniqueId()).getKits().hasMaurerKit()) {
                            this.skyWars.getKitManager().removePlayerFromKit(player);
                            this.skyWars.getKitManager().getMaurerKitPlayers().add(player);
                            player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getLanguage().getTranslations().get("lobby_kit_select_message"), SkyWars.PREFIX, kit));
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            this.skyWars.getScoreboard().setScoreboard(player);
                            /*} else {
                                player.closeInventory();
                                player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getUniqueId()).getLanguage().getTranslations().get("lobby_kit_select_message_noperms"), SkyWars.PREFIX));
                            }*/
                            break;

                        case "Enchanter":
                            //if (this.skyWars.getSkyWarsPlayerManager().getSkyWarsPlayer(player.getUniqueId()).getKits().hasEnchanterKit()) {
                            this.skyWars.getKitManager().removePlayerFromKit(player);
                            this.skyWars.getKitManager().getEnchanterKitPlayers().add(player);
                            player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getLanguage().getTranslations().get("lobby_kit_select_message"), SkyWars.PREFIX, kit));
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            this.skyWars.getScoreboard().setScoreboard(player);
                            /*} else {
                                player.closeInventory();
                                player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getUniqueId()).getLanguage().getTranslations().get("lobby_kit_select_message_noperms"), SkyWars.PREFIX));
                            }*/
                            break;

                        case "Assassine":
                            //if (this.skyWars.getSkyWarsPlayerManager().getSkyWarsPlayer(player.getUniqueId()).getKits().hasAssassineKit()) {
                            this.skyWars.getKitManager().removePlayerFromKit(player);
                            this.skyWars.getKitManager().getAssassineKitPlayers().add(player);
                            player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getLanguage().getTranslations().get("lobby_kit_select_message"), SkyWars.PREFIX, kit));
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            this.skyWars.getScoreboard().setScoreboard(player);
                            /*} else {
                                player.closeInventory();
                                player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getUniqueId()).getLanguage().getTranslations().get("lobby_kit_select_message_noperms"), SkyWars.PREFIX));
                            }*/
                            break;

                        case "Enderman":
                            //if (this.skyWars.getSkyWarsPlayerManager().getSkyWarsPlayer(player.getUniqueId()).getKits().hasEndermanKit()) {
                            this.skyWars.getKitManager().removePlayerFromKit(player);
                            this.skyWars.getKitManager().getEndermanKitPlayers().add(player);
                            player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getLanguage().getTranslations().get("lobby_kit_select_message"), SkyWars.PREFIX, kit));
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            this.skyWars.getScoreboard().setScoreboard(player);
                            /*} else {
                                player.closeInventory();
                                player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getUniqueId()).getLanguage().getTranslations().get("lobby_kit_select_message_noperms"), SkyWars.PREFIX));
                            }*/
                            break;

                        case "Tank":
                            //if (this.skyWars.getSkyWarsPlayerManager().getSkyWarsPlayer(player.getUniqueId()).getKits().hasTankKit()) {
                            this.skyWars.getKitManager().removePlayerFromKit(player);
                            this.skyWars.getKitManager().getTankKitPlayers().add(player);
                            player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getLanguage().getTranslations().get("lobby_kit_select_message"), SkyWars.PREFIX, kit));
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            this.skyWars.getScoreboard().setScoreboard(player);
                            /*} else {
                                player.closeInventory();
                                player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getUniqueId()).getLanguage().getTranslations().get("lobby_kit_select_message_noperms"), SkyWars.PREFIX));
                            }*/
                            break;

                        case "Rabbit":
                            //if (this.skyWars.getSkyWarsPlayerManager().getSkyWarsPlayer(player.getUniqueId()).getKits().hasRabbitKit()) {
                            this.skyWars.getKitManager().removePlayerFromKit(player);
                            this.skyWars.getKitManager().getRabbitKitPlayers().add(player);
                            player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getLanguage().getTranslations().get("lobby_kit_select_message"), SkyWars.PREFIX, kit));
                            player.playSound(player.getLocation(), Sound.ENTITY_PLAYER_LEVELUP, 1, 1);
                            this.skyWars.getScoreboard().setScoreboard(player);
                            /*} else {
                                player.closeInventory();
                                player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getUniqueId()).getLanguage().getTranslations().get("lobby_kit_select_message_noperms"), SkyWars.PREFIX));
                            }*/
                            break;
                    }
                } else if (event.getCurrentItem().getItemMeta().getDisplayName().startsWith("§8» §7") && event.getClick().isRightClick()) {
                    String kit = event.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§8» §7", "");
                    Inventory inventory = Bukkit.createInventory(null, 9 * 1, "§8» §7Kit §8❘ §e" + kit);
                    inventory.setItem(0, new ItemBuilder(Material.PAPER, 1, (byte) 0).setDisplayName("§8» §eInformation").build());
                    inventory.setItem(4, new ItemBuilder(Material.INK_SACK, 1, (byte) 10).setDisplayName("§8» §aBuy").build());
                    inventory.setItem(8, new ItemBuilder(Material.INK_SACK, 1, (byte) 1).setDisplayName("§8» §cCancel").build());
                    event.getWhoClicked().openInventory(inventory);
                }
            }

            // =========================================================================================================================================== //

            if (event.getClickedInventory().getName().startsWith("§8» §7Kit §8❘ §e")) {
                String kit = event.getClickedInventory().getName().replaceAll("§8» §7Kit §8❘ §e", "");
                event.setCancelled(true);
                if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§8» §aBuy")) {
                    if (this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getCoins() >=
                            this.skyWars.getKitManager().getKit(kit).getPrize()) {
                        player.sendMessage("HURENSOHN!");
                    } else {
                        player.sendMessage("Kit HS");
                    }
                }
            }

            // =========================================================================================================================================== //

            if (event.getClickedInventory().getName().equals("§8» §eTeams")) {
                event.setCancelled(true);
                if (event.getCurrentItem().getItemMeta().getDisplayName().startsWith("§8» §e#")) {
                    String teamName = event.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§8» §e#", "");
                    if (!this.skyWars.getUnestiaAPI().getTeamManager().getTeam(teamName).getPlayers().contains(player)) {
                        this.skyWars.getUnestiaAPI().getTeamManager().getTeams().forEach(team -> {
                            team.removePlayer(player);
                            this.skyWars.getUnestiaAPI().getTeamManager().getTeam(teamName).getPlayers().add(player);
                            player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getLanguage().getTranslations().get("lobby_add_player_in_team"), SkyWars.PREFIX, teamName));
                        });
                    } else {
                        player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getLanguage().getTranslations().get("lobby_team_player_already_in_team"), SkyWars.PREFIX));
                    }

                    player.closeInventory();
                }

            }

            // =========================================================================================================================================== //

            if (event.getClickedInventory().getName().equals("§8» §4Settings")) {
                event.setCancelled(true);
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cForceStart")) {
                    player.closeInventory();
                    this.skyWars.getCountdownType().getLobbyCountdown().forcestart(player);
                }
                if (event.getCurrentItem().getItemMeta().getDisplayName().equals("§8» §cForceMap")) {
                    player.closeInventory();
                    player.openInventory(this.skyWars.getWorldsInventory().getInventory());
                }
            }

            // =========================================================================================================================================== //

            if (event.getClickedInventory().getName().equals("§8» §9Worlds")) {
                if (this.skyWars.getCountdownType().getLobbyCountdown().getCountdown() > 5) {
                    if (this.mapChange == true) {
                        event.setCancelled(true);
                        if (event.getCurrentItem().getItemMeta().getDisplayName().startsWith("§8» §9")) {
                            String world = event.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§8» §9", "");
                            player.closeInventory();
                            this.mapChange = false;
                            player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getLanguage().getTranslations().get("lobby_world_select_message"), SkyWars.PREFIX, world));
                            //TODO: ADD MAP CHANGE
                        }
                    } else {
                        player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getLanguage().getTranslations().get("lobby_world_select_message_already_changed"), SkyWars.PREFIX));
                    }
                } else {
                    player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getLanguage().getTranslations().get("lobby_world_select_message_under_5_seconds"), SkyWars.PREFIX));
                }

                // =========================================================================================================================================== //

            }
        }
    }
}
