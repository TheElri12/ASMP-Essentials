package de.theelri12.asmp.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import de.theelri12.asmp.commands.functions.CourtFunctions;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import me.clip.placeholderapi.PlaceholderAPI;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;

public class ASMPCommand {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("asmp")
                .then(Commands.literal("reload")
                        .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd.reload"))
                        .executes()
                )

                .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd"))
                .then(Commands.literal("fake-leave")
                        .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd.fake-leave"))
                        .executes(ASMPCommand::fakeLeave)
                )
                .then(Commands.literal("fake-join")
                        .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd.fake-join"))
                        .executes(ASMPCommand::fakeJoin)
                )
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
                                .requires(ctx -> ctx.getSender() instanceof Player player && player.hasPermission("asmp.cmd.court.accuse"))
                                .then(Commands.argument("player", ArgumentTypes.player())
                                        .executes(CourtFunctions::accusePlayer)
                                )
                        )
                )
                .build();
    }

    private static int reload(CommandContext<CommandSourceStack> ctx) {

        return 1;
    }

    private static int fakeJoin(CommandContext<CommandSourceStack> ctx) {
        Player player = ((Player) ctx.getSource().getSender());

        String formattedPlaceholders = PlaceholderAPI.setPlaceholders(player, "%vault_prefix%%player_name%%vault_suffix%");
        Bukkit.getServer().sendMessage(MiniMessage.miniMessage().deserialize("<gray>[<green>+<gray>] " + formattedPlaceholders));
        return 1;
    }

    private static int fakeLeave(CommandContext<CommandSourceStack> ctx) {
        Player player = ((Player) ctx.getSource().getSender());

        String formattedPlaceholders = PlaceholderAPI.setPlaceholders(player, "%vault_prefix%%player_name%%vault_suffix%");
        Bukkit.getServer().sendMessage(MiniMessage.miniMessage().deserialize("<gray>[<red>-<gray>] " + formattedPlaceholders));
        return 1;
    }

}
