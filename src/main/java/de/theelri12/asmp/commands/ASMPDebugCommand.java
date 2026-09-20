package de.theelri12.asmp.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import de.theelri12.asmp.passes.PassManager;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ASMPDebugCommand {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("asmp-debug")
                .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd.debug"))
                .executes(
                        ctx -> {
                            CommandSender sender = ctx.getSource().getSender();
                            sender.sendMessage("Dies ist der AmethystSMP Essentials Debug-Command.");
                            return 1;
                        }
                )
                .then(Commands.literal("passmanager"))
                .executes(
                        ctx -> {
                            if (ctx.getSource().getSender() instanceof Player player)
                                player.sendMessage("Int:" + PassManager.getPassLevel(player, "shard"));
                            return 1;
                        }
                )
                .build();
    }

}
