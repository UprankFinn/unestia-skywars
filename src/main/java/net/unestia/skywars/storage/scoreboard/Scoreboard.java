package net.unestia.skywars.storage.scoreboard;

import net.unestia.skywars.SkyWars;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;

/**
 * @author: Uprank
 * @date: 31.12.2020
 */

public class Scoreboard {

    private final SkyWars skyWars;

    public Scoreboard(SkyWars skyWars) {
        this.skyWars = skyWars;
    }

    public void setScoreboard(Player player) {
        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        org.bukkit.scoreboard.Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        Objective objective = scoreboard.registerNewObjective("aaa", "bbb");
        objective.setDisplaySlot(DisplaySlot.SIDEBAR);
        objective.setDisplayName("§8× §aSkyWars §8×");

        switch (this.skyWars.getUnestiaAPI().getGameState()) {
            case LOBBY:
                objective.getScore("     ").setScore(13);
                objective.getScore("§aMap§8:").setScore(12);
                objective.getScore("§8» §e" + this.skyWars.getUnestiaAPI().getWorld().getName().substring(0, 1).toUpperCase() + this.skyWars.getUnestiaAPI().getWorld().getName().substring(1)).setScore(11);
                objective.getScore("    ").setScore(10);
                objective.getScore("§aOnline§8:").setScore(9);
                objective.getScore("§8» §e" + Bukkit.getOnlinePlayers().size() + "§8/§e" + Bukkit.getMaxPlayers()).setScore(8);
                objective.getScore("   ").setScore(7);
                objective.getScore("§aKit§8:").setScore(6);
                objective.getScore("§8» " + this.skyWars.getKitManager().getKit(player)).setScore(5);
                objective.getScore("  ").setScore(4);
                objective.getScore("§aGameId§8:").setScore(3);
                objective.getScore("§8» §e" + this.skyWars.getUnestiaAPI().getGameId()).setScore(2);
                objective.getScore(" ").setScore(1);
                objective.getScore("§7mc.unestia.net").setScore(0);
                break;
            case PROTECTION:
                objective.getScore("     ").setScore(13);
                objective.getScore("§aMap§8:").setScore(12);
                objective.getScore("§8» §e" + this.skyWars.getUnestiaAPI().getWorld().getName().substring(0, 1).toUpperCase() + this.skyWars.getUnestiaAPI().getWorld().getName().substring(1)).setScore(11);
                objective.getScore("    ").setScore(10);
                objective.getScore("§aPlayers§8:").setScore(9);
                objective.getScore("§8» §e" + this.skyWars.getUnestiaAPI().getPlayers().size()).setScore(8);
                objective.getScore("   ").setScore(7);
                objective.getScore("§aKills§8:").setScore(6);
                objective.getScore("§8» §e0").setScore(5);
                objective.getScore("  ").setScore(4);
                objective.getScore("§aGameId§8:").setScore(3);
                objective.getScore("§8» §e" + this.skyWars.getUnestiaAPI().getGameId()).setScore(2);
                objective.getScore(" ").setScore(1);
                objective.getScore("§7mc.unestia.net").setScore(0);
                break;
            case INGAME:
                if (this.skyWars.getUnestiaAPI().getPlayers().contains(player)) {
                    objective.getScore("    ").setScore(10);
                    objective.getScore("§aMap§8:").setScore(9);
                    objective.getScore("§8» §e" + this.skyWars.getUnestiaAPI().getWorld().getName().substring(0, 1).toUpperCase() + this.skyWars.getUnestiaAPI().getWorld().getName().substring(1)).setScore(11);
                    objective.getScore("   ").setScore(7);
                    objective.getScore("§aPlayers§8:").setScore(6);
                    objective.getScore("§8» §e" + this.skyWars.getUnestiaAPI().getPlayers().size()).setScore(5);
                    objective.getScore(" ").setScore(4);
                    objective.getScore("§aGameId§8:").setScore(3);
                    objective.getScore("§8» §e" + this.skyWars.getUnestiaAPI().getGameId()).setScore(2);
                    objective.getScore(" ").setScore(1);
                    objective.getScore("§7mc.unestia.net").setScore(0);
                } else {
                    objective.getScore("     ").setScore(13);
                    objective.getScore("§aMap§8:").setScore(12);
                    objective.getScore("§8» §e" + this.skyWars.getUnestiaAPI().getWorld().getName().substring(0, 1).toUpperCase() + this.skyWars.getUnestiaAPI().getWorld().getName().substring(1)).setScore(11);
                    objective.getScore("    ").setScore(10);
                    objective.getScore("§aPlayers§8:").setScore(9);
                    objective.getScore("§8» §e" + this.skyWars.getUnestiaAPI().getPlayers().size()).setScore(8);
                    objective.getScore("   ").setScore(7);
                    objective.getScore("§aKills§8:").setScore(6);
                    objective.getScore("§8» §e0").setScore(5);
                    objective.getScore("  ").setScore(4);
                    objective.getScore("§aGameId§8:").setScore(3);
                    objective.getScore("§8» §e" + this.skyWars.getUnestiaAPI().getGameId()).setScore(2);
                    objective.getScore(" ").setScore(1);
                    objective.getScore("§7mc.unestia.net").setScore(0);
                }
                break;
            case ENDING:
                objective.getScore("     ").setScore(13);
                objective.getScore("§aMap§8:").setScore(12);
                objective.getScore("§8» §e" + this.skyWars.getUnestiaAPI().getWorld().getName().substring(0, 1).toUpperCase() + this.skyWars.getUnestiaAPI().getWorld().getName().substring(1)).setScore(11);
                objective.getScore("    ").setScore(10);
                objective.getScore("§aPlayers§8:").setScore(9);
                objective.getScore("§8» §e" + this.skyWars.getUnestiaAPI().getPlayers().size()).setScore(8);
                objective.getScore("   ").setScore(7);
                objective.getScore("§aKills§8:").setScore(6);
                objective.getScore("§8» §e0").setScore(5);
                objective.getScore("  ").setScore(4);
                objective.getScore("§aGameId§8:").setScore(3);
                objective.getScore("§8» §e" + this.skyWars.getUnestiaAPI().getGameId()).setScore(2);
                objective.getScore(" ").setScore(1);
                objective.getScore("§7mc.unestia.net").setScore(0);
                break;

        }

        player.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
        player.setScoreboard(scoreboard);
    }

}
