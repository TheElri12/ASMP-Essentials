package de.theelri12.asmp.passes;

import de.theelri12.asmp.pdc.PDCKeys;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class PassInventories {

    private static ItemStack setPDC(ItemStack tierItem, int cost, int level) {
        tierItem.getItemMeta().getPersistentDataContainer().set(PDCKeys.ITEM_COST, PersistentDataType.INTEGER, cost);
        tierItem.getItemMeta().getPersistentDataContainer().set(PDCKeys.ITEM_LEVEL, PersistentDataType.INTEGER, level);
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
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 1));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 2));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 3));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 4));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 5));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 6));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 7));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 8));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 9));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 10));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 11));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 12));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 13));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 14));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 15));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 16));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 17));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 18));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 19));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 20));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 21));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 22));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 23));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 24));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 25));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 26));
        tiersPage1.add(setPDC(new ItemStack(Material.DIAMOND), 5, 27));

        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 1 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 2 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 3 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 4 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 5 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 6 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 7 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 8 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 9 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 10 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 11 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 12 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 13 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 14 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 15 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 16 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 17 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 18 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 19 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 20 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 21 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 22 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 23 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 24 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 25 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 26 +27));
        tiersPage2.add(setPDC(new ItemStack(Material.DIAMOND), 5, 27 +27));
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
