package de.theelri12.asmp.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import de.theelri12.asmp.commands.functions.CourtFunctions;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import org.bukkit.entity.Player;

public class ASMPCommand {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("asmp")
                .requires(ctx -> ctx.getSender() instanceof Player player && player.hasPermission("asmp.cmd"))
                .then(Commands.literal("court")
                        .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd.court"))
                        .then(Commands.literal("prison")
                                .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd.court.prison"))
                                .then(Commands.literal("send")
                                        .then(Commands.argument("player", ArgumentTypes.player())
                                                .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd.court.prison.send"))
                                                .executes(CourtFunctions::sendPlayerToPrison)
                                        )
                                )
                                .then(Commands.literal("remove")
                                        .then(Commands.argument("player", ArgumentTypes.player())
                                                .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd.court.prison.remove"))
                                                .executes(CourtFunctions::removePlayerFromPrison)
                                        )
                                )
                        )
                        .then(Commands.literal("accuse")
                                .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd.court.accuse"))
                                .then(Commands.argument("player", ArgumentTypes.player())
                                        .executes(CourtFunctions::accusePlayer)
                                )
                        )
                )
                .build();
    }

}
