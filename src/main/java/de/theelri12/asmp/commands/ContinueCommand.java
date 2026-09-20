package de.theelri12.asmp.commands;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.tree.LiteralCommandNode;
import de.theelri12.asmp.pdc.PDCKeys;
import de.theelri12.asmp.worldguard.RegionCheck;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.Commands;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

public class ContinueCommand {

    public static LiteralCommandNode<CommandSourceStack> create() {
        return Commands.literal("continue")
                .executes(ContinueCommand::continuePlaying)
                .build();
    }

    private static int continuePlaying(CommandContext<CommandSourceStack> ctx) {
        Player player = (Player) ctx.getSource().getSender();
        PersistentDataContainer pdc = player.getPersistentDataContainer();

        boolean isPlayer = ctx.getSource().getSender() instanceof Player;


        if (isPlayer && player.hasPermission("asmp.cmd.continue")) {
            if (RegionCheck.isPlayerInRegion(player, "spawn")) {
                if (!pdc.getOrDefault(PDCKeys.HAS_CONTINUED, PersistentDataType.BOOLEAN, false)) {
                    World world = Bukkit.getWorld(pdc.getOrDefault(PDCKeys.LOGOUT_WORLD, PersistentDataType.STRING, "world"));

                    Location logOutLocation = new Location(
                            world,
                            pdc.getOrDefault(PDCKeys.LOGOUT_X, PersistentDataType.DOUBLE, -144.5d),
                            pdc.getOrDefault(PDCKeys.LOGOUT_Y, PersistentDataType.DOUBLE, 197d),
                            pdc.getOrDefault(PDCKeys.LOGOUT_Z, PersistentDataType.DOUBLE, -293.5d ),
                            pdc.getOrDefault(PDCKeys.LOGOUT_PITCH, PersistentDataType.FLOAT, 0f),
                            pdc.getOrDefault(PDCKeys.LOGOUT_YAW, PersistentDataType.FLOAT, 15f)
                    );
                    player.teleport(logOutLocation);
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<grey>[<bold><gradient:#cba6f7:#815add>ASMP</gradient></bold>]</grey> <green>Du wurdest erfolgreich zu deiner Logout-Position teleportiert!"));
                    pdc.set(PDCKeys.HAS_CONTINUED, PersistentDataType.BOOLEAN, true);

                    return 1;
                } else {
                    player.sendMessage(MiniMessage.miniMessage().deserialize("<grey>[<bold><gradient:#cba6f7:#815add>ASMP</gradient></bold>]</grey> <red>Du kannst diesen Befehl nur einmal nach dem du gejoint bist ausführen!"));
                    return 0;
                }
            } else {
                player.sendMessage(MiniMessage.miniMessage().deserialize("<grey>[<bold><gradient:#cba6f7:#815add>ASMP</gradient></bold>]</grey> <red>Du kannst diesen Befehl nicht ausführen, du bist nicht mehr im <gold>Spawn</gold> Bereich!"));
                return 0;
            }
        } else return 0;
    }
}
