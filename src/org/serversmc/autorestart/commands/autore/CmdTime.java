package org.serversmc.autorestart.commands.autore;

import org.bukkit.command.CommandSender;
import org.serversmc.autorestart.core.TimerThread;
import org.serversmc.autorestart.types.AutoCommand;
import org.serversmc.autorestart.utils.Messenger;
import org.serversmc.autorestart.utils.TimeDeserializer;

public class CmdTime extends AutoCommand {

	@Override
	public void execute(CommandSender sender, String[] args) {
		Integer time = TimerThread.getCurrentTime();
		TimeDeserializer td = new TimeDeserializer(time);
		Integer h = td.getH();
		Integer m = td.getM();
		Integer s = td.getS();
		Messenger.popupTime(sender, h, m, s);
		Messenger.broadcastTime(sender, h, m, s);
	}

	@Override
	public String getLabel() {
		return "TIME";
	}

	@Override
	public String getDescription() {
		return "Shows exactly when is the next server restart.";
	}

	@Override
	public String getPermission() {
		return "autorestart.time";
	}

	@Override
	public String getUsage() {
		return "/autore time";
	}

}
