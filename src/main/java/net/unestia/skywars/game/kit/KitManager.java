package net.unestia.skywars.game.kit;

import lombok.Getter;
import lombok.SneakyThrows;
import net.unestia.skywars.game.kit.type.*;
import net.unestia.skywars.game.kit.type.finished.EnchanterKit;
import net.unestia.skywars.game.kit.type.finished.EnderManKit;
import net.unestia.skywars.game.kit.type.finished.RabbitKit;
import net.unestia.skywars.game.kit.type.finished.MaurerKit;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author: Uprank
 * @date: 01.01.2021
 */

public class KitManager {

    @Getter
    private List<Player> defaultKitPlayers = new ArrayList<>();
    @Getter
    private List<Player> maurerKitPlayers = new ArrayList<>();
    @Getter
    private List<Player> endermanKitPlayers = new ArrayList<>();
    @Getter
    private List<Player> tankKitPlayers = new ArrayList<>();
    @Getter
    private List<Player> assassineKitPlayers = new ArrayList<>();
    @Getter
    private List<Player> rabbitKitPlayers = new ArrayList<>();
    @Getter
    private List<Player> enchanterKitPlayers = new ArrayList<>();

    private Kit kit;

    @Getter
    private final Map<Integer, Kit> kits = new HashMap<>();

    @Getter
    private final Map<String, Kit> kitString = new HashMap<>();

    private void registerKit(Kit kit) {
        this.kits.put(this.kits.size(), kit);

        this.kitString.put("default", new DefaultKit());
        this.kitString.put("maurer", new MaurerKit());
        this.kitString.put("enderman", new EnchanterKit());
        this.kitString.put("tank", new TankKit());
        this.kitString.put("assassine", new AssassineKit());
        this.kitString.put("rabbit", new RabbitKit());
        this.kitString.put("enchanter", new EnchanterKit());

    }

    @SneakyThrows
    public void registerKits() {
        this.registerKit(new DefaultKit());
        this.registerKit(new MaurerKit());
        this.registerKit(new EnderManKit());
        this.registerKit(new TankKit());
        this.registerKit(new AssassineKit());
        this.registerKit(new RabbitKit());
        this.registerKit(new EnchanterKit());
    }

    public boolean hasKit(Player player) {
        if (defaultKitPlayers.contains(player)) {
            return true;
        } else if (maurerKitPlayers.contains(player)) {
            return true;
        } else if (endermanKitPlayers.contains(player)) {
            return true;
        } else if (tankKitPlayers.contains(player)) {
            return true;
        } else if (assassineKitPlayers.contains(player)) {
            return true;
        } else if (rabbitKitPlayers.contains(player)) {
            return true;
        } else if (enchanterKitPlayers.contains(player)) {
            return true;
        }
        return false;
    }

    public void setInventoryOfKit(Player player) {
        if (this.defaultKitPlayers.contains(player)) {
            player.getInventory().setContents(new DefaultKit().getContent().getContents());
            player.getInventory().setArmorContents(new DefaultKit().getArmorContent());
        } else if (this.maurerKitPlayers.contains(player)) {
            player.getInventory().setContents(new MaurerKit().getContent().getContents());
            player.getInventory().setArmorContents(new MaurerKit().getArmorContent());
        } else if (this.endermanKitPlayers.contains(player)) {
            player.getInventory().setContents(new EnderManKit().getContent().getContents());
            player.getInventory().setArmorContents(new EnderManKit().getArmorContent());
        } else if (this.tankKitPlayers.contains(player)) {
            player.getInventory().setContents(new TankKit().getContent().getContents());
            player.getInventory().setArmorContents(new TankKit().getArmorContent());
        } else if (this.assassineKitPlayers.contains(player)) {
            player.getInventory().setContents(new AssassineKit().getContent().getContents());
            player.getInventory().setArmorContents(new AssassineKit().getArmorContent());
        } else if (this.rabbitKitPlayers.contains(player)) {
            player.getInventory().setContents(new RabbitKit().getContent().getContents());
            player.getInventory().setArmorContents(new RabbitKit().getArmorContent());
        } else if (this.enchanterKitPlayers.contains(player)) {
            player.getInventory().setContents(new EnchanterKit().getContent().getContents());
            player.getInventory().setArmorContents(new EnchanterKit().getArmorContent());
        } else {
            player.getInventory().setContents(new DefaultKit().getContent().getContents());
            player.getInventory().setArmorContents(new DefaultKit().getArmorContent());
        }
    }

    public String getKit(Player player) {
        if (this.defaultKitPlayers.contains(player)) {
            return "§eDefault";
        } else if (this.maurerKitPlayers.contains(player)) {
            return "§eMaurer";
        } else if (this.endermanKitPlayers.contains(player)) {
            return "§eEnderman";
        } else if (this.tankKitPlayers.contains(player)) {
            return "§eTank";
        } else if (this.assassineKitPlayers.contains(player)) {
            return "§eAssassine";
        } else if (this.rabbitKitPlayers.contains(player)) {
            return "§eRabbit";
        } else if (this.enchanterKitPlayers.contains(player)) {
            return "§eEnchanter";
        }

        return "§eDefault";
    }

    public void removePlayerFromKit(Player player) {
        if (defaultKitPlayers.contains(player)) {
            this.defaultKitPlayers.remove(player);
        } else if (this.maurerKitPlayers.contains(player)) {
            this.maurerKitPlayers.remove(player);
        } else if (this.endermanKitPlayers.contains(player)) {
            this.endermanKitPlayers.remove(player);
        } else if (this.tankKitPlayers.contains(player)) {
            this.tankKitPlayers.remove(player);
        } else if (this.assassineKitPlayers.contains(player)) {
            this.assassineKitPlayers.remove(player);
        } else if (this.rabbitKitPlayers.contains(player)) {
            rabbitKitPlayers.remove(player);
        } else if (this.enchanterKitPlayers.contains(player)) {
            enchanterKitPlayers.remove(player);
        }

    }

    public Kit getKit(@NotNull String kit) {
        if(kitString.containsKey(kit)){
            return this.kitString.get(kit);
        }
        System.out.println("HURENSOHN!");
        return null;
    }

    public Kit getKit(@NotNull Integer value) {
        if (kits.containsKey(value)) {
            return kits.get(value);
        }
        return null;
    }
}
