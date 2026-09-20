package de.theelri12.asmp.passes;

import de.theelri12.asmp.pdc.PassKeyMap;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class PassActions {

    public static int getLevelOfSlot(int page, int slot) {
        int level;

        if (slot <= 8)
            level = slot + 1;
        else if (slot >= 18 && slot <= 26)
            level = slot - 9 + 1;
        else if (slot >= 36 && slot <= 44)
            level = slot - 18 + 1;
        else
            return -1;

        return level + ((page - 1) * 27);
    }

    public static boolean canRedeemTier(int slot, PassHolder holder) {
        int page = holder.getPage();
        Player player = holder.getPlayer();
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        PassKeyMap.PassKeys keys = PassKeyMap.get(holder.getPassType());

        int level = pdc.getOrDefault(keys.levelKey(), PersistentDataType.INTEGER, 0);
        List<Integer> claimedTiers = pdc.getOrDefault(keys.claimedTiersKey(), PersistentDataType.LIST.integers(), new ArrayList<>());

        return getLevelOfSlot(page, slot) == level && !claimedTiers.contains(level);
    }

    public static void redeemTier(int slot, PassHolder holder) {
        int levelOfSlot = getLevelOfSlot(holder.getPage(), slot);
        Player player = holder.getPlayer();
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        PassKeyMap.PassKeys keys = PassKeyMap.get(holder.getPassType());


    }

    public static boolean canUnlockTier(int slot, PassHolder holder) {
        int page = holder.getPage();
        Player player = holder.getPlayer();
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        PassKeyMap.PassKeys keys = PassKeyMap.get(holder.getPassType());

        int level = pdc.getOrDefault(keys.levelKey(), PersistentDataType.INTEGER, 0);
        List<Integer> claimedTiers = pdc.getOrDefault(keys.claimedTiersKey(), PersistentDataType.LIST.integers(), new ArrayList<>());

        return getLevelOfSlot(page, slot) == level && !claimedTiers.contains(level);
    }

    public static void unlockTier(int slot, PassHolder holder) {

    }

}
