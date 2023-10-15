package net.unestia.skywars.countdown.type;

import lombok.Getter;
import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.Sound;

import java.text.MessageFormat;

/**
 * @author: Uprank
 * @date: 10.01.2021
 */

public class EndingCountdown {

    private final SkyWars skyWars;

    @Getter
    private Integer countdown = 15;

    private Boolean started = false;

    private Integer startCountdown;

    public EndingCountdown(SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public void checkCountdown() {
        started = true;
        startCountdown();
    }

    private void startCountdown() {
        if (this.skyWars.getUnestiaAPI().getGameState() == GameState.ENDING) {
            this.startCountdown = Bukkit.getScheduler().scheduleSyncRepeatingTask(this.skyWars, () -> {
                if (started) {
                    if (countdown == 15 || countdown == 10 || countdown == 4 || countdown == 3 || countdown == 2) {
                        Bukkit.getOnlinePlayers().forEach(all -> {
                            all.getInventory().clear();
                            all.setLevel(countdown);
                            all.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(all.getName()).getLanguage().getTranslations().get("ending_countdown_start_about_one_second"), SkyWars.PREFIX, countdown));

                            all.playSound(all.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1, 1);
                        });
                    } else if (countdown == 5) {

                        Bukkit.getOnlinePlayers().forEach(all -> {
                            all.setLevel(countdown);
                            all.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(all.getName()).getLanguage().getTranslations().get("ending_countdown_start_about_one_second"), SkyWars.PREFIX, countdown));

                            all.playSound(all.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1, 1);
                        });
                    } else if (countdown == 1) {
                        Bukkit.getOnlinePlayers().forEach(all -> {
                            all.setLevel(countdown);
                            all.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(all.getName()).getLanguage().getTranslations().get("ending_countdown_start_on_one_second"), SkyWars.PREFIX, countdown));

                            all.playSound(all.getLocation(), Sound.BLOCK_WOOD_BUTTON_CLICK_ON, 1, 1);
                        });
                        Bukkit.getScheduler().cancelTask(startCountdown);
                        Bukkit.shutdown();
                    } else {
                        Bukkit.getOnlinePlayers().forEach(all -> {
                            all.setLevel(countdown);
                        });
                    }
                    countdown--;
                } else {
                    countdown = 15;
                    Bukkit.getScheduler().cancelTask(startCountdown);
                }
            }, 20, 20);
        }
    }

}
