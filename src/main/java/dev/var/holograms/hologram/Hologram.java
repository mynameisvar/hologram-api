package dev.var.holograms.hologram;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.WrappedDataWatcher;
import lombok.val;
import org.bukkit.Location;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * @author mynameisvar <github@mynameisvar>
 */

public class Hologram {

    private final Location location;
    private final List<String> lines = new ArrayList<>();
    private int entityId;

    /**
     * Конструктор для создания голограммы
     *
     * @param location
     */
    public Hologram(Location location) {
        this.location = location;
    }

    /**
     * Добавление строки текста в голограмму
     *
     * @param line
     * @return
     */
    public Hologram addLine(String line) {
        lines.add(line);
        return this;
    }

    /**
     * Спавн голограммы для заданного игрока
     *
     * @param protoManager
     * @param player
     */
    public void spawn(ProtocolManager protoManager, Player player) {
        entityId = createArmorStand(protoManager, player);
        updateText(protoManager, player);
    }

    /**
     * Создание арморстэнда и возвращение его ID
     *
     * @param protoManager
     * @param player
     * @return
     */
    private int createArmorStand(ProtocolManager protoManager, Player player) {
        PacketContainer spawnPacket = protoManager.createPacket(PacketType.Play.Server.SPAWN_ENTITY);
        int id = (int) (Math.random() * Integer.MAX_VALUE);
        spawnPacket.getIntegers().write(0, id);
        spawnPacket.getUUIDs().write(0, java.util.UUID.randomUUID());
        spawnPacket.getEntityTypeModifier().write(0, EntityType.ARMOR_STAND);
        spawnPacket.getDoubles().write(0, location.getX());
        spawnPacket.getDoubles().write(1, location.getY());
        spawnPacket.getDoubles().write(2, location.getZ());

        protoManager.sendServerPacket(player, spawnPacket);
        return id;
    }

    /**
     * Обновление текста голограммы для игрока
     *
     * @param protoManager
     * @param player
     */
    public void updateText(ProtocolManager protoManager, Player player) {
        for (int i = 0; i < lines.size(); i++) {
            setArmorStandText(protoManager, lines.get(i), i, player);
        }
    }

    /**
     * Установка текста для отдельной линии на арморстэнде
     *
     * @param protoManager
     * @param text
     * @param line
     * @param player
     */
    private void setArmorStandText(ProtocolManager protoManager, String text, int line, Player player) {
        WrappedDataWatcher watcher = new WrappedDataWatcher();
        watcher.setObject(2, text);

        PacketContainer metadataPacket = protoManager.createPacket(PacketType.Play.Server.ENTITY_METADATA);
        metadataPacket.getIntegers().write(0, entityId);
        metadataPacket.getDataWatcherModifier().write(0, watcher);

        val lineHeight = 0.25;
        metadataPacket.getDoubles().write(1, location.getY() + line * lineHeight);

        protoManager.sendServerPacket(player, metadataPacket);
    }

    /**
     * Удаление голограммы для конкретного игрока
     *
     * @param protoManager
     * @param player
     */
    public void remove(ProtocolManager protoManager, Player player) {
        PacketContainer destroyPacket = protoManager.createPacket(PacketType.Play.Server.ENTITY_DESTROY);
        destroyPacket.getIntegerArrays().write(0, new int[]{entityId});
        protoManager.sendServerPacket(player, destroyPacket);
    }

    /**
     * Удаление голограммы для всех игроков
     *
     * @param protoManager
     */
    public void removeAll(ProtocolManager protoManager) {
        for (Player player : org.bukkit.Bukkit.getOnlinePlayers()) {
            remove(protoManager, player);
        }
    }
}