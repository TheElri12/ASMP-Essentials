package de.theelri12.asmp.commands.functions;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.theelri12.asmp.main.Main;
import io.papermc.paper.command.brigadier.CommandSourceStack;
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

public class CourtFunctions {

    public static int sendPlayerToPrison(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        PlayerSelectorArgumentResolver resolver =
                ctx.getArgument("player", PlayerSelectorArgumentResolver.class);
        Player target = resolver.resolve(ctx.getSource()).getFirst();
        Player player = ((Player) ctx.getSource().getSender());
        LuckPerms lp = LuckPermsProvider.get();

        lp.getUserManager().modifyUser(target.getUniqueId(), (User user) -> {
            user.data().add(InheritanceNode.builder("gefangener").build());
        });
        target.teleport(new Location(Bukkit.getWorld("world"), -74d, 103d, 22d));
        target.setGameMode(GameMode.SURVIVAL);
        target.setOp(false);

        player.sendMessage(MiniMessage.miniMessage().deserialize(Main.getPrefix() + "Du hast " + target.getName() + " <green>erfolgreich aus ins Gefängnis gesendet."));

        return 1;
    }

    public static int removePlayerFromPrison(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        PlayerSelectorArgumentResolver resolver =
                ctx.getArgument("player", PlayerSelectorArgumentResolver.class);
        Player target = resolver.resolve(ctx.getSource()).getFirst();
        Player player = ((Player) ctx.getSource().getSender());
        LuckPerms lp = LuckPermsProvider.get();

        lp.getUserManager().modifyUser(target.getUniqueId(), (User user) -> {
            user.data().remove(InheritanceNode.builder("gefangener").build());
        });
        target.teleport(new Location(Bukkit.getWorld("world"), -144.5d, 197d, -293.5d));
        target.setGameMode(GameMode.SURVIVAL);

        player.sendMessage(MiniMessage.miniMessage().deserialize(Main.getPrefix() + "Du hast " + target.getName() + " <green>erfolgreich aus dem Gefängnis entfernt."));

        return 1;
    }

    public static int accusePlayer(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {

        return 1;
    }

}
