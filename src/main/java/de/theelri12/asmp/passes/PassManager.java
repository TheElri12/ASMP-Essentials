package de.theelri12.asmp.passes;

import de.theelri12.asmp.main.Main;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class PassManager {

    static PersistentDataContainer pdc;

    public static int getPassLevel(Player player, String pass) {
        pdc = player.getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey(Main.getPlugin(), pass + "_pass_pass_level");
        return pdc.getOrDefault(key, PersistentDataType.INTEGER, 0);
    }

    public static List<Integer> getClaimedTiers(Player player, String pass) {
        pdc = player.getPersistentDataContainer();

        NamespacedKey key = new NamespacedKey(Main.getPlugin(), pass + "_pass_claimed_tiers");
        return pdc.getOrDefault(key, PersistentDataType.LIST.integers(), new ArrayList<>());
    }

}
