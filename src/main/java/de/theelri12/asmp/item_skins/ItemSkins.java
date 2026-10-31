package de.theelri12.asmp.item_skins;


import de.theelri12.asmp.item_skins.data_types.ItemSkin;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

import javax.naming.Name;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class ItemSkins {

    private static final Map<String, ItemSkin> itemSkins = new HashMap<>();
    private static final List<String> skinIDS = new ArrayList<>();

    public static List<String> getIDS() { return skinIDS; }
    public static Map<String, ItemSkin> get() { return itemSkins; }

    public static void loadFromConfig(FileConfiguration config) {
        ConfigurationSection section = config.getConfigurationSection("item-skins");
        Set<String> skinKeys = section != null ? section.getKeys(false) : Set.of();

        skinIDS.clear();
        skinIDS.addAll(skinKeys);
        itemSkins.clear();
        for (String skinID : skinKeys) {
            // String List to EntityType List
            List<String> entityStringList = section.getStringList(skinID + ".mobs");
            List<EntityType> entityEnumList = new ArrayList<>();
            for (String currentString : entityStringList) { entityEnumList.add(EntityType.valueOf(currentString.toUpperCase())); }

            // String List to Material List
            List<String> materialStringList = section.getStringList(skinID + ".items");
            List<Material> materialEnumList = new ArrayList<>();
            for (String currentString : materialStringList) { materialEnumList.add(Material.valueOf(currentString.toUpperCase())); }

            // String to NamespacedKey
            NamespacedKey model = NamespacedKey.fromString(Objects.requireNonNull(section.getString(skinID + ".skin.model")));

            ItemSkin itemSkin = new ItemSkin(skinID,
                    section.getString(skinID + ".displayname"),
                    ItemSkin.Rarity.valueOf(Objects.requireNonNull(section.getString(skinID + ".rarity")).toUpperCase()),
                    materialEnumList,
                    ItemSkin.Type.valueOf(Objects.requireNonNull(section.getString(skinID + ".skin.type")).toUpperCase()),
                    model,
                    entityEnumList,
                    section.getBoolean(skinID + ".all-mobs")
            );
            itemSkins.put(skinID, itemSkin);

        }
    }

    public static ItemSkin randomOf(ItemSkin.Rarity rarity, EntityType mob) {
        List<ItemSkin> randomFilteredItemSkin = itemSkins.values().stream()
                .filter(skin -> skin.rarity() == rarity)
                .filter(skin -> skin.allMobs() || skin.mobs().contains(mob))
                .toList();

        if (randomFilteredItemSkin.isEmpty()) return null;
        return randomFilteredItemSkin.get(ThreadLocalRandom.current().nextInt(randomFilteredItemSkin.size()));
    }

}
