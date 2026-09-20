package de.theelri12.asmp.commands;

import com.mojang.brigadier.arguments.StringArgumentType;
import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.suggestion.SuggestionsBuilder;
import com.mojang.brigadier.tree.LiteralCommandNode;
import de.theelri12.asmp.pdc.PDCKeys;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class ApplyCommand {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("apply")
                .requires( ctx -> ctx.getSender() instanceof Player player && player.hasPermission("asmp.cmd.apply"))
                .executes(ctx -> {
                    ctx.getSource().getSender().sendMessage(MiniMessage.miniMessage().deserialize(""));
                    return 1;
                })
                .then(Commands.literal("item")
                        .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd.apply.item"))
                        .then(Commands.argument("item", StringArgumentType.word())
                                .suggests((ctx, builder) -> {
                                    for (String id : getAvailableItems(ctx.getSource())) {
                                        if (id.toLowerCase().startsWith(builder.getRemainingLowerCase())) {
                                            builder.suggest(id);
                                        }
                                    }
                                    return builder.buildFuture();
                                })
                        )
                        .executes(ctx -> {
                            ctx.getSource().getSender().sendMessage(MiniMessage.miniMessage().deserialize(""));
                            return 1;
                        })
                )
                .then(Commands.literal("block")
                        .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd.apply.block"))
                        .then(Commands.argument("item", StringArgumentType.word())
                                .suggests((ctx, builder) -> {
                                    for (String id : getAvailableBlocks(ctx.getSource())) {
                                        if (id.toLowerCase().startsWith(builder.getRemainingLowerCase())) {
                                            builder.suggest(id);
                                        }
                                    }
                                    return builder.buildFuture();
                                })
                        )
                        .executes(ctx -> {
                            ctx.getSource().getSender().sendMessage(MiniMessage.miniMessage().deserialize(""));
                            return 1;
                        })
                )
                .build();
    }

    private static List<String> getAvailableItems(CommandSourceStack src) {
        return ((Player) src.getSender()).getPersistentDataContainer().getOrDefault(PDCKeys.UNLOCKED_ITEMS, PersistentDataType.LIST.strings(), new ArrayList<>());
    }

    private static List<String> getAvailableBlocks(CommandSourceStack src) {
        return ((Player) src.getSender()).getPersistentDataContainer().getOrDefault(PDCKeys.UNLOCKED_BLOCKS, PersistentDataType.LIST.strings(), new ArrayList<>());
    }

    private static int applyItem(CommandContext<CommandSourceStack> ctx) {
        String item = ctx.getArgument("item", String.class);
        Player player = (Player) ctx.getSource().getSender();
        PersistentDataContainer pdc = player.getPersistentDataContainer();

        if (pdc.getOrDefault(PDCKeys.UNLOCKED_ITEMS, PersistentDataType.LIST.strings(), new ArrayList<>()).contains(item)) {

            return 1;
        } else return 0;
    }

    private static int applyBlock(CommandContext<CommandSourceStack> ctx) {
        String item = ctx.getArgument("block", String.class);



        return 1;
    }

 }
