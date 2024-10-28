package dev.var.holograms;

import dev.var.holograms.hologram.HologramAPI;
import dev.var.holograms.manager.HologramManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

@Getter
public final class HologramAPIPlugin extends JavaPlugin {

    private static HologramAPIPlugin instance;
    private HologramManager hologramManager;
    private HologramAPI hologramAPI;

    public static HologramAPIPlugin getInstance() {
        return instance;
    }

    public HologramManager getHologramManager() {
        return hologramManager;
    }

    public HologramAPI getHologramAPI() {
        return hologramAPI;
    }


    @Override
    public void onEnable() {
        instance = this;
        hologramManager = new HologramManager();
        hologramAPI = new HologramAPI();
    }

    @Override
    public void onDisable() {
        hologramManager.cleanupAll();
    }

}
