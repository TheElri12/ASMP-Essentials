package de.theelri12.asmp.item_skins.data_types;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.EntityType;

import java.util.List;

public record ItemSkin(
        String id,
        String displayName,
        Rarity rarity,
        List<Material> items,
        Type type,
        NamespacedKey model,
        List<EntityType> mobs,
        Boolean allMobs
) {

    public enum Rarity {
        COMMON,
        UNCOMMON,
        RARE,
        EPIC,
        MYTHIC,
        LEGENDARY,
        CREATIVE
    }

    public enum Type {
        MODEL,
        CRAFTENGINE
    }

}
