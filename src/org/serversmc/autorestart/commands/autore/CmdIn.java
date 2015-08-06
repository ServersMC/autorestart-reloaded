package org.serversmc.autorestart.commands.autore;

import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.serversmc.autorestart.core.TimerThread;
import org.serversmc.autorestart.types.AutoCommand;
import org.serversmc.autorestart.utils.Config;
import org.serversmc.autorestart.utils.Messenger;
import org.serversmc.autorestart.utils.TimeDeserializer;

public class CmdIn extends AutoCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		if (!new Config().isMainMulticraft()) {
			if (args.length == 2) {
				Integer time = 0;
				try {
					time = new Integer(args[1]) * 60;
				} catch (NumberFormatException ex) {
					sender.sendMessage(ChatColor.RED + "Please enter a number: /autore in <minutes>");
					return;
				}
				TimeDeserializer td = new TimeDeserializer(time);
				Integer h = td.getH();
				Integer m = td.getM();
				Integer s = td.getS();
				Messenger.popupChange(h, m, s);
				Messenger.broadcastChange(h, m, s);
				TimerThread.setTime(time);
			}
			else {
				sender.sendMessage(ChatColor.RED + "Invalid Arguments! /autore in <minutes>");
			}
		}
		else {
			sender.sendMessage(ChatColor.RED + "This feature is disabled with MutliCraft support.");
		}
	}

	@Override
	public String getLabel() {
		return "IN";
	}

	@Override
	public String getDescription() {
		return "Sets the time server will restart in.";
	}

	@Override
	public String getPermission() {
		return "autorestart.in";
	}

	@Override
	public String getUsage() {
		return "/autore in <minutes>";
	}

}
