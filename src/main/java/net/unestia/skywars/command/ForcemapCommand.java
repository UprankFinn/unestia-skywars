package net.unestia.skywars.command;

import net.unestia.skywars.SkyWars;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

/**
 * @author: Uprank
 * @date: 15.01.2021
 */

public class ForcemapCommand implements CommandExecutor {

    private final SkyWars skyWars;

    public ForcemapCommand(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getCommand("forcemap").setExecutor(this);
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {



        return false;
    }
}
