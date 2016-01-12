package org.serversmc.autorestart.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Messenger {

	public static Config config = new Config();

	private static void sendTitle(Player player, Integer delay, Integer fadein, Integer stay, Integer fadeout, String title) {
		TitleManager.sendTitle(player, fadein, stay, fadeout, "{text:\"" + title + "\"}");
	}

	private static void sendSubTitle(Player player, Integer delay, Integer fadein, Integer stay, Integer fadeout, String title) {
		TitleManager.sendSubTitle(player, fadein, stay, fadeout, "{text:\"" + title + "\"}");
	}
	
	public static void popupMinutes(Integer m) {
		if (config.isPopupsEnabledMinutes()) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				sendTitle(player,
						config.getTimingsMinutesTitleDelay(),
						config.getTimingsMinutesTitleFadein(),
						config.getTimingsMinutesTitleStay(),
						config.getTimingsMinutesTitleFadeout(),
						config.getPopupsMessagesMinutesTitle(m));
				sendSubTitle(player,
						config.getTimingsMinutesSubtitleDelay(),
						config.getTimingsMinutesSubtitleFadein(),
						config.getTimingsMinutesSubtitleStay(),
						config.getTimingsMinutesSubtitleFadeout(),
						config.getPopupsMessagesMinutesSubtitle(m));
			}
		}
	}

	public static void popupSeconds(Integer s) {
		if (config.isPopupsEnabledSeconds()) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				sendTitle(player,
						config.getTimingsSecondsTitleDelay(),
						config.getTimingsSecondsTitleFadein(),
						config.getTimingsSecondsTitleStay(),
						config.getTimingsSecondsTitleFadeout(),
						config.getPopupsMessagesSecondsTitle(s));
				sendSubTitle(player,
						config.getTimingsSecondsSubtitleDelay(),
						config.getTimingsSecondsSubtitleFadein(),
						config.getTimingsSecondsSubtitleStay(),
						config.getTimingsSecondsSubtitleFadeout(),
						config.getPopupsMessagesSecondsSubtitle(s));
			}
		}
	}

	public static void popupTime(CommandSender sender, Integer h, Integer m, Integer s) {
		if (config.isPopupsEnabledTime()) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				sendTitle(player,
						config.getTimingsTimeTitleDelay(),
						config.getTimingsTimeTitleFadein(),
						config.getTimingsTimeTitleStay(),
						config.getTimingsTimeTitleFadeout(),
						config.getPopupsMessagesTimeTitle(h, m, s));
				sendSubTitle(player,
						config.getTimingsTimeSubtitleDelay(),
						config.getTimingsTimeSubtitleFadein(),
						config.getTimingsTimeSubtitleStay(),
						config.getTimingsTimeSubtitleFadeout(),
						config.getPopupsMessagesTimeSubtitle(h, m, s));
			}
		}
	}

	public static void popupStatusStart() {
		if (config.isPopupsEnabledTime()) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				sendTitle(player,
						config.getTimingsStatusStartTitleDelay(),
						config.getTimingsStatusStartTitleFadein(),
						config.getTimingsStatusStartTitleStay(),
						config.getTimingsStatusStartTitleFadeout(),
						config.getPopupsMessagesStatusStartTitle());
				sendSubTitle(player,
						config.getTimingsStatusStartSubtitleDelay(),
						config.getTimingsStatusStartSubtitleFadein(),
						config.getTimingsStatusStartSubtitleStay(),
						config.getTimingsStatusStartSubtitleFadeout(),
						config.getPopupsMessagesStatusStartSubtitle());
			}
		}
	}

	public static void popupStatusPause() {
		if (config.isPopupsEnabledTime()) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				sendTitle(player,
						config.getTimingsStatusPauseTitleDelay(),
						config.getTimingsStatusPauseTitleFadein(),
						config.getTimingsStatusPauseTitleStay(),
						config.getTimingsStatusPauseTitleFadeout(),
						config.getPopupsMessagesStatusPauseTitle());
				sendSubTitle(player,
						config.getTimingsStatusPauseSubtitleDelay(),
						config.getTimingsStatusPauseSubtitleFadein(),
						config.getTimingsStatusPauseSubtitleStay(),
						config.getTimingsStatusPauseSubtitleFadeout(),
						config.getPopupsMessagesStatusPauseSubtitle());
			}
		}
	}

	public static void popupChange(Integer h, Integer m, Integer s) {
		if (config.isPopupsEnabledTime()) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				sendTitle(player,
						config.getTimingsChangeTitleDelay(),
						config.getTimingsChangeTitleFadein(),
						config.getTimingsChangeTitleStay(),
						config.getTimingsChangeTitleFadeout(),
						config.getPopupsMessagesChangeTitle(h, m, s));
				sendSubTitle(player,
						config.getTimingsChangeSubtitleDelay(),
						config.getTimingsChangeSubtitleFadein(),
						config.getTimingsChangeSubtitleStay(),
						config.getTimingsChangeSubtitleFadeout(),
						config.getPopupsMessagesChangeSubtitle(h, m, s));
			}
		}
	}

	public static void popupShutdown() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			TitleManager.sendTimings(player, 20, 40, 20);
			TitleManager.sendTitle(player, "Server Is Now");
			TitleManager.sendSubTitle(player, "Restarting!");
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

	public static void broadcastTime(CommandSender sender, Integer h, Integer m, Integer s) {
		if (config.isBroadcastEnabledMinutes()) {
			String prefix = config.getBroadcastMessagesPrefix();
			sender.sendMessage(prefix + config.getBroadcastMessagesTime(h, m, s));
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

	public static void broadcastShutdown() {
		String prefix = config.getBroadcastMessagesPrefix();
		Bukkit.broadcastMessage(prefix + config.getMainShutdown());
	}

}
