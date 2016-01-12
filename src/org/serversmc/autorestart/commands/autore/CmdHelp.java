package org.serversmc.autorestart.commands.autore;

import java.util.ArrayList;
import java.util.List;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.serversmc.autorestart.commands.CmdAutoRestart;
import org.serversmc.autorestart.types.AutoCommand;

public class CmdHelp extends AutoCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		List<AutoCommand> commands = new ArrayList<AutoCommand>();
		for (AutoCommand command : CmdAutoRestart.commands) {
			if (sender.hasPermission(command.getPermission())) {
				commands.add(command);
			}
		}
		if (!commands.isEmpty()) {
			for (AutoCommand command : commands) {
				sender.sendMessage(command.getUsage() + " - " + command.getDescription());
			}
		}
		else {
			sender.hasPermission(ChatColor.RED + "You don't have permmision to any subcommands!");
		}
	}
	
	@Override
	public String getLabel() {
		return "HELP";
	}

	@Override
	public String getDescription() {
		return "Shows the help screen.";
	}

	@Override
	public String getPermission() {
		return "autorestart.help";
	}

	@Override
	public String getUsage() {
		return "/autore help";
	}

}
