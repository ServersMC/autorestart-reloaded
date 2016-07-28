package org.serversmc.autorestart.commands.autore;

import org.bukkit.command.CommandSender;
import org.serversmc.autorestart.core.Main;
import org.serversmc.autorestart.core.TimerThread;
import org.serversmc.autorestart.objects.AutoCommand;
import org.serversmc.autorestart.objects.TimeDeserializer;
import org.serversmc.autorestart.utils.Messenger;

public class CmdTime extends AutoCommand {
	
	private TimerThread timerThread = Main.timerThread;
	
	@Override
	public void execute(CommandSender sender, String[] args) {
		Integer time = timerThread.time;
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
