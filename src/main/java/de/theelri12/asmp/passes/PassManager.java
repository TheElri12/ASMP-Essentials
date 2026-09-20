package de.theelri12.asmp.passes;

import de.theelri12.asmp.pdc.PDCKeys;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class PassManager {

    static PersistentDataContainer pdc;

    public static int getPassLevel(Player player, String pass) {
        pdc = player.getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey("asmp", pass + "_pass_claimed_tiers");
        return pdc.getOrDefault(key, PersistentDataType.INTEGER, 0);
    }

    public static int getClaimedTiers(Player player, String pass) {
        pdc = player.getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey("asmp", pass + "_pass_claimed_tiers");
        return pdc.getOrDefault(key, PersistentDataType.INTEGER, 0);
    }

}
