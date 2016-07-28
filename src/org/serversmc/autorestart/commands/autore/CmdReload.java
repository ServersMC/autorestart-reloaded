package org.serversmc.autorestart.commands.autore;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.serversmc.autorestart.core.Main;
import org.serversmc.autorestart.core.TimerThread;
import org.serversmc.autorestart.objects.AutoCommand;
import org.serversmc.autorestart.utils.Config;

public class CmdReload extends AutoCommand {

	private TimerThread timerThread = Main.timerThread;
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!Config.MAIN.MULTICRAFT() && !timerThread.timestamp) {
			Main.plugin.reloadConfig();
	        Config.setConfig(Main.plugin.getConfig());;
			sender.sendMessage(ChatColor.GRAY + "Config has been reloaded!");
		}
		else if (timerThread.timestamp) {
			sender.sendMessage(ChatColor.RED + "This feature is disabled with TimeStamp feature!");
		}
		else {
			sender.sendMessage(ChatColor.RED + "This feature is disabled with MutliCraft support.");
		}
	}

	@Override
	public String getLabel() {
		return "RELOAD";
	}

	@Override
	public String getDescription() {
		return "Reloads the config file.";
	}

	@Override
	public String getPermission() {
		return "autorestart.reload";
	}

	@Override
	public String getUsage() {
		return "/autore reload";
	}

}
