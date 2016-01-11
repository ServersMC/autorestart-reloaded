package org.serversmc.autorestart.core;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.serversmc.autorestart.utils.Config;
import org.serversmc.autorestart.utils.MemoryUtils;
import org.serversmc.autorestart.utils.Messenger;

public class TimerThread implements Runnable {

	private static Config config = new Config();
	private static Integer time = 0;
	private static Boolean running = true;

	private Integer paused = 60;

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
					if (time <= config.getReminderTimerSeconds()) {
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
					paused = 60;
				}
				else {
					if (paused > 0) {
						paused --;
					}
					else {
						for (Player player : Bukkit.getOnlinePlayers()) {
							if (player.isOp()) {
								String prefix = config.getBroadcastMessagesPrefix();
								player.sendMessage(prefix + ChatColor.RED + "Timer is still paused!");
							}
						}
						paused = 60;
					}
				}
			}
			else {
				while (true) {
					if (Bukkit.getOnlinePlayers().size() > config.getMaxplayersAmount()) {
						if (config.isMaxplayersEnabled() && config.isMainMulticraft()) {
							MemoryUtils.setWaiting();
							Messenger.broadcastMaxplayersCanceled();
							break;
						}
					}
					Main.shutdownServer(Main.FORCED);
					break;
				}
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public static Integer getCurrentTime() {
		return time;
	}

	public static Boolean isRunning() {
		return running;
	}

	public static void startRunning() {
		running = true;
	}

	public static void stopRunning() {
		running = false;
	}

	public static void setTime(Integer time) {
		TimerThread.time = time;
	}

}
