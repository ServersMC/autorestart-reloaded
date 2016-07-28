package org.serversmc.autorestart.commands.autore;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.serversmc.autorestart.enums.ActionEnum;
import org.serversmc.autorestart.objects.AutoCommand;
import org.serversmc.autorestart.utils.Config;
import org.serversmc.autorestart.utils.PluginUtils;

public class CmdNow extends AutoCommand {
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!Config.MAIN.MULTICRAFT()) {
            PluginUtils.shutdownServer(ActionEnum.FORCED);
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
