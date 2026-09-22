package de.theelri12.asmp.passes;

import de.theelri12.asmp.hooks.CraftEngineHook;
import de.theelri12.asmp.main.Main;
import de.theelri12.asmp.passes.data_types.TierReward;
import de.theelri12.asmp.passes.data_types.TierRewardPDC;
import de.theelri12.asmp.pdc.PDCKeys;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class PassInventories {

    private static ItemStack setPDC(ItemStack tierItem, int cost, int level, TierReward reward) {
        ItemMeta meta = tierItem.getItemMeta();
        PersistentDataContainer pdc = meta.getPersistentDataContainer();

        pdc.set(PDCKeys.ITEM_COST, PersistentDataType.INTEGER, cost);
        pdc.set(PDCKeys.ITEM_LEVEL, PersistentDataType.INTEGER, level);
        pdc.set(PDCKeys.TIER_REWARD, new TierRewardPDC(Main.getPlugin()), reward);

        tierItem.setItemMeta(meta);
        return tierItem;
    }

    public static int openShardPass(Player player) {
        String titleString = "<white>%shift_mm_-8%<image:asmp:shard_pass_bg>";
        String resolvedPlaceholder = PlaceholderAPI.setPlaceholders(null, titleString);
        final var title = MiniMessage.miniMessage().deserialize(resolvedPlaceholder);

        Inventory passInv = Bukkit.createInventory(new PassHolder(player, PassHolder.PassType.SHARD), 6 * 9, title);

        Map<Integer, List<ItemStack>> pages = new HashMap<>(Map.of());
        List<ItemStack> tiersPage1 = new ArrayList<>();
        List<ItemStack> tiersPage2 = new ArrayList<>();
        List<ItemStack> tiersPage3 = new ArrayList<>();
        List<ItemStack> tiersPage4 = new ArrayList<>();

        //Tier Items Seite 1
        tiersPage1.add(setPDC(CraftEngineHook.getCraftEngineItem("asmp:spruce_dark_oak_table"), 5, 1, TierReward.unlockable("asmp:spruce_dark_oak_table", 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 2, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 3, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 4, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 5, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 6, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 7, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 8, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 9, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 10, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 11, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 12, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 13, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 14, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 15, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 16, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 17, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 18, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 19, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 20, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 21, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 22, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 23, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 24, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 25, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 26, TierReward.item(Material.DIAMOND, 1)));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 27, TierReward.item(Material.DIAMOND, 1)));

        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 1 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 2 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 3 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 4 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 5 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 6 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 7 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 8 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 9 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 10 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 11 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 12 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 13 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 14 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 15 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 16 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 17 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 18 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 19 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 20 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 21 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 22 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 23 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 24 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 25 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 26 +27, TierReward.item(Material.DIAMOND, 1)));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 27 +27, TierReward.item(Material.DIAMOND, 1)));
        //Tier Items
        pages.put(1, tiersPage1);
        pages.put(2, tiersPage2);
        pages.put(3, tiersPage3);
        pages.put(4, tiersPage4);

        int slot = 0;
        for (ItemStack item : tiersPage1) {
            if (slot > 8 && slot < 18 || slot > 26 && slot < 36 || slot > 44 && slot < 54)
                slot += 9;
            passInv.setItem(slot, item);
            slot++;
        }

        player.openInventory(passInv);
        return 1;
    }

}
