package de.theelri12.asmp.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import io.papermc.paper.command.brigadier.argument.ArgumentTypes;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class CourtCommand {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("court")
                .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd.court"))
                .then(Commands.literal("prison")
                        .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd.court.prison"))
                        .then(Commands.literal("send")
                                .then(Commands.argument("player", ArgumentTypes.player())
                                        .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd.court.prison.send"))
                                        .executes(CourtCommand::sendPlayerToPrison)
                                )
                        )
                        .then(Commands.literal("remove")
                                .then(Commands.argument("player", ArgumentTypes.player())
                                        .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd.court.prison.remove"))
                                        .executes(CourtCommand::removePlayerFromPrison)
                                )
                        )
                )
                .then(Commands.literal("anklagen")
                        .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd.court.anklagen"))
                        .then(Commands.argument("player", ArgumentTypes.player()))
                )

                .build();
    }

    private static int sendPlayerToPrison(CommandContext<CommandSourceStack> ctx) {
        Player selectedPlayer = (Player) ctx.getArgument("player", Player.class);
        Player player = ((Player) ctx.getSource().getSender());
        LuckPerms lp = LuckPermsProvider.get();

        lp.getUserManager().modifyUser(selectedPlayer.getUniqueId(), (User user) -> {
            user.data().add(InheritanceNode.builder("gefangener").build());
        });
        selectedPlayer.teleport(new Location(Bukkit.getWorld("world"), -74d, 103d, 22d));
        selectedPlayer.setGameMode(GameMode.SURVIVAL);
        selectedPlayer.setOp(false);

        player.sendMessage(MiniMessage.miniMessage().deserialize("<grey>[<bold><gradient:#cba6f7:#815add>ASMP</gradient></bold>]</grey> <green>Du hast " + selectedPlayer.getName() + " <green>erfolgreich aus ins Gefängnis gesendet."));

        return 1;
    }

    private static int removePlayerFromPrison(CommandContext<CommandSourceStack> ctx) {
        Player selectedPlayer = (Player) ctx.getArgument("player", Player.class);
        Player player = ((Player) ctx.getSource().getSender());
        LuckPerms lp = LuckPermsProvider.get();

        lp.getUserManager().modifyUser(selectedPlayer.getUniqueId(), (User user) -> {
            user.data().remove(InheritanceNode.builder("gefangener").build());
        });
        selectedPlayer.teleport(new Location(Bukkit.getWorld("world"), -144.5d, 197d, -293.5d));
        selectedPlayer.setGameMode(GameMode.SURVIVAL);

        player.sendMessage(MiniMessage.miniMessage().deserialize("<grey>[<bold><gradient:#cba6f7:#815add>ASMP</gradient></bold>]</grey> <green>Du hast " + selectedPlayer.getName() + " <green>erfolgreich aus dem Gefängnis entfernt."));

        return 1;
    }

}
