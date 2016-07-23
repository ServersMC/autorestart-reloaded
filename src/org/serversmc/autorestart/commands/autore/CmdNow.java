package org.serversmc.autorestart.commands.autore;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.serversmc.autorestart.core.Main;
import org.serversmc.autorestart.types.AutoCommand;
import org.serversmc.autorestart.utils.Config;

public class CmdNow extends AutoCommand {
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!Config.MAIN.MULTICRAFT()) {
			Main.shutdownServer(Main.FORCED);
		}
		else {
			sender.sendMessage(ChatColor.RED + "This feature is disabled with MutliCraft support.");
		}
	}

	@Override
	public String getLabel() {
		return "NOW";
	}

	@Override
	public String getDescription() {
		return "Restarts the server now!";
	}

	@Override
	public String getPermission() {
		return "autorestart.now";
	}

	@Override
	public String getUsage() {
		return "/autore now";
	}

}
