package dev.var.holograms.hologram;

import dev.var.holograms.HologramAPIPlugin;
import dev.var.holograms.manager.HologramManager;
import org.bukkit.Location;
import org.bukkit.entity.Player;

/**
 * @author mynameisvar <github@mynameisvar>
 */

public class HologramAPI {

    private final HologramManager hologramManager;

    public HologramAPI() {
        this.hologramManager = HologramAPIPlugin.getInstance().getHologramManager();
    }

    public Hologram createHologram(Location location, Player player) {
        return hologramManager.createHologram(location, player);
    }
}