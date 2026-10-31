package de.theelri12.asmp.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;

public class SetSkinCommand {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("skin")
                .requires( ctx -> ctx.getSender() instanceof Player player && player.hasPermission("asmp.cmd.set-skin"))
                .then(Commands.literal("claim")
                )
                .then(Commands.literal("apply")
                        .then(Commands.argument("id", StringArgumentType.word())
                                .requires( ctx -> ctx.getSender() instanceof Player player && player.hasPermission("asmp.cmd.set-skin"))
                                .suggests((ctx, builder) -> {

                                    return builder.buildFuture();
                                })
                        )
                )
                .build();
    }

    public static int claimSkin(CommandContext<CommandSourceStack> ctx) {
        Player player = (Player) ctx.getSource().getSender();
        PersistentDataContainer pdc = player.getPersistentDataContainer();

        return 1;
    }

 }
