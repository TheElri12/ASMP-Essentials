package de.theelri12.asmp.commands;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.entity.Player;

public class HelpCommand {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("help")
                .requires(ctx -> ctx.getSender() instanceof Player player && player.hasPermission("asmp.cmd.help"))
                .build();
    }
}
