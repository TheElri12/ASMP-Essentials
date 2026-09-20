package de.theelri12.asmp.passes.unlockables;

import de.theelri12.asmp.hooks.CraftEngineHook;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.Map;

public class Blocks {

    private static final Map<String, ItemStack> BLOCKS = new HashMap<>();

    static {
        BLOCKS.put("test", CraftEngineHook.getCraftEngineItem("asmp:testblock"));
    }

    public static ItemStack getBlockByID(String id) {
        return BLOCKS.get(id);
    }

}
