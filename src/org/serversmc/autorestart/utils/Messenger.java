package org.serversmc.autorestart.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.inventivegames.util.title.TitleManager;

public class Messenger {

	public static Config config = new Config();

	public static void popupMinutes(Integer m) {
		if (config.isPopupsEnabledMinutes()) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				TitleManager.sendTimings(player, 20, 40, 20);
				TitleManager.sendTitle(player, config.getPopupsMessagesMinutesTitle(m));
				TitleManager.sendSubTitle(player, config.getPopupsMessagesMinutesSubtitle(m));
			}
		}
	}

	public static void popupSeconds(Integer s) {
		if (config.isPopupsEnabledSeconds()) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				TitleManager.sendTimings(player, 20, 40, 20);
				TitleManager.sendTitle(player, config.getPopupsMessagesSecondsTitle(s));
				TitleManager.sendSubTitle(player, config.getPopupsMessagesSecondsSubtitle(s));
			}
		}
	}

	public static void popupTime(Player player, Integer h, Integer m, Integer s) {
		if (config.isPopupsEnabledTime()) {
			TitleManager.sendTimings(player, 20, 40, 20);
			TitleManager.sendTitle(player, config.getPopupsMessagesTimeTitle(h, m, s));
			TitleManager.sendSubTitle(player, config.getPopupsMessagesTimeSubtitle(h, m, s));
		}
	}

	public static void popupStatusStart() {
		if (config.isPopupsEnabledTime()) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				TitleManager.sendTimings(player, 20, 40, 20);
				TitleManager.sendTitle(player, config.getPopupsMessagesStatusStartTitle());
				TitleManager.sendSubTitle(player, config.getPopupsMessagesStatusStartSubtitle());
			}
		}
	}

	public static void popupStatusPause() {
		if (config.isPopupsEnabledTime()) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				TitleManager.sendTimings(player, 20, 40, 20);
				TitleManager.sendTitle(player, config.getPopupsMessagesStatusPauseTitle());
				TitleManager.sendSubTitle(player, config.getPopupsMessagesStatusPauseSubtitle());
			}
		}
	}

	public static void popupChange(Integer h, Integer m, Integer s) {
		if (config.isPopupsEnabledTime()) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				TitleManager.sendTimings(player, 20, 40, 20);
				TitleManager.sendTitle(player, config.getPopupsMessagesChangeTitle(h, m, s));
				TitleManager.sendSubTitle(player, config.getPopupsMessagesChangeSubtitle(h, m, s));
			}
		}
	}

	public static void broadcastMinutes(Integer m) {
		if (config.isBroadcastEnabledMinutes()) {
			String prefix = config.getBroadcastMessagesPrefix();
			Bukkit.broadcastMessage(prefix + config.getBroadcastMessagesMinutes(m));
		}
	}

	public static void broadcastSeconds(Integer s) {
		if (config.isBroadcastEnabledMinutes()) {
			String prefix = config.getBroadcastMessagesPrefix();
			Bukkit.broadcastMessage(prefix + config.getBroadcastMessagesSeconds(s));
		}
	}

	public static void broadcastTime(Integer h, Integer m, Integer s) {
		if (config.isBroadcastEnabledMinutes()) {
			String prefix = config.getBroadcastMessagesPrefix();
			Bukkit.broadcastMessage(prefix + config.getBroadcastMessagesTime(h, m, s));
		}
	}

	public static void broadcastStatusStart() {
		if (config.isBroadcastEnabledMinutes()) {
			String prefix = config.getBroadcastMessagesPrefix();
			Bukkit.broadcastMessage(prefix + config.getBroadcastMessagesStatusStart());
		}
	}

	public static void broadcastStatusPause() {
		if (config.isBroadcastEnabledMinutes()) {
			String prefix = config.getBroadcastMessagesPrefix();
			Bukkit.broadcastMessage(prefix + config.getBroadcastMessagesStatusPause());
		}
	}

	public static void broadcastChange(Integer h, Integer m, Integer s) {
		if (config.isBroadcastEnabledMinutes()) {
			String prefix = config.getBroadcastMessagesPrefix();
			Bukkit.broadcastMessage(prefix + config.getBroadcastMessagesChange(h, m, s));
		}
	}
	
	public static void broadcastMaxplayersCanceled() {
		String prefix = config.getBroadcastMessagesPrefix();
		Bukkit.broadcastMessage(prefix + config.getMaxplayersMessagesAlert());
	}
	
}
