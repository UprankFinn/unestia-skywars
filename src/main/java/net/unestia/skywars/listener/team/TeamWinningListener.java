package net.unestia.skywars.listener.team;

import net.unestia.api.event.TeamWinningEvent;
import net.unestia.api.game.GameState;
import net.unestia.api.team.Team;
import net.unestia.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.text.MessageFormat;

/**
 * @author: Uprank
 * @date: 12.01.2021
 */

public class TeamWinningListener implements Listener {

    private final SkyWars skyWars;

    public TeamWinningListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onTeamWinningEvent(TeamWinningEvent event) {
        Team team = event.getTeam();

        if (this.skyWars.getUnestiaAPI().getTeamManager().getTeams().size() == 1) {
            Bukkit.getOnlinePlayers().forEach(players -> {
                players.getInventory().clear();
                players.setLevel(0);
                players.getActivePotionEffects().clear();
                players.setFoodLevel(20);
                players.setHealth(20);
                players.showPlayer(this.skyWars, players);
                players.getPlayer().teleport(Bukkit.getWorld("world").getSpawnLocation());
                players.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(players.getName()).getLanguage().getTranslations().get("ending_winner"), SkyWars.PREFIX, team.getName()));
                players.playSound(players.getLocation(), Sound.ENTITY_FIREWORK_BLAST, 1f, 1f);
            });
            this.skyWars.getUnestiaAPI().setGameState(GameState.ENDING);
            this.skyWars.getCountdownType().getEndingCountdown().checkCountdown();
        }
    }

}
