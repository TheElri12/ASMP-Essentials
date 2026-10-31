package de.theelri12.asmp.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.NamespacedKey;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class SetItemModelCommands {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("set-item-model")
                .requires(ctx -> ctx.getSender() instanceof Player player && player.hasPermission("asmp.cmd.set-item-model"))
                .then(Commands.argument("modelnamespace", StringArgumentType.word())
                        .requires(ctx -> ctx.getSender() instanceof Player player && player.hasPermission("asmp.cmd.set-item-model"))
                        .then(Commands.argument("modelname", StringArgumentType.word())
                                .requires(ctx -> ctx.getSender() instanceof Player player && player.hasPermission("asmp.cmd.set-item-model"))
                                .executes(SetItemModelCommands::setItemModel)
                        )
                )
                .build();
    }

    private static int setItemModel(CommandContext<CommandSourceStack> ctx) {
        Player player = ((Player) ctx.getSource().getSender());
        ItemStack hand = player.getInventory().getItemInMainHand();
        ItemMeta itemMeta = hand.getItemMeta();
        NamespacedKey namespacedModel = new NamespacedKey(ctx.getArgument("modelnamespace", String.class), ctx.getArgument("modelname", String.class));

        itemMeta.setItemModel(namespacedModel);
        hand.setItemMeta(itemMeta);

        return 1;
    }

}
