package org.serversmc.autorestart.core;

import java.util.List;

import org.bukkit.Bukkit;
import org.serversmc.autorestart.utils.Config;
import org.serversmc.autorestart.utils.TitleLib;

public class TimerThread implements Runnable {

	private static Integer time = 0;
	private static Boolean running = true;
	
	@Override
	public void run() {
		List<Integer> reminders = Config.getReminders();
		time = Config.getTime();
		while (true) {
			if (time > 0) {
				if (running) {
					for (Integer reminder : reminders) {
						Integer minute = time / 60;
						if (minute == reminder) {
							TitleLib.broadcastReminder(minute.toString());
							Bukkit.broadcastMessage(Config.getMessageMinutes(minute.toString()));
						}
					}
					time--;
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
