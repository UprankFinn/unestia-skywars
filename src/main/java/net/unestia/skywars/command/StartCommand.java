package net.unestia.skywars.command;

import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.text.MessageFormat;

/**
 * @author: Uprank
 * @date: 15.01.2021
 */

public class StartCommand implements CommandExecutor {

    private final SkyWars skyWars;

    public StartCommand(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getCommand("start").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        Player player = (Player) commandSender;

        if (this.skyWars.getUnestiaAPI().getGameState() == GameState.LOBBY) {
            this.skyWars.getCountdownType().getLobbyCountdown().forcestart(player);
            player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getLanguage().getTranslations().get("lobby_command_start"), SkyWars.PREFIX));

        } else {
            player.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(player.getName()).getLanguage().getTranslations().get("lobby_command_start_in_not_lobby_state"), SkyWars.PREFIX));
        }

        return false;
    }
}
