package de.theelri12.asmp.passes.data_types;

import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.inventory.ItemStack;

public record TierReward(RewardType type, Material item, String craftEngineId, int amount) {

    public enum RewardType {
        UNLOCKABLE,
        ITEM
    }

    public static TierReward unlockable(String craftEngineId, int amount) {
        return new TierReward(RewardType.UNLOCKABLE, null, craftEngineId, amount);
    }

    public static TierReward item(Material item, int amount) {
        return new TierReward(RewardType.ITEM, item, null, amount);
    }

}
