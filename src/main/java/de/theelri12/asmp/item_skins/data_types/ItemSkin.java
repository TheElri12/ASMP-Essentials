package de.theelri12.asmp.item_skins.data_types;

import org.bukkit.Material;
import org.bukkit.entity.EntityType;

import java.util.List;

public record ItemSkin(
        String id,
        String displayName,
        Rarity rarity,
        List<Material> items,
        Type type,
        List<EntityType> mob,
        Boolean allMobs
) {

    public enum Rarity {
        COMMON,
        UNCOMMON,
        RARE,
        SUPERRARE,
        EPIC,
        MYTHIC,
        LEGENDARY,
        CREATIVE
    }

    public enum Type {
        MODEL,
        CRAFTENGINE
    }

    public enum Mob {
        MODEL,
        CRAFTENGINE
    }

}
