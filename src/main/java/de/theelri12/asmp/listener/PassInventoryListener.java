package de.theelri12.asmp.listener;

import de.theelri12.asmp.passes.PassActions;
import de.theelri12.asmp.passes.PassHolder;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryDragEvent;

public class PassInventoryListener implements Listener {

    @EventHandler
    public void onInventoryClickEvent(InventoryClickEvent event) {
        if (!(event.getInventory().getHolder() instanceof PassHolder)) return;
        event.setCancelled(true);

        if (event.isLeftClick() && PassActions.canRedeemTier(event.getCurrentItem(), (PassHolder) event.getInventory().getHolder())) {

        }
    }

    @EventHandler
    public void onInventoryDragEvent(InventoryDragEvent event) {
        if (!(event.getInventory().getHolder() instanceof PassHolder)) return;
        event.setCancelled(true);
    }

}
