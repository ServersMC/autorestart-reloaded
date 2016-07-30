package org.serversmc.autorestart.commands.autore;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.serversmc.autorestart.core.Main;
import org.serversmc.autorestart.core.TimerThread;
import org.serversmc.autorestart.enums.ActionEnum;
import org.serversmc.autorestart.objects.AutoCommand;
import org.serversmc.autorestart.utils.PluginUtils;

public class CmdNow extends AutoCommand {
	
	private TimerThread timerThread = Main.timerThread;
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!timerThread.timestamp) {
            PluginUtils.shutdownServer(ActionEnum.FORCED);
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
