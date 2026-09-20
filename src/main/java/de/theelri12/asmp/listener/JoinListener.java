package de.theelri12.asmp.listener;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoinEvent(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        if (!player.hasPermission("asmp.bypass.tptospawnonjoin")) {
            Location spawnLocation = new Location(Bukkit.getWorld("world"), -144.5d, 197d, -293.5d, 0f, 15f);
            player.teleport(spawnLocation);
        }
    }

}
