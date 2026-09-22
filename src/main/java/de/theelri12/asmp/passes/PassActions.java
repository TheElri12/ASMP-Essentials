package de.theelri12.asmp.passes;

import de.theelri12.asmp.hooks.VaultHook;
import de.theelri12.asmp.pdc.PDCKeys;
import de.theelri12.asmp.pdc.PassKeyMap;
import net.milkbowl.vault.economy.Economy;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.ArrayList;
import java.util.List;

public class PassActions {

    public static boolean canRedeemTier(ItemStack item, PassHolder holder) {
        Player player = holder.getPlayer();
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        PassKeyMap.PassKeys keys = PassKeyMap.get(holder.getPassType());

        int playerLevel = pdc.getOrDefault(keys.levelKey(), PersistentDataType.INTEGER, 0);
        List<Integer> claimedTiers = pdc.getOrDefault(keys.claimedTiersKey(), PersistentDataType.LIST.integers(), new ArrayList<>());

        return item.getItemMeta().getPersistentDataContainer().getOrDefault(PDCKeys.ITEM_LEVEL, PersistentDataType.INTEGER, 0) >= playerLevel && !claimedTiers.contains(playerLevel);
    }

    public static void redeemTier(ItemStack item, PassHolder holder) {
        int tierLevel = item.getItemMeta().getPersistentDataContainer().getOrDefault(PDCKeys.ITEM_LEVEL, PersistentDataType.INTEGER, 0);
        Player player = holder.getPlayer();
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        PassKeyMap.PassKeys keys = PassKeyMap.get(holder.getPassType());


    }

    public static boolean canUnlockTier(ItemStack item, PassHolder holder) {
        Player player = holder.getPlayer();
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        PassKeyMap.PassKeys keys = PassKeyMap.get(holder.getPassType());
        Economy eco = VaultHook.getEconomy();

        int playerLevel = pdc.getOrDefault(keys.levelKey(), PersistentDataType.INTEGER, 0);
        List<Integer> claimedTiers = pdc.getOrDefault(keys.claimedTiersKey(), PersistentDataType.LIST.integers(), new ArrayList<>());
        Integer itemCost = item.getItemMeta().getPersistentDataContainer().get(PDCKeys.ITEM_COST, PersistentDataType.INTEGER);

        return itemCost != null && eco.getBalance(player) >= itemCost; //Hier müssen noch andere bedingungen eingefügt werden!!!!
    }

    public static void unlockTier(ItemStack item, PassHolder holder) {

    }

}
