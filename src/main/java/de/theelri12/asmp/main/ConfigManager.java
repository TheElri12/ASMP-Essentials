package de.theelri12.asmp.main;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

public class ConfigManager {

    private final JavaPlugin plugin;
    public ConfigManager(JavaPlugin plugin) {
        this.plugin = plugin;
    }

    private File file;
    private YamlConfiguration cfg;

    public void setup() {
        file = new File(plugin.getDataFolder(), "prison_invs.yml");
        if (!file.exists()) {
            plugin.saveResource("prison_invs.yml", false);
        }
        cfg = YamlConfiguration.loadConfiguration(file);
    }

    public void save() throws IOException {
        cfg.save(file);
    }

}
