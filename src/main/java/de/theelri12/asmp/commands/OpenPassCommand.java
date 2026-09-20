package de.theelri12.asmp.commands;

import com.mojang.brigadier.tree.LiteralCommandNode;
import de.theelri12.asmp.passes.PassInventories;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import org.bukkit.entity.Player;

public class OpenPassCommand {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("open-pass")
                .requires(ctx -> ctx.getSender() instanceof Player player && player.hasPermission("asmp.cmd.open-pass"))
                .then(Commands.literal("shard")
                        .requires(ctx -> ctx.getSender().hasPermission("asmp.cmd.open-pass.shard"))
                        .executes(ctx -> PassInventories.openShardPass((Player) ctx.getSource().getSender()))
                )
                .build();
    }

}
