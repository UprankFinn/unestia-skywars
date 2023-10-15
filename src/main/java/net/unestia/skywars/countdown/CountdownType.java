package net.unestia.skywars.countdown;

import lombok.Getter;
import net.unestia.skywars.SkyWars;
import net.unestia.skywars.countdown.type.EndingCountdown;
import net.unestia.skywars.countdown.type.LobbyCountdown;
import net.unestia.skywars.countdown.type.ProtectionCountdown;

/**
 * @author: Uprank
 * @date: 14.01.2021
 */

public class CountdownType {

    private final SkyWars skyWars;
    @Getter
    private EndingCountdown endingCountdown;
    @Getter
    private LobbyCountdown lobbyCountdown;
    @Getter
    private ProtectionCountdown protectionCountdown;

    public CountdownType(SkyWars skyWars) {
        this.skyWars = skyWars;
        this.endingCountdown = new EndingCountdown(this.skyWars);
        this.lobbyCountdown = new LobbyCountdown(this.skyWars);
        this.protectionCountdown = new ProtectionCountdown(this.skyWars);
    }
}
