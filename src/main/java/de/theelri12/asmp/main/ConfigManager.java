package de.theelri12.asmp.main;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private final JavaPlugin plugin;
    private File file;
    private YamlConfiguration cfg;

    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    public void setup() {
        file = new File(plugin.getDataFolder(), "prison_invs.yml");
        if (!file.exists()) {
            try {
                plugin.getDataFolder().mkdirs();
                file.createNewFile();
            } catch (IOException e) {
                plugin.getLogger().severe("Konnte prison_invs.yml nicht erstellen: " + e.getMessage());
            }
        }
        cfg = YamlConfiguration.loadConfiguration(file);
    }

    public YamlConfiguration get() {
        return cfg;
    }

    public void save() {
        try {
            cfg.save(file);
        } catch (IOException e) {
            plugin.getLogger().severe("Konnte prison_invs.yml nicht speichern: " + e.getMessage());
        }
    }
}
