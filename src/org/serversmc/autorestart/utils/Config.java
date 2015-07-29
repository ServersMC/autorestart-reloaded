package org.serversmc.autorestart.utils;

import java.util.List;

import org.bukkit.configuration.file.FileConfiguration;

public class Config {

	private static FileConfiguration config;
	
	public static void setConfig(FileConfiguration config) {
		Config.config = config;
	}
	
	public static Integer getTime() {
		return config.getInt("config.time");
	}
	
	public static Boolean isMulticraftEnabled() {
		return config.getBoolean("config.multicraft");
	}
	
	public static Boolean isRemindingSeconds() {
		return config.getBoolean("config.remind-seconds");
	}
	
	public static List<Integer> getReminders() {
		return config.getIntegerList("config.reminder");
	}
	
	public static Integer getSecondCountdown() {
		return config.getInt("config.seconds-countdown");
	}
	
}
