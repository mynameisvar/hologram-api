package dev.var.holograms.manager;

import com.comphenix.protocol.ProtocolLibrary;
import dev.var.holograms.hologram.Hologram;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mynameisvar <github@mynameisvar>
 */

public class HologramManager {

    private final List<Hologram> holograms = new ArrayList<>();

    /**
     * Создание новой голограммы и спавн для игрока
     *
     * @param location
     * @param player
     * @return
     */
    public Hologram createHologram(Location location, Player player) {
        Hologram hologram = new Hologram(location);
        hologram.spawn(ProtocolLibrary.getProtocolManager(), player);
        holograms.add(hologram);
        return hologram;
    }

    /**
     * Удаление голограммы у конкретного игрока
     *
     * @param hologram
     * @param player
     */
    public void removeHologram(Hologram hologram, Player player) {
        hologram.remove(ProtocolLibrary.getProtocolManager(), player);
        holograms.remove(hologram);
    }

    /**
     * Удаление всех голограмм у конкретного игрока
     *
     * @param player
     */
    public void cleanup(Player player) {
        for (Hologram hologram : new ArrayList<>(holograms)) {
            hologram.remove(ProtocolLibrary.getProtocolManager(), player);
        }
        holograms.clear();
    }

    /**
     *
     * Удаление всех голограмм для всех игроков
     *
     */
    public void cleanupAll() {
        for (Hologram hologram : new ArrayList<>(holograms)) {
            hologram.removeAll(ProtocolLibrary.getProtocolManager());
        }
        holograms.clear();
    }
}