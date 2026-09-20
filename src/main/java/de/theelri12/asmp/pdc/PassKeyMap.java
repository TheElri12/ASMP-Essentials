package de.theelri12.asmp.pdc;

import de.theelri12.asmp.passes.PassHolder;
import org.bukkit.NamespacedKey;

import java.util.EnumMap;
import java.util.Map;

public class PassKeyMap {

    public record PassKeys(
            NamespacedKey levelKey,
            NamespacedKey claimedTiersKey
    ) {}

    private static final Map<PassHolder.PassType, PassKeys> KEYS = new EnumMap<>(PassHolder.PassType.class);

    static {
        KEYS.put(PassHolder.PassType.SHARD, new PassKeys(PDCKeys.SHARD_PASS_LEVEL, PDCKeys.SHARD_PASS_CLAIMED_TIERS));
        KEYS.put(PassHolder.PassType.BLOCK, new PassKeys(PDCKeys.BLOCK_PASS_LEVEL, PDCKeys.BLOCK_PASS_CLAIMED_TIERS));
        KEYS.put(PassHolder.PassType.BOW, new PassKeys(PDCKeys.BOW_PASS_LEVEL, PDCKeys.BOW_PASS_CLAIMED_TIERS));
        KEYS.put(PassHolder.PassType.GEM, new PassKeys(PDCKeys.GEM_PASS_LEVEL, PDCKeys.GEM_PASS_CLAIMED_TIERS));
        KEYS.put(PassHolder.PassType.TOOL, new PassKeys(PDCKeys.TOOL_PASS_LEVEL, PDCKeys.TOOL_PASS_CLAIMED_TIERS));
        KEYS.put(PassHolder.PassType.WEAPON, new PassKeys(PDCKeys.WEAPON_PASS_LEVEL, PDCKeys.WEAPON_PASS_CLAIMED_TIERS));
    }

    public static PassKeys get(PassHolder.PassType type) {
        return KEYS.get(type);
    }

}
