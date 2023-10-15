package net.unestia.skywars.countdown.type;

import lombok.Getter;
import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.entity.EntityType;
import org.bukkit.util.Vector;

import java.text.MessageFormat;

/**
 * @author: Uprank
 * @date: 31.12.2020
 */

public class ProtectionCountdown {

    private SkyWars skyWars;
    @Getter
    private Integer countdown = 10;
    @Getter
    public Boolean started = false;
    @Getter
    private Integer schutzCountdown;

    public ProtectionCountdown(SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public void handleProtectionCountdown() {
        started = true;
        startProtectionCountdown();
    }

    private void startProtectionCountdown() {
        if (this.skyWars.getUnestiaAPI().getGameState() == GameState.PROTECTION) {
            this.schutzCountdown = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.skyWars, () -> {
                if (started) {
                    if (countdown == 10 || countdown == 5 || countdown == 4 || countdown == 3 || countdown == 2) {
                        Bukkit.getOnlinePlayers().forEach(all -> {

                            this.skyWars.getUnestiaAPI().getPlayers().forEach(alivePlayer -> this.skyWars.getUnestiaAPI().getPlayers().forEach(alivePlayer::showPlayer));

                            all.setLevel(countdown);
                            all.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(all.getName()).getLanguage().getTranslations().get("graceperiod_countdown_start_about_one_second"), SkyWars.PREFIX, countdown));
                            all.playSound(all.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1, 1);
                        });
                    } else if (countdown == 1) {
                        Bukkit.getOnlinePlayers().forEach(all -> {
                            all.setLevel(countdown);
                            all.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(all.getName()).getLanguage().getTranslations().get("graceperiod_countdown_start_about_one_second"), SkyWars.PREFIX, countdown));
                            all.playSound(all.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1, 1);
                        });
                        Bukkit.getScheduler().cancelTask(schutzCountdown);
                        this.skyWars.getUnestiaAPI().setGameState(GameState.INGAME);
                        Bukkit.getOnlinePlayers().forEach(all -> {
                            all.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(all.getName()).getLanguage().getTranslations().get("graceperiod_you_can_fight_now"), SkyWars.PREFIX));
                            all.setLevel(0);
                        });
                        this.skyWars.startChangeBoarderTask();


                        /*this.skyWars.getServer().getScheduler().scheduleAsyncRepeatingTask(this.skyWars, () -> {
                            this.skyWars.getUnestiaAPI().getPlayers().forEach(players -> {
                                players.getLocation().getWorld().getNearbyEntities(players.getLocation(), 10.5D, 1.5D, 10.5D).forEach(nearbyEntities -> {
                                    if (!(nearbyEntities.getType().equals(EntityType.PLAYER))) {
                                        return;
                                    }

                                    Location entityLocation = nearbyEntities.getLocation();
                                    nearbyEntities.setVelocity(new Vector(entityLocation.getX() - players.getLocation().getX(),
                                            entityLocation.getY() - players.getLocation().getY(),
                                            entityLocation.getZ() - players.getLocation().getZ()).normalize().multiply(0.5).setY(0.2D));
                                });
                            });
                        }, 0L, 2L);*/

                    } else {
                        Bukkit.getOnlinePlayers().forEach(all -> {
                            all.setLevel(countdown);
                        });
                    }
                    countdown--;
                } else {
                    countdown = 10;
                    Bukkit.getOnlinePlayers().forEach(all -> {
                        all.setLevel(countdown);
                        all.playSound(all.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1, 1);
                    });
                    Bukkit.getScheduler().cancelTask(schutzCountdown);
                }
            }, 20, 20);
        }
    }

    public void stopCountdown() {
        if (started) {
            Bukkit.getScheduler().cancelTask(schutzCountdown);
            this.countdown = 10;
        }
    }
}
