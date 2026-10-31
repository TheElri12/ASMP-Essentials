package de.theelri12.asmp.main;

import de.theelri12.asmp.commands.*;
import de.theelri12.asmp.hooks.VaultHook;
import de.theelri12.asmp.item_skins.ItemSkins;
import de.theelri12.asmp.item_skins.listener.MobKillListener;
import de.theelri12.asmp.listener.JoinListener;
import de.theelri12.asmp.listener.LeaveListener;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.plugin.lifecycle.event.types.LifecycleEvents;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;
import java.util.Objects;

public class Main extends JavaPlugin {

    private static Main plugin;

    private static ConfigManager configManager;

    @Override
    public void onEnable() {
        plugin = this;

        saveDefaultConfig();
        getConfig().options().copyDefaults(true);
        saveConfig();

        ItemSkins.loadFromConfig(plugin.getConfig());

        configManager = new ConfigManager(this);
        configManager.setup();

        VaultHook.setupEconomy(this);

        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new LeaveListener(), this);
        pluginManager.registerEvents(new JoinListener(), this);
        pluginManager.registerEvents(new MobKillListener(), this);


        //Command
        this.getLifecycleManager().registerEventHandler(LifecycleEvents.COMMANDS, commands -> {
            Commands registrar = commands.registrar();
            registrar.register(ContinueCommand.create(), "Teleportiert dich zu deiner letzten Logout Position", List.of("c", "back", "weiter", "b"));
            registrar.register(ASMPDebugCommand.create());
            registrar.register(OpenPassCommand.create(), "Öffnet einen Pass", List.of("open", "pass"));
            registrar.register(DepositCommand.create(), "Zahlt Shards auf dein Konto ein", List.of("d", "dep"));
            registrar.register(WithdrawCommand.create(), "Zahlt Shards aus", List.of("w", "with", "draw"));
            registrar.register(SetSkinCommand.create(), "Wendet einen Bestimmten block/item auf deine item/block an um es zu verbessern");
            registrar.register(ASMPCommand.create());
            registrar.register(SetItemModelCommands.create());
        });


    }

    @Override
    public void onDisable() {

    }

    public static Main getPlugin() {
        return plugin;
    }

    public static String getPrefix() {
        return getPlugin().getConfig().getString("prefix", "[ASMP]");
    }
    public static Location getLocation(String locationName) {
        Location location = Main.getPlugin().getConfig().getLocation("locations." + locationName);
        return Objects.requireNonNullElseGet(location, () -> new Location(Bukkit.getWorld("world"), -144.5d, 197.0d, -293.5d, 90L, 0L));
    }

    public static ConfigManager getConfigManager() { return configManager; }


}
