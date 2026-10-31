package de.theelri12.asmp.item_skins.listener;

import de.theelri12.asmp.item_skins.ItemSkins;
import de.theelri12.asmp.item_skins.data_types.ItemSkin;
import de.theelri12.asmp.pdc.PDCKeys;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Random;

public class MobKillListener implements Listener {

    @EventHandler
    public void onMobKillEvent(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            EntityType thisEntityType = event.getEntityType();

            Random random = new Random();
            if (random.nextInt(4) == 1) {
                int chance = random.nextInt(100);
                Player player = event.getEntity().getKiller();

                ItemSkin.Rarity rarity = null;

                if (chance < 30) { // COMMON 30%
                    rarity = ItemSkin.Rarity.COMMON;
                } else if (chance < 55) { // UNCOMMON 25%
                    rarity = ItemSkin.Rarity.UNCOMMON;
                } else if (chance < 72) { // RARE 17%
                    rarity = ItemSkin.Rarity.RARE;
                } else if (chance < 85) { // EPIC 13%
                    rarity = ItemSkin.Rarity.EPIC;
                } else if (chance < 95) { // MYTHIC 10%
                    rarity = ItemSkin.Rarity.MYTHIC;
                } else if (chance < 99) { // LEGENDARY 4%
                    rarity = ItemSkin.Rarity.LEGENDARY;
                } else if (chance < 100) { // CREATIVE 1%
                    rarity = ItemSkin.Rarity.CREATIVE;
                }

                setClaimableSkin(rarity, thisEntityType, player);
            }
        }
    }

    public static void setClaimableSkin(ItemSkin.Rarity rarity, EntityType entityType, Player player) {
        PersistentDataContainer pdc = player.getPersistentDataContainer();
        ItemSkin.Rarity[] all = ItemSkin.Rarity.values();

        for (int i = rarity.ordinal(); i >= 0; i--) {
            ItemSkin skin = ItemSkins.randomOf(all[i], entityType);
            if (skin != null) {
                pdc.set(PDCKeys.CLAIMABLE_SKIN, PersistentDataType.STRING, skin.id());
                return;
            }
        }
    }

}
