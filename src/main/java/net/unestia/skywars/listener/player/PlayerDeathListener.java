package net.unestia.skywars.listener.player;

import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import net.unestia.skywars.inventory.spec.SpectatorInventory;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.text.MessageFormat;

/**
 * @author: Uprank
 * @date: 30.12.2020
 */

public class PlayerDeathListener implements Listener {

    private final SkyWars skyWars;

    public PlayerDeathListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onPlayerDeathEvent(PlayerDeathEvent event) {
        event.setDeathMessage(null);

        if (this.skyWars.getUnestiaAPI().getGameState() == GameState.INGAME) {
            if (event.getEntity().getKiller() == null) {
                Bukkit.getOnlinePlayers().forEach(players -> {
                    players.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(players.getName()).getLanguage().getTranslations().get("death_message"), SkyWars.PREFIX, event.getEntity().getName()));

                });
            } else if (event.getEntity().getKiller() != null) {
                Bukkit.getOnlinePlayers().forEach(players -> {
                    players.sendMessage(MessageFormat.format(this.skyWars.getUnestiaAPI().getPlayerManager().getPlayer(players.getName()).getLanguage().getTranslations().get("death_message_from_other_player"), SkyWars.PREFIX, event.getEntity().getName(), event.getEntity().getKiller().getName()));
                });
                event.setKeepInventory(false);
            }

            for (ItemStack itemStack : event.getEntity().getInventory().getContents()) {
                if (itemStack == null) {
                    return;
                }
                if (itemStack.getType().equals(Material.AIR)) {
                    return;
                }

                event.getEntity().getWorld().dropItemNaturally(event.getEntity().getLocation(), itemStack);
                event.getEntity().getInventory().remove(itemStack);
            }

            this.skyWars.setSpectator(event.getEntity());

            event.getEntity().getPlayer().spigot().respawn();

            Bukkit.getScheduler().scheduleAsyncDelayedTask(this.skyWars, new Runnable() {
                @Override
                public void run() {
                    event.getEntity().getInventory().setContents(new SpectatorInventory().getInventory().getContents());
                }
            }, 5L);

            for (Player player : this.skyWars.getSpectator()) {
                Bukkit.getOnlinePlayers().forEach(players -> {
                    player.hidePlayer(this.skyWars, players);
                    player.showPlayer(this.skyWars, players);
                });
            }
        }
    }
}
