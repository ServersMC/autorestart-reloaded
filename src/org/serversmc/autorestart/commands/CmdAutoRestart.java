package org.serversmc.autorestart.commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.serversmc.autorestart.commands.autore.CmdHelp;
import org.serversmc.autorestart.commands.autore.CmdIn;
import org.serversmc.autorestart.commands.autore.CmdNow;
import org.serversmc.autorestart.commands.autore.CmdPause;
import org.serversmc.autorestart.commands.autore.CmdReload;
import org.serversmc.autorestart.commands.autore.CmdStart;
import org.serversmc.autorestart.commands.autore.CmdTime;
import org.serversmc.autorestart.core.Main;
import org.serversmc.autorestart.objects.AutoCommand;

public class CmdAutoRestart implements CommandExecutor {

	public static List<AutoCommand> commands = new ArrayList<AutoCommand>();
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage(ChatColor.RED + "AutoRestart " + ChatColor.GRAY + "- v" + Main.VERSION);
		if (args.length == 0) {
			new CmdHelp().execute(sender, args);
		}
		else {
			AutoCommand target = null;
			for (AutoCommand command : commands) {
				if (command.getLabel().equalsIgnoreCase(args[0])) {
					target = command;
					break;
				}
			}
			if (target != null) {
				if (target.getPermission() != null) {
					if (!sender.hasPermission(target.getPermission())) {
						sender.sendMessage(ChatColor.RED + "You don't have permission to use this subcommand!");
						return true;
					}
				}
				target.execute(sender, args);
			}
			else {
				sender.sendMessage(ChatColor.RED + "Subcommand not found! Type /autore help");
			}
		}
		return true;
	}
	
	public static void setupSubCommands() {
		commands.add(new CmdHelp());
		commands.add(new CmdTime());
		commands.add(new CmdNow());
		commands.add(new CmdReload());
		commands.add(new CmdIn());
		commands.add(new CmdStart());
		commands.add(new CmdPause());
	}

}
