package de.theelri12.asmp.passes.data_types;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataAdapterContext;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.Plugin;

public class TierRewardPDC implements PersistentDataType<PersistentDataContainer, TierReward> {

    private final NamespacedKey keyType;
    private final NamespacedKey keyItemId;
    private final NamespacedKey keyCraftEngineItemId;
    private final NamespacedKey keyAmount;

    public TierRewardPDC(Plugin plugin) {
        this.keyType   = new NamespacedKey(plugin, "reward_type");
        this.keyItemId = new NamespacedKey(plugin, "reward_item_id");
        this.keyCraftEngineItemId = new NamespacedKey(plugin, "reward_craft_engine_id");
        this.keyAmount = new NamespacedKey(plugin, "reward_amount");
    }

    private static Material readMaterial(PersistentDataContainer pdc, NamespacedKey key) {
        String name = pdc.get(key, PersistentDataType.STRING);
        if (name == null) return null;
        try {
            return Material.valueOf(name);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }

    @Override
    public Class<PersistentDataContainer> getPrimitiveType() {
        return PersistentDataContainer.class;
    }

    @Override
    public Class<TierReward> getComplexType() {
        return TierReward.class;
    }

    @Override
    public PersistentDataContainer toPrimitive(TierReward reward, PersistentDataAdapterContext ctx) {
        PersistentDataContainer pdc = ctx.newPersistentDataContainer();

        pdc.set(keyType, PersistentDataType.STRING, reward.type().name());
        if (reward.item() != null) {
            pdc.set(keyItemId, PersistentDataType.STRING, reward.item().toString());
        }
        pdc.set(keyAmount, PersistentDataType.INTEGER, reward.amount());
        return pdc;
    }

    @Override
    public TierReward fromPrimitive(PersistentDataContainer pdc, PersistentDataAdapterContext ctx) {
        TierReward.RewardType type = TierReward.RewardType.valueOf(pdc.get(keyType, PersistentDataType.STRING));
        String craftEngineItem = pdc.get(keyCraftEngineItemId, PersistentDataType.STRING);
        Material item = readMaterial(pdc, keyItemId);

        int amount = pdc.getOrDefault(keyAmount, PersistentDataType.INTEGER, 0);
        return new TierReward(type, item, craftEngineItem, amount);
    }
}
