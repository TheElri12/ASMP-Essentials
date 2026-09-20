package de.theelri12.asmp.pdc;

import de.theelri12.asmp.passes.PassHolder;
import org.bukkit.NamespacedKey;

import java.util.EnumMap;
import java.util.Map;

public class PDCKeys {
    //Player PDCKeys
    public static final NamespacedKey HAS_CONTINUED = new NamespacedKey("asmp", "has_continued");
    public static final NamespacedKey LOGOUT_WORLD = new NamespacedKey("asmp", "logout_world");
    public static final NamespacedKey LOGOUT_X = new NamespacedKey("asmp", "logout_x");
    public static final NamespacedKey LOGOUT_Y = new NamespacedKey("asmp", "logout_y");
    public static final NamespacedKey LOGOUT_Z = new NamespacedKey("asmp", "logout_z");
    public static final NamespacedKey LOGOUT_YAW = new NamespacedKey("asmp", "logout_yaw");
    public static final NamespacedKey LOGOUT_PITCH = new NamespacedKey("asmp", "logout_pitch");

    public static final NamespacedKey WEAPON_PASS_LEVEL = new NamespacedKey("asmp", "weapon_pass_level");
    public static final NamespacedKey WEAPON_PASS_CLAIMED_TIERS = new NamespacedKey("asmp", "weapon_pass_claimed_tiers");

    public static final NamespacedKey SHARD_PASS_LEVEL = new NamespacedKey("asmp", "shard_pass_level");
    public static final NamespacedKey SHARD_PASS_CLAIMED_TIERS = new NamespacedKey("asmp", "shard_pass_claimed_tiers");

    public static final NamespacedKey TOOL_PASS_LEVEL = new NamespacedKey("asmp", "tool_pass_level");
    public static final NamespacedKey TOOL_PASS_CLAIMED_TIERS = new NamespacedKey("asmp", "tool_pass_claimed_tiers");

    public static final NamespacedKey BOW_PASS_LEVEL = new NamespacedKey("asmp", "bow_pass_level");
    public static final NamespacedKey BOW_PASS_CLAIMED_TIERS = new NamespacedKey("asmp", "bow_pass_claimed_tiers");

    public static final NamespacedKey GEM_PASS_LEVEL = new NamespacedKey("asmp", "gem_pass_level");
    public static final NamespacedKey GEM_PASS_CLAIMED_TIERS = new NamespacedKey("asmp", "gem_pass_claimed_tiers");

    public static final NamespacedKey BLOCK_PASS_LEVEL = new NamespacedKey("asmp", "block_pass_level");
    public static final NamespacedKey BLOCK_PASS_CLAIMED_TIERS = new NamespacedKey("asmp", "block_pass_claimed_tiers");

    public static final NamespacedKey UNLOCKED_BLOCKS = new NamespacedKey("asmp", "unlocked_blocks");
    public static final NamespacedKey UNLOCKED_ITEMS = new NamespacedKey("asmp", "unlocked_items");

    //Item PDCKeys
    public static final NamespacedKey ITEM_COST = new NamespacedKey("asmp", "ietm_cost");
    public static final NamespacedKey ITEM_LEVEL = new NamespacedKey("asmp", "item_level");

}