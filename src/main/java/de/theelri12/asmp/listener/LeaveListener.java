package de.theelri12.asmp.listener;

import de.theelri12.asmp.pdc.PDCKeys;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class LeaveListener implements Listener {

    @EventHandler
    public void onLeaveEvent(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        PersistentDataContainer pdc = player.getPersistentDataContainer();

        //Write Logout Position to PDC
        pdc.set(PDCKeys.LOGOUT_WORLD, PersistentDataType.STRING, player.getLocation().getWorld().getName());
        pdc.set(PDCKeys.LOGOUT_X, PersistentDataType.DOUBLE, player.getLocation().getX());
        pdc.set(PDCKeys.LOGOUT_Y, PersistentDataType.DOUBLE, player.getLocation().getY());
        pdc.set(PDCKeys.LOGOUT_Z, PersistentDataType.DOUBLE, player.getLocation().getZ());
        pdc.set(PDCKeys.LOGOUT_PITCH, PersistentDataType.FLOAT, player.getLocation().getPitch());
        pdc.set(PDCKeys.LOGOUT_YAW, PersistentDataType.FLOAT, player.getLocation().getYaw());
        pdc.set(PDCKeys.HAS_CONTINUED, PersistentDataType.BOOLEAN, false);



    }

}
