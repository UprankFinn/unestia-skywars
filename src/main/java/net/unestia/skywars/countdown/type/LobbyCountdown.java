package net.unestia.skywars.countdown.type;

import lombok.Getter;
import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import net.unestia.skywars.game.kit.type.DefaultKit;
import org.bukkit.*;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;

/**
 * @author: Uprank
 * @date: 30.12.2020
 */

public class LobbyCountdown {

    private final SkyWars skyWars;

    @Getter
    private Integer countdown = 60;

    private Boolean started = false;

    private Integer startCountdown;

    public LobbyCountdown(SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public void checkCountdown(@NotNull Integer onlinePlayers) {
        if (this.skyWars.getTemplate().equalsIgnoreCase("2x1") || this.skyWars.getTemplate().equalsIgnoreCase("2x2") || this.skyWars.getTemplate().equalsIgnoreCase("4x1")) {
            if (onlinePlayers >= 2) {
                if (started) {
                    return;
                }
                started = true;
                startCountdown();
            } else {
                if (this.skyWars.getUnestiaAPI().getGameState() == GameState.INGAME || this.skyWars.getUnestiaAPI().getGameState() == GameState.ENDING) {
                    stopCountdown();
                    started = false;
                }
                if (this.skyWars.getUnestiaAPI().getGameState() == GameState.LOBBY) {
                    stopCountdown();
                    started = false;
                }
            }
        } else if (this.skyWars.getTemplate().equalsIgnoreCase("4x2") || this.skyWars.getTemplate().equalsIgnoreCase("8x1")) {
            if (onlinePlayers >= 4) {
                if (started) {
                    return;
                }

                started = true;
                startCountdown();
            } else {
                if (this.skyWars.getUnestiaAPI().getGameState() == GameState.INGAME || this.skyWars.getUnestiaAPI().getGameState() == GameState.ENDING) {
                    stopCountdown();
                    started = false;
                }
                if (this.skyWars.getUnestiaAPI().getGameState() == GameState.LOBBY) {
                    stopCountdown();
                    started = false;
                }
            }
        } else if (this.skyWars.getTemplate().equalsIgnoreCase("8x2")) {
            if (onlinePlayers >= 9) {
                if (started) {
                    return;
                }

                started = true;
                startCountdown();
            } else {
                if (this.skyWars.getUnestiaAPI().getGameState() == GameState.INGAME || this.skyWars.getUnestiaAPI().getGameState() == GameState.ENDING) {
                    stopCountdown();
                    started = false;
                }
                if (this.skyWars.getUnestiaAPI().getGameState() == GameState.LOBBY) {
                    stopCountdown();
                    started = false;
                }
            }
        }
    }

    private void startCountdown() {
        if (this.skyWars.getUnestiaAPI().getGameState() == GameState.LOBBY) {
            this.startCountdown = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.skyWars, () -> {
                if (started) {
                    if (countdown == 60 || countdown == 50 || countdown == 40 || countdown == 30 || countdown == 10 || countdown == 4 || countdown == 3 || countdown == 2) {
                        BossBar bossBar = Bukkit.createBossBar("§7Das Spiel startet in §e" + this.countdown + " §7Sekunden", BarColor.BLUE, BarStyle.SEGMENTED_20);
                        Bukkit.getOnlinePlayers().forEach(all -> {
                            if (bossBar.getPlayers().contains(all)) {
                                return;
                            }
                            bossBar.addPlayer(all);
                            bossBar.setTitle("§7Das Spiel startet in §e" + this.countdown + " §7Sekunden");
                            bossBar.setProgress((double) countdown / 60);

                            all.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(all.getName()).getLanguage().getTranslations().get("lobby_countdown_start_about_one_second"), SkyWars.PREFIX, countdown));

                            all.playSound(all.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1, 1);
                        });
                    } else if (countdown == 5) {

                        Bukkit.getOnlinePlayers().forEach(all -> {
                            all.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(all.getName()).getLanguage().getTranslations().get("lobby_countdown_start_about_one_second"), SkyWars.PREFIX, countdown));

                            all.playSound(all.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1, 1);

                            all.sendMessage(SkyWars.PREFIX + "§8§m-----------------------------");
                            all.sendMessage(SkyWars.PREFIX + "§7Information§8:");
                            all.sendMessage(SkyWars.PREFIX + "§7Map§8: §e" + this.skyWars.getUnestiaAPI().getWorld().getName().substring(0, 1).toUpperCase() + this.skyWars.getUnestiaAPI().getWorld().getName().substring(1));
                            all.sendMessage(SkyWars.PREFIX + "§7Kit§8: §e" + this.skyWars.getKitManager().getKit(all.getPlayer()));

                            if (!this.skyWars.getUnestiaAPI().getTemplateName().split("x")[1].equals("1")) {
                                all.sendMessage(SkyWars.PREFIX + "§7Teaming§8: §c" + "Verboten");

                                //all.sendMessage(SkyWars.PREFIX + );
                            } else {
                                all.sendMessage(SkyWars.PREFIX + "§7Teaming§8: §a" + "Erlaubt");
                            }

                            all.sendMessage(SkyWars.PREFIX + "§7Map Builder§8: §e" + this.skyWars.getUnestiaAPI().getWorld().getBuilder().toString());
                            all.sendMessage(SkyWars.PREFIX + "§8§m-----------------------------");

                        });
                    } else if (countdown == 1) {
                        Bukkit.getOnlinePlayers().forEach(all -> {
                            all.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(all.getName()).getLanguage().getTranslations().get("lobby_countdown_start_in_one_second"), SkyWars.PREFIX, countdown));
                            all.playSound(all.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1, 1);
                        });

                        this.skyWars.getUnestiaAPI().setGameState(GameState.PROTECTION);
                        Bukkit.getScheduler().cancelTask(startCountdown);
                        this.stopCountdown();
                        this.skyWars.getCountdownType().getProtectionCountdown().handleProtectionCountdown();

                        Bukkit.getOnlinePlayers().forEach(all -> {
                            this.skyWars.getScoreboard().setScoreboard(all);
                            all.getInventory().clear();

                            all.closeInventory();
                            if (this.skyWars.getKitManager().hasKit(all)) {
                                this.skyWars.getKitManager().setInventoryOfKit(all);
                            } else {
                                all.getInventory().setContents(new DefaultKit().getContent().getContents());
                            }
                            all.setGameMode(GameMode.SURVIVAL);

                            all.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(all.getName()).getLanguage().getTranslations().get("lobby_countdown_player_team"), SkyWars.PREFIX, this.skyWars.getUnestiaAPI().getTeamManager().getTeam(all).getName()));
                            all.teleport(this.skyWars.getUnestiaAPI().getWorldManager().getWorld(this.skyWars.getUnestiaAPI().getWorld().getName()).getLocations().get(String.valueOf(this.skyWars.getUnestiaAPI().getTeamManager().getTeam(all).getName())).toLocation());
                        });
                    }
                    countdown--;
                } else {
                    countdown = 60;
                    Bukkit.getScheduler().cancelTask(startCountdown);
                }
            }, 20, 20);
        }
    }

    public void stopCountdown() {
        if (started) {
            Bukkit.getScheduler().cancelTask(startCountdown);
            this.countdown = 60;
        }
    }

    public void forcestart(@NotNull Player player) {
        if (started && this.countdown > 10) {
            this.countdown = 10;
            player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getLanguage().getTranslations().get("lobby_countdown_forcestart_start"), SkyWars.PREFIX));
        } else {
            player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getLanguage().getTranslations().get("lobby_countdown_forcestart_wait_for_other_players"), SkyWars.PREFIX));

        }
    }

}
