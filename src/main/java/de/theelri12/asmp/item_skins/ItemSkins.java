package de.theelri12.asmp.item_skins;


import de.theelri12.asmp.item_skins.data_types.ItemSkin;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.EntityType;

import java.util.*;

public class ItemSkins {

    private static final Map<String, ItemSkin> itemSkins = new HashMap<>();
    private static final List<String> skinIDS = new ArrayList<>();

    public static List<String> getIDS() { return skinIDS; }
    public static Map<String, ItemSkin> get() { return itemSkins; }

    public static void loadFromConfig(FileConfiguration config) {
        ConfigurationSection section = config.getConfigurationSection("item-skins");
        Set<String> skinKeys = section != null ? section.getKeys(false) : Set.of();

        itemSkins.clear();
        skinIDS.addAll(skinKeys);
        for (String skinID : skinKeys) {
            // String List to EntityType List
            List<String> entityStringList = section.getStringList(skinID + ".mobs");
            List<EntityType> entityEnumList = new ArrayList<>();
            for (String currentString : entityStringList) { entityEnumList.add(EntityType.valueOf(currentString)); }

            // String List to Material List
            List<String> materialStringList = section.getStringList(skinID + ".items");
            List<Material> materialEnumList = new ArrayList<>();
            for (String currentString : materialStringList) { materialEnumList.add(Material.valueOf(currentString)); }

            ItemSkin itemSkin = new ItemSkin(skinID,
                    (String) section.get(skinID + ".displayname"),
                    (ItemSkin.Rarity) section.get(skinID + ".rarity"),
                    materialEnumList,
                    (ItemSkin.Type) section.get(skinID + ".type"),
                    entityEnumList,
                    (Boolean) section.get(skinID + ".all-mobs")
            );
            itemSkins.put(skinID, itemSkin);
        }
    }

}
