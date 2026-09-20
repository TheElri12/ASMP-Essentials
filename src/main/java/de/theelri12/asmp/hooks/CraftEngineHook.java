package de.theelri12.asmp.hooks;

import net.momirealms.craftengine.bukkit.api.CraftEngineItems;
import net.momirealms.craftengine.core.util.Key;
import org.bukkit.inventory.ItemStack;

import java.util.Objects;

public class CraftEngineHook {

    public static ItemStack getCraftEngineItem(String id) {
        return Objects.requireNonNull(CraftEngineItems.byId(Key.of(id))).buildBukkitItem();
    }

}
