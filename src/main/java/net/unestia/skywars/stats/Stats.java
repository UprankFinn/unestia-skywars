package net.unestia.skywars.stats;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * @author: Uprank
 * @date: 12.01.2021
 */

@Getter
@Setter
@AllArgsConstructor
public class Stats {

    private Integer kills;
    private Integer deaths;
    private Integer wins;
    private Integer games;

}
