package com.thefishnextdoor.jump.command;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;

import com.thefishnextdoor.jump.DoubleJump;
import com.thefishnextdoor.jump.PlayerProfile;

import net.md_5.bungee.api.ChatColor;

public class DoubleJumpCommand implements CommandExecutor, TabCompleter {

    public final String RELOAD_PERMISSION = "doublejump.reload";

    private final DoubleJump instance;

    public DoubleJumpCommand(DoubleJump instance) {
        this.instance = instance;
    }

    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args.length == 1) {
            ArrayList<String> list = new ArrayList<>();
            list.add("enable");
            list.add("disable");
            if (sender.hasPermission(RELOAD_PERMISSION)) {
                list.add("reload");
            }
            return list;
        }
        return null;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(ChatColor.RED + "You must be a player to use this command.");
            return true;
        }
        Player player = (Player) sender;
        PlayerProfile playerProfile = PlayerProfile.get(player);
        boolean enableDoubleJump;
        if (args.length == 0) {
            enableDoubleJump = !playerProfile.wantsDoubleJump();
        }
        else {
            if (args[0].equalsIgnoreCase("enable")) {
                enableDoubleJump = true;
            }
            else if (args[0].equalsIgnoreCase("disable")) {
                enableDoubleJump = false;
            }
            else if (args[0].equalsIgnoreCase("reload") && player.hasPermission(RELOAD_PERMISSION)) {
                instance.load();
                player.sendMessage(ChatColor.GREEN + "Plugin Reloaded");
                return true;
            }
            else {
                return false;
            }
        }
        playerProfile.setWantsDoubleJump(enableDoubleJump);
        if (enableDoubleJump) {
            player.sendMessage(ChatColor.GREEN + "Double jump enabled");
        }
        else {
            player.sendMessage(ChatColor.DARK_GREEN + "Double jump disabled");
        }
        return true;
    }
}