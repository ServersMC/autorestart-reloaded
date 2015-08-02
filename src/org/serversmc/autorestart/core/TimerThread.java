package org.serversmc.autorestart.core;

import java.util.List;

import org.bukkit.Bukkit;
import org.serversmc.autorestart.utils.Config;
import org.serversmc.autorestart.utils.MemoryUtils;
import org.serversmc.autorestart.utils.Messenger;

public class TimerThread implements Runnable {

	private static Config config = new Config();
	private static Integer time = 0;
	private static Boolean running = true;

	@Override
	public void run() {
		List<Integer> reminders = config.getReminderTimerMintues();
		time = (int) (config.getMainInterval() * 3600);
		while (true) {
			if (time > 0) {
				if (running) {
					// Minute Reminders
					for (Integer reminder : reminders) {
						if (time == reminder * 60) {
							Messenger.popupMinutes(reminder);
							Messenger.broadcastMinutes(reminder);
						}
					}

					// Second Reminders
					if (time == config.getReminderTimerSeconds()) {
						Messenger.popupSeconds(time);
						Messenger.broadcastSeconds(time);
					}

					// Commands Exector
					if (config.isCommandsEnabled()) {
						if (time == config.getCommandsTime()) {
							for (String command : config.getCommandsList()) {
								if (command.startsWith("/")) {
									command = command.replaceFirst("/", "");
								}
								Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
							}
						}
					}

					time--;
				}
				else {
					while (true) {
						if (Bukkit.getOnlinePlayers().size() > config.getMaxplayersAmount()) {
							if (config.isMaxplayersEnabled()) {
								MemoryUtils.setWaiting();
								Messenger.broadcastMaxplayersCanceled();
								break;
							}
						}
						Main.shutdownServer(Main.TIMED);
					}
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
