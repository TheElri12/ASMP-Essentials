package de.theelri12.asmp.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import de.theelri12.asmp.hooks.CraftEngineHook;
import de.theelri12.asmp.hooks.VaultHook;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class WithdrawCommand {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("withdraw")
                .requires(ctx -> ctx.getSender() instanceof Player player && player.hasPermission("asmp.cmd.withdraw"))
                .then(Commands.argument("amount", IntegerArgumentType.integer(1, 64))
                        .requires(ctx -> ctx.getSender() instanceof Player player && player.hasPermission("asmp.cmd.withdraw"))
                        .executes(WithdrawCommand::withdraw)
                )
                .build();
    }

    private static int withdraw(CommandContext<CommandSourceStack> ctx) {
        Player player = (Player) ctx.getSource().getSender();
        Integer amount = ctx.getArgument("amount", Integer.class);
        Economy eco = VaultHook.getEconomy();

        EconomyResponse economyResponse = eco.withdrawPlayer(player, amount);
        if (economyResponse.transactionSuccess()) {
            ItemStack shards = CraftEngineHook.getCraftEngineItem("asmp:shard");
            shards.setAmount(amount);
            player.give(shards);
            if (economyResponse.amount == 1)
                player.sendMessage(MiniMessage.miniMessage().deserialize("<grey>[<bold><gradient:#cba6f7:#815add>ASMP</gradient></bold>]</grey> <green>Du hast " + eco.format(economyResponse.amount) + " <green><bold><gradient:#cba6f7:#815add>Shard</gradient></bold> ausgezahlt."));
            else
                player.sendMessage(MiniMessage.miniMessage().deserialize("<grey>[<bold><gradient:#cba6f7:#815add>ASMP</gradient></bold>]</grey> <green>Du hast " + eco.format(economyResponse.amount) + " <green><bold><gradient:#cba6f7:#815add>Shards</gradient></bold> ausgezahlt."));
        } else {
            player.sendMessage(MiniMessage.miniMessage().deserialize("<grey>[<bold><gradient:#cba6f7:#815add>ASMP</gradient></bold>]</grey> <red>Ein Fehler ist beim auszahlen deiner <bold><gradient:#cba6f7:#815add>Shards</gradient></bold> aufgetreten: " + economyResponse.errorMessage));
        }


        return 1;
    }

}
