package org.serversmc.autorestart.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Messenger {
	
	private static void sendTitle(Player player, Integer delay, Integer fadein, Integer stay, Integer fadeout, String title) {
		TitleAPI.sendTitle(player, fadein, stay, fadeout, title);
	}
	
	private static void sendSubTitle(Player player, Integer delay, Integer fadein, Integer stay, Integer fadeout, String subtitle) {
		TitleAPI.sendSubTitle(player, fadein, stay, fadeout, subtitle);
	}
	
	public static void popupMinutes(Integer m) {
		if (Config.POPUPS.ENABLED.MINUTES()) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				TitleAPI.clearTitle(player);
				sendTitle(player, Config.POPUPS.MESSAGES.MINUTES.TITLE.DELAY(), Config.POPUPS.MESSAGES.MINUTES.TITLE.FADEIN(), Config.POPUPS.MESSAGES.MINUTES.TITLE.STAY(), Config.POPUPS.MESSAGES.MINUTES.TITLE.FADEOUT(), Config.POPUPS.MESSAGES.MINUTES.TITLE.TEXT().replaceAll("%m", m.toString()));
				sendSubTitle(player, Config.POPUPS.MESSAGES.MINUTES.SUBTITLE.DELAY(), Config.POPUPS.MESSAGES.MINUTES.SUBTITLE.FADEIN(), Config.POPUPS.MESSAGES.MINUTES.SUBTITLE.STAY(), Config.POPUPS.MESSAGES.MINUTES.SUBTITLE.FADEOUT(), Config.POPUPS.MESSAGES.MINUTES.SUBTITLE.TEXT().replaceAll("%m", m.toString()));
			}
		}
	}
	
	public static void popupSeconds(Integer s) {
		if (Config.POPUPS.ENABLED.SECONDS()) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				TitleAPI.clearTitle(player);
				sendTitle(player, Config.POPUPS.MESSAGES.SECONDS.TITLE.DELAY(), Config.POPUPS.MESSAGES.SECONDS.TITLE.FADEIN(), Config.POPUPS.MESSAGES.SECONDS.TITLE.STAY(), Config.POPUPS.MESSAGES.SECONDS.TITLE.FADEOUT(), Config.POPUPS.MESSAGES.SECONDS.TITLE.TEXT().replaceAll("%s", s.toString()));
				sendSubTitle(player, Config.POPUPS.MESSAGES.SECONDS.SUBTITLE.DELAY(), Config.POPUPS.MESSAGES.SECONDS.SUBTITLE.FADEIN(), Config.POPUPS.MESSAGES.SECONDS.SUBTITLE.STAY(), Config.POPUPS.MESSAGES.SECONDS.SUBTITLE.FADEOUT(), Config.POPUPS.MESSAGES.SECONDS.SUBTITLE.TEXT().replaceAll("%s", s.toString()));
			}
		}
	}
	
	public static void popupTime(CommandSender sender, Integer h, Integer m, Integer s) {
		if (Config.POPUPS.ENABLED.TIME()) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				TitleAPI.clearTitle(player);
				sendTitle(player, Config.POPUPS.MESSAGES.TIME.TITLE.DELAY(), Config.POPUPS.MESSAGES.TIME.TITLE.FADEIN(), Config.POPUPS.MESSAGES.TIME.TITLE.STAY(), Config.POPUPS.MESSAGES.TIME.TITLE.FADEOUT(), Config.POPUPS.MESSAGES.TIME.TITLE.TEXT().replaceAll("%h", h.toString()).replaceAll("%m", m.toString()).replaceAll("%s", s.toString()));
				sendSubTitle(player, Config.POPUPS.MESSAGES.TIME.SUBTITLE.DELAY(), Config.POPUPS.MESSAGES.TIME.SUBTITLE.FADEIN(), Config.POPUPS.MESSAGES.TIME.SUBTITLE.STAY(), Config.POPUPS.MESSAGES.TIME.SUBTITLE.FADEOUT(), Config.POPUPS.MESSAGES.TIME.SUBTITLE.TEXT().replaceAll("%h", h.toString()).replaceAll("%m", m.toString()).replaceAll("%s", s.toString()));
			}
		}
	}
	
	public static void popupStatusStart() {
		if (Config.POPUPS.ENABLED.STATUS()) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				TitleAPI.clearTitle(player);
				sendTitle(player, Config.POPUPS.MESSAGES.STATUS.START.TITLE.DELAY(), Config.POPUPS.MESSAGES.STATUS.START.TITLE.FADEIN(), Config.POPUPS.MESSAGES.STATUS.START.TITLE.STAY(), Config.POPUPS.MESSAGES.STATUS.START.TITLE.FADEOUT(), Config.POPUPS.MESSAGES.STATUS.START.TITLE.TEXT());
				sendSubTitle(player, Config.POPUPS.MESSAGES.STATUS.START.SUBTITLE.DELAY(), Config.POPUPS.MESSAGES.STATUS.START.SUBTITLE.FADEIN(), Config.POPUPS.MESSAGES.STATUS.START.SUBTITLE.STAY(), Config.POPUPS.MESSAGES.STATUS.START.SUBTITLE.FADEOUT(), Config.POPUPS.MESSAGES.STATUS.START.SUBTITLE.TEXT());
			}
		}
	}
	
	public static void popupStatusPause() {
		if (Config.POPUPS.ENABLED.STATUS()) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				TitleAPI.clearTitle(player);
				sendTitle(player, Config.POPUPS.MESSAGES.STATUS.PAUSE.TITLE.DELAY(), Config.POPUPS.MESSAGES.STATUS.PAUSE.TITLE.FADEIN(), Config.POPUPS.MESSAGES.STATUS.PAUSE.TITLE.STAY(), Config.POPUPS.MESSAGES.STATUS.PAUSE.TITLE.FADEOUT(), Config.POPUPS.MESSAGES.STATUS.PAUSE.TITLE.TEXT());
				sendSubTitle(player, Config.POPUPS.MESSAGES.STATUS.PAUSE.SUBTITLE.DELAY(), Config.POPUPS.MESSAGES.STATUS.PAUSE.SUBTITLE.FADEIN(), Config.POPUPS.MESSAGES.STATUS.PAUSE.SUBTITLE.STAY(), Config.POPUPS.MESSAGES.STATUS.PAUSE.SUBTITLE.FADEOUT(), Config.POPUPS.MESSAGES.STATUS.PAUSE.SUBTITLE.TEXT());
			}
		}
	}
	
	public static void popupChange(Integer h, Integer m, Integer s) {
		if (Config.POPUPS.ENABLED.CHANGE()) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				TitleAPI.clearTitle(player);
				sendTitle(player, Config.POPUPS.MESSAGES.CHANGE.TITLE.DELAY(), Config.POPUPS.MESSAGES.CHANGE.TITLE.FADEIN(), Config.POPUPS.MESSAGES.CHANGE.TITLE.STAY(), Config.POPUPS.MESSAGES.CHANGE.TITLE.FADEOUT(), Config.POPUPS.MESSAGES.CHANGE.TITLE.TEXT().replaceAll("%h", h.toString()).replaceAll("%m", m.toString()).replaceAll("%s", s.toString()));
				sendSubTitle(player, Config.POPUPS.MESSAGES.CHANGE.SUBTITLE.DELAY(), Config.POPUPS.MESSAGES.CHANGE.SUBTITLE.FADEIN(), Config.POPUPS.MESSAGES.CHANGE.SUBTITLE.STAY(), Config.POPUPS.MESSAGES.CHANGE.SUBTITLE.FADEOUT(), Config.POPUPS.MESSAGES.CHANGE.SUBTITLE.TEXT().replaceAll("%h", h.toString()).replaceAll("%m", m.toString()).replaceAll("%s", s.toString()));
			}
		}
	}
	
	public static void popupShutdown() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			TitleAPI.clearTitle(player);
			sendTitle(player, Config.POPUPS.MESSAGES.SHUTDOWN.TITLE.DELAY(), Config.POPUPS.MESSAGES.SHUTDOWN.TITLE.FADEIN(), Config.POPUPS.MESSAGES.SHUTDOWN.TITLE.STAY(), Config.POPUPS.MESSAGES.SHUTDOWN.TITLE.FADEOUT(), Config.POPUPS.MESSAGES.SHUTDOWN.TITLE.TEXT());
			sendSubTitle(player, Config.POPUPS.MESSAGES.SHUTDOWN.SUBTITLE.DELAY(), Config.POPUPS.MESSAGES.SHUTDOWN.SUBTITLE.FADEIN(), Config.POPUPS.MESSAGES.SHUTDOWN.SUBTITLE.STAY(), Config.POPUPS.MESSAGES.SHUTDOWN.SUBTITLE.FADEOUT(), Config.POPUPS.MESSAGES.SHUTDOWN.SUBTITLE.TEXT());
		}
	}
	
	public static void broadcastMinutes(Integer m) {
		if (Config.BROADCAST.ENABLED.MINUTES()) {
			String prefix = Config.BROADCAST.MESSAGES.PREFIX();
			broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + Config.BROADCAST.MESSAGES.MINUTES().replaceAll("%m", m.toString())));
		}
	}
	
	public static void broadcastSeconds(Integer s) {
		if (Config.BROADCAST.ENABLED.SECONDS()) {
			String prefix = Config.BROADCAST.MESSAGES.PREFIX();
			broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + Config.BROADCAST.MESSAGES.SECONDS().replaceAll("%s", s.toString())));
		}
	}
	
	public static void broadcastTime(CommandSender sender, Integer h, Integer m, Integer s) {
		if (Config.BROADCAST.ENABLED.TIME()) {
			String prefix = Config.BROADCAST.MESSAGES.PREFIX();
			sender.sendMessage(ChatColor.translateAlternateColorCodes('&', prefix + Config.BROADCAST.MESSAGES.TIME().replaceAll("%h", h.toString()).replaceAll("%m", m.toString()).replaceAll("%s", s.toString())));
		}
	}
	
	public static void broadcastStatusStart() {
		if (Config.BROADCAST.ENABLED.STATUS()) {
			String prefix = Config.BROADCAST.MESSAGES.PREFIX();
			broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + Config.BROADCAST.MESSAGES.STATUS.START()));
		}
	}
	
	public static void broadcastStatusPause() {
		if (Config.BROADCAST.ENABLED.STATUS()) {
			String prefix = Config.BROADCAST.MESSAGES.PREFIX();
			broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + Config.BROADCAST.MESSAGES.STATUS.PAUSE()));
		}
	}
	
	public static void broadcastChange(Integer h, Integer m, Integer s) {
		if (Config.BROADCAST.ENABLED.CHANGE()) {
			String prefix = Config.BROADCAST.MESSAGES.PREFIX();
			broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + Config.BROADCAST.MESSAGES.CHANGE().replaceAll("%h", h.toString()).replaceAll("%m", m.toString()).replaceAll("%s", s.toString())));
		}
	}
	
	public static void broadcastMaxplayersCanceled() {
		String prefix = Config.BROADCAST.MESSAGES.PREFIX();
		broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + Config.MAXPLAYERS.MESSAGES.ALERT().replaceAll("%a", Config.MAXPLAYERS.AMOUNT().toString())));
	}
	
	public static void broadcastShutdown() {
		String prefix = Config.BROADCAST.MESSAGES.PREFIX();
		broadcastMessage(ChatColor.translateAlternateColorCodes('&', prefix + Config.MAIN.SHUTDOWN().replaceAll("%d", Config.MAXPLAYERS.DELAY().toString())));
	}
	
	private static void broadcastMessage(String msg) {
		Bukkit.broadcastMessage(msg);
	}
}
