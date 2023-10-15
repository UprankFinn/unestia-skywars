package net.unestia.skywars.listener.block;

import net.unestia.api.game.GameState;
import net.unestia.skywars.SkyWars;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

/**
 * @author: Uprank
 * @date: 30.12.2020
 */

public class BlockPlaceListener implements Listener {

    private final SkyWars skyWars;

    public BlockPlaceListener(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.skyWars.getServer().getPluginManager().registerEvents(this, this.skyWars);
    }

    @EventHandler
    public void onBlockPlaceEvent(BlockPlaceEvent event) {

        if ((this.skyWars.getUnestiaAPI().getGameState() == GameState.INGAME) || this.skyWars.getUnestiaAPI().getGameState() == GameState.PROTECTION) {
            event.setCancelled(this.skyWars.getSpectator().contains(event.getPlayer()));

        } else {
            event.setCancelled(true);
        }


    }

}
