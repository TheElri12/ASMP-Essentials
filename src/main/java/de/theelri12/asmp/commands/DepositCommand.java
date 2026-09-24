package de.theelri12.asmp.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import de.theelri12.asmp.hooks.VaultHook;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import net.momirealms.craftengine.bukkit.api.CraftEngineItems;
import net.momirealms.craftengine.core.util.Key;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class DepositCommand {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("deposit")
                .requires(ctx -> ctx.getSender() instanceof Player player && player.hasPermission("asmp.cmd.deposit"))
                .executes(ctx -> deposit(ctx, false))
                .then(Commands.literal("all")
                        .requires(ctx -> ctx.getSender() instanceof Player player && player.hasPermission("asmp.cmd.deposit.all"))
                        .executes(ctx -> deposit(ctx, true))
                )
                .build();
    }

    private static int deposit(CommandContext<CommandSourceStack> ctx, boolean all) {
        Player player = (Player) ctx.getSource().getSender();
        Economy eco = VaultHook.getEconomy();
        ItemStack mainHandItem = player.getInventory().getItemInMainHand();

        Key mainHandItemId = CraftEngineItems.getCustomItemId(mainHandItem);

        if (mainHandItemId != null && mainHandItemId.equals(Key.of("asmp:shard"))) {
            int shardAmount = mainHandItem.getAmount();
            if (all) {
                EconomyResponse economyResponse = eco.depositPlayer(player, shardAmount);
                if (!economyResponse.transactionSuccess()) {
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<grey>[<bold><gradient:#cba6f7:#815add>ASMP</gradient></bold>]</grey> <red>Ein Fehler ist beim einzahlen deiner <bold><gradient:#cba6f7:#815add>Shards</gradient></bold> aufgetreten: " + economyResponse.errorMessage));
                    mainHandItem.setAmount(shardAmount);
                } else {
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<grey>[<bold><gradient:#cba6f7:#815add>ASMP</gradient></bold>]</grey> <green>Du hast " + eco.format(economyResponse.amount) + "<green> <bold><gradient:#cba6f7:#815add>Shards</gradient></bold> auf dein Konto eingezahlt."));
                    player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
                }
            } else {
                mainHandItem.setAmount(shardAmount -1);
                EconomyResponse economyResponse = eco.depositPlayer(player, 1);
                if (!economyResponse.transactionSuccess()) {
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<grey>[<bold><gradient:#cba6f7:#815add>ASMP</gradient></bold>]</grey> <red>Ein Fehler ist beim einzahlen deiner <bold><gradient:#cba6f7:#815add>Shards</gradient></bold> aufgetreten: " + economyResponse.errorMessage));
                } else
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<grey>[<bold><gradient:#cba6f7:#815add>ASMP</gradient></bold>]</grey> <green>Du hast " + eco.format(economyResponse.amount) + "<green> <bold><gradient:#cba6f7:#815add>Shard</gradient></bold> auf dein Konto eingezahlt."));
            }
        } else
            player.sendMessage(MiniMessage.miniMessage().deserialize("<grey>[<bold><gradient:#cba6f7:#815add>ASMP</gradient></bold>]</grey> <red>Du musst <bold><gradient:#cba6f7:#815add>Shards</gradient></bold> in der Hand halten um sie einzuzahlen!"));

        return 1;
    }

}
