package de.theelri12.asmp.commands.functions;

import com.mojang.brigadier.context.CommandContext;
import com.mojang.brigadier.exceptions.CommandSyntaxException;
import de.theelri12.asmp.main.Main;
import de.theelri12.asmp.pdc.PDCKeys;
import io.papermc.paper.command.brigadier.CommandSourceStack;
import io.papermc.paper.command.brigadier.argument.resolvers.selector.PlayerSelectorArgumentResolver;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.luckperms.api.LuckPerms;
import net.luckperms.api.LuckPermsProvider;
import net.luckperms.api.model.user.User;
import net.luckperms.api.node.types.InheritanceNode;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import java.util.Base64;

public class CourtFunctions {

    public static int sendPlayerToPrison(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        PlayerSelectorArgumentResolver resolver = ctx.getArgument("player", PlayerSelectorArgumentResolver.class);
        Player target = resolver.resolve(ctx.getSource()).getFirst();
        Player player = ((Player) ctx.getSource().getSender());
        PersistentDataContainer targetPDC = target.getPersistentDataContainer();

        if (!targetPDC.getOrDefault(PDCKeys.IS_IN_PRISON, PersistentDataType.BOOLEAN, false)) {
            LuckPerms lp = LuckPermsProvider.get();
            Inventory targetInventory = target.getInventory();
            YamlConfiguration cfg = Main.getConfigManager().get();

            target.setOp(false);
            target.teleport(Main.getLocation("prison"));
            target.setGameMode(GameMode.SURVIVAL);

            lp.getUserManager().modifyUser(target.getUniqueId(), (User user) -> {
                user.data().add(InheritanceNode.builder("gefangener").build());
            });

            byte[] bytes = ItemStack.serializeItemsAsBytes(targetInventory.getContents());
            cfg.set("players." + target.getUniqueId(), Base64.getEncoder().encodeToString(bytes));
            Main.getConfigManager().save();
            targetInventory.clear();
            targetPDC.set(PDCKeys.IS_IN_PRISON, PersistentDataType.BOOLEAN, true);

            player.sendMessage(MiniMessage.miniMessage().deserialize(Main.getPrefix() + "Du hast " + target.getName() + " <green>erfolgreich ins Gefängnis gesendet."));
            return 1;
        } else {
            player.sendMessage(MiniMessage.miniMessage().deserialize(Main.getPrefix() + "<red>" + target.getName() + "<red> ist bereits im gefängnis."));
            return 0;
        }

    }

    public static int removePlayerFromPrison(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {
        PlayerSelectorArgumentResolver resolver = ctx.getArgument("player", PlayerSelectorArgumentResolver.class);
        Player target = resolver.resolve(ctx.getSource()).getFirst();
        Player player = ((Player) ctx.getSource().getSender());
        PersistentDataContainer targetPDC = target.getPersistentDataContainer();

        if (targetPDC.getOrDefault(PDCKeys.IS_IN_PRISON, PersistentDataType.BOOLEAN, false)) {
            LuckPerms lp = LuckPermsProvider.get();
            Inventory inv = target.getInventory();
            YamlConfiguration cfg = Main.getConfigManager().get();

            lp.getUserManager().modifyUser(target.getUniqueId(), (User user) -> {
                user.data().remove(InheritanceNode.builder("gefangener").build());
            });
            target.setGameMode(GameMode.SURVIVAL);
            target.teleport(Main.getLocation("spawn"));

            String b64 = cfg.getString("players." + target.getUniqueId());
            if (b64 != null) {
                ItemStack[] savedInv = ItemStack.deserializeItemsFromBytes(Base64.getDecoder().decode(b64));
                inv.setContents(savedInv);
            }
            targetPDC.set(PDCKeys.IS_IN_PRISON, PersistentDataType.BOOLEAN, false);

            player.sendMessage(MiniMessage.miniMessage().deserialize(Main.getPrefix() + "Du hast " + target.getName() + "<green> erfolgreich aus dem Gefängnis entfernt."));

            return 1;
        } else {
            player.sendMessage(MiniMessage.miniMessage().deserialize(Main.getPrefix() + "<red>" + target.getName() + "<red> ist nicht im Gefängnis."));
            return 0;
        }
    }

    public static int accusePlayer(CommandContext<CommandSourceStack> ctx) throws CommandSyntaxException {

        return 1;
    }

}
