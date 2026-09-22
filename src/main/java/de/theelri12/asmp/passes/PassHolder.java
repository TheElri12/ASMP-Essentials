package de.theelri12.asmp.passes;

import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;

public class PassHolder implements InventoryHolder {

    public enum PassType {
        SHARD,
        WEAPON,
        BLOCK,
        BOW,
        TOOL,
        GEM
    }

    private final Player player;
    private final PassType passType;
    private int page;
    private Inventory inventory;

    public PassHolder(Player player, PassType passType) {
        this(player, passType, 0);
    }

    public PassHolder(Player player, PassType passType, int page) {
        this.player = player;
        this.passType = passType;
        this.page = page;
    }

    public Player getPlayer() {
        return player;
    }

    public PassType getPassType() {
        return passType;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public void setInventory(Inventory inventory) { // neu
        this.inventory = inventory;
    }

    @Override
    public Inventory getInventory() {
        return inventory;
    }

}
