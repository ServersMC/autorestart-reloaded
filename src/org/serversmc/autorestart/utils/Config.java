package org.serversmc.autorestart.utils;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

	private static FileConfiguration config;

	private final String MAIN_INVERVAL = "config.main.inverval";
	private final String MAIN_MULTICRAFT = "config.main.multicraft";
	private final String MAIN_SHUTDOWN = "config.main.shutdown";
	
	private final String REMINDER_ENABLED_MINUTES = "config.reminder.enabled.minutes";
	private final String REMINDER_ENABLED_SECONDS = "config.reminder.enabled.seconds";
	private final String REMINDER_TIMER_MINUTES = "config.reminder.timer.minutes";
	private final String REMINDER_TIMER_SECONDS = "config.reminder.timer.seconds";
	
	private final String BROADCAST_ENABLED_MINUTES = "config.broadcast.enabled.minutes";
	private final String BROADCAST_ENABLED_SECONDS = "config.broadcast.enabled.seconds";
	private final String BROADCAST_ENABLED_TIME = "config.broadcast.enabled.time";
	private final String BROADCAST_ENABLED_STATUS = "config.broadcast.enabled.status";
	private final String BROADCAST_ENABLED_CHANGE = "config.broadcast.change";
	private final String BROADCAST_MESSAGES_PREFIX = "config.broadcast.messages.prefix";
	private final String BROADCAST_MESSAGES_MINUTES = "config.broadcast.messages.minutes";
	private final String BROADCAST_MESSAGES_SECONDS = "config.broadcast.messages.seconds";
	private final String BROADCAST_MESSAGES_TIME = "config.broadcast.messages.time";
	private final String BROADCAST_MESSAGES_STATUS_START = "config.broadcast.messages.status.start";
	private final String BROADCAST_MESSAGES_STATUS_PAUSE = "config.broadcast.messages.status.stop";
	private final String BROADCAST_MESSAGES_CHANGE = "config.broadcast.messages.change";
	
	private final String POPUPS_ENABLED_MINUTES = "config.popups.enabled.minutes";
	private final String POPUPS_ENABLED_SECONDS = "config.popups.enabled.seconds";
	private final String POPUPS_ENABLED_TIME = "config.popups.enabled.time";
	private final String POPUPS_ENABLED_STATUS = "config.popups.enabled.status";
	private final String POPUPS_ENABLED_CHANGE = "config.popups.enabled.change";
	private final String POPUPS_MESSAGES_MINUTES_TITLE = "config.popups.messages.minutes.title";
	private final String POPUPS_MESSAGES_MINUTES_SUBTITLE = "config.popups.messages.minutes.subtitle";
	private final String POPUPS_MESSAGES_SECONDS_TITLE = "config.popups.messages.seconds.title";
	private final String POPUPS_MESSAGES_SECONDS_SUBTITLE = "config.popups.messages.seconds.subtitle";
	private final String POPUPS_MESSAGES_TIME_TITLE = "config.popups.messages.time.title";
	private final String POPUPS_MESSAGES_TIME_SUBTITLE = "config.popups.messages.time.subtitle";
	private final String POPUPS_MESSAGES_STATUS_START_TITLE = "config.popups.messages.status.start.title";
	private final String POPUPS_MESSAGES_STATUS_START_SUBTITLE = "config.popups.messages.status.start.subtitle";
	private final String POPUPS_MESSAGES_STATUS_PAUSE_TITLE = "config.popups.messages.status.pause.title";
	private final String POPUPS_MESSAGES_STATUS_PAUSE_SUBTITLE = "config.popups.messages.status.pause.subtitle";
	private final String POPUPS_MESSAGES_CHANGE_TITLE = "config.popups.messages.change.title";
	private final String POPUPS_MESSAGES_CHANGE_SUBTITLE = "config.popups.messages.change.subtitle";
	
	private final String COMMANDS_ENABLED = "config.commands.enabled";
	private final String COMMANDS_TIME = "config.commands.time";
	private final String COMMANDS_COMMANDS = "config.commands.commands";
	
	private final String MAXPLAYERS_ENABLED = "config.max-players.enabled";
	private final String MAXPLAYERS_AMOUNT = "config.max-players.amount";
	private final String MAXPLAYERS_DELAY = "config.max-players.delay";
	private final String MAXPLAYERS_MESSAGES_ALERT = "config.max-players.alert";
	private final String MAXPLAYERS_MESSAGES_SHUTDOWN = "config.max-players.shutdown";
	
	private final String VERSION = "version";
	
	public static void setConfig(FileConfiguration config) {
		Config.config = config;
	}

	private String convert(String in) {
		return ChatColor.translateAlternateColorCodes('&', in);
	}
	
	private String getString(String node) {
		return convert(config.getString(node));
	}
	
	private Integer getInteger(String node) {
		return config.getInt(node);
	}
	
	private Double getDouble(String node) {
		return config.getDouble(node);
	}
	
	private Boolean getBoolean(String node) {
		return config.getBoolean(node);
	}
	
	private List<Integer> getListInteger(String node) {
		return config.getIntegerList(node);
	}
	
	private List<String> getListString(String node) {
		return config.getStringList(node);
	}

	// ============================== //
	// ============ MAIN ============ //
	// ============================== //
	
	public Double getMainInterval() {
		return getDouble(MAIN_INVERVAL);
	}
	
	public Boolean isMainMulticraft() {
		return getBoolean(MAIN_MULTICRAFT);
	}
	
	public String getMainShutdown() {
		return getString(MAIN_SHUTDOWN);
	}

	// ============================== //
	// ========== REMINDER ========== //
	// ============================== //
	
	public Boolean isReminderEnabledMinutes() {
		return getBoolean(REMINDER_ENABLED_MINUTES);
	}
	
	public Boolean isReminderEnabledSeconds() {
		return getBoolean(REMINDER_ENABLED_SECONDS);
	}
	
	public List<Integer> getReminderTimerMintues() {
		return getListInteger(REMINDER_TIMER_MINUTES);
	}
	
	public Integer getReminderTimerSeconds() {
		return getInteger(REMINDER_TIMER_SECONDS);
	}

	// ============================== //
	// ========= BROADCAST  ========= //
	// ============================== //
	
	public Boolean isBroadcastEnabledMinutes() {
		return getBoolean(BROADCAST_ENABLED_MINUTES);
	}
	
	public Boolean isBroadcastEnabledSeconds() {
		return getBoolean(BROADCAST_ENABLED_SECONDS);
	}
	
	public Boolean isBroadcastEnabledTime() {
		return getBoolean(BROADCAST_ENABLED_TIME);
	}
	
	public Boolean isBroadcastEnabledStatus() {
		return getBoolean(BROADCAST_ENABLED_STATUS);
	}
	
	public Boolean isBroadcastEnabledChange() {
		return getBoolean(BROADCAST_ENABLED_CHANGE);
	}
	
	public String getBroadcastMessagesPrefix() {
		return getString(BROADCAST_MESSAGES_PREFIX);
	}
	
	public String getBroadcastMessagesMinutes(Integer m) {
		return getString(BROADCAST_MESSAGES_MINUTES)
				.replace("%m", m.toString());
	}
	
	public String getBroadcastMessagesSeconds(Integer s) {
		return getString(BROADCAST_MESSAGES_SECONDS)
				.replace("%s", s.toString());
	}
	
	public String getBroadcastMessagesTime(Integer h, Integer m, Integer s) {
		return getString(BROADCAST_MESSAGES_TIME)
				.replace("%h", h.toString())
				.replace("%m", m.toString())
				.replace("%s", s.toString());
	}
	
	public String getBroadcastMessagesStatusStart() {
		return getString(BROADCAST_MESSAGES_STATUS_START);
	}
	
	public String getBroadcastMessagesStatusPause() {
		return getString(BROADCAST_MESSAGES_STATUS_PAUSE);
	}
	
	public String getBroadcastMessagesChange(Integer h, Integer m, Integer s) {
		return getString(BROADCAST_MESSAGES_CHANGE)
				.replace("%h", h.toString())
				.replace("%m", m.toString())
				.replace("%s", s.toString());
	}

	// ============================== //
	// =========== POPUPS =========== //
	// ============================== //
	
	public Boolean isPopupsEnabledMinutes() {
		return getBoolean(POPUPS_ENABLED_MINUTES);
	}
	
	public Boolean isPopupsEnabledSeconds() {
		return getBoolean(POPUPS_ENABLED_SECONDS);
	}
	
	public Boolean isPopupsEnabledTime() {
		return getBoolean(POPUPS_ENABLED_TIME);
	}
	
	public Boolean isPopupsEnabledStatus() {
		return getBoolean(POPUPS_ENABLED_STATUS);
	}
	
	public Boolean isPopupsEnabledChange() {
		return getBoolean(POPUPS_ENABLED_CHANGE);
	}

	public String getPopupsMessagesMinutesTitle(Integer m) {
		return getString(POPUPS_MESSAGES_MINUTES_TITLE)
				.replace("%m", m.toString());
	}
	
	public String getPopupsMessagesMinutesSubtitle(Integer m) {
		return getString(POPUPS_MESSAGES_MINUTES_SUBTITLE)
				.replace("%m", m.toString());
	}

	public String getPopupsMessagesSecondsTitle(Integer s) {
		return getString(POPUPS_MESSAGES_SECONDS_TITLE)
				.replace("%s", s.toString());
	}
	
	public String getPopupsMessagesSecondsSubtitle(Integer s) {
		return getString(POPUPS_MESSAGES_SECONDS_SUBTITLE)
				.replace("%s", s.toString());
	}

	public String getPopupsMessagesTimeTitle(Integer h, Integer m, Integer s) {
		return getString(POPUPS_MESSAGES_TIME_TITLE)
				.replace("%h", h.toString())
				.replace("%m", m.toString())
				.replace("%s", s.toString());
	}
	
	public String getPopupsMessagesTimeSubtitle(Integer h, Integer m, Integer s) {
		return getString(POPUPS_MESSAGES_TIME_SUBTITLE)
				.replace("%h", h.toString())
				.replace("%m", m.toString())
				.replace("%s", s.toString());
	}

	public String getPopupsMessagesStatusStartTitle() {
		return getString(POPUPS_MESSAGES_STATUS_START_TITLE);
	}
	
	public String getPopupsMessagesStatusStartSubtitle() {
		return getString(POPUPS_MESSAGES_STATUS_START_SUBTITLE);
	}

	public String getPopupsMessagesStatusPauseTitle() {
		return getString(POPUPS_MESSAGES_STATUS_PAUSE_TITLE);
	}
	
	public String getPopupsMessagesStatusPauseSubtitle() {
		return getString(POPUPS_MESSAGES_STATUS_PAUSE_SUBTITLE);
	}

	public String getPopupsMessagesChangeTitle(Integer h, Integer m, Integer s) {
		return getString(POPUPS_MESSAGES_CHANGE_TITLE)
				.replace("%h", h.toString())
				.replace("%m", m.toString())
				.replace("%s", s.toString());
	}
	
	public String getPopupsMessagesChangeSubtitle(Integer h, Integer m, Integer s) {
		return getString(POPUPS_MESSAGES_CHANGE_SUBTITLE)
				.replace("%h", h.toString())
				.replace("%m", m.toString())
				.replace("%s", s.toString());
	}

	// ============================== //
	// ========== COMMANDS ========== //
	// ============================== //
	
	public Boolean isCommandsEnabled() {
		return getBoolean(COMMANDS_ENABLED);
	}
	
	public Integer getCommandsTime() {
		return getInteger(COMMANDS_TIME);
	}
	
	public List<String> getCommandsList() {
		return getListString(COMMANDS_COMMANDS);
	}

	// ============================== //
	// ========= MAXPLAYERS ========= //
	// ============================== //

	public Boolean isMaxplayersEnabled() {
		return getBoolean(MAXPLAYERS_ENABLED);
	}
	
	public Integer getMaxplayersAmount() {
		return getInteger(MAXPLAYERS_AMOUNT);
	}
	
	public Integer getMaxplayersDelay() {
		return getInteger(MAXPLAYERS_DELAY);
	}
	
	public String getMaxplayersMessagesAlert() {
		return getString(MAXPLAYERS_MESSAGES_ALERT)
				.replace("%a", getMaxplayersAmount().toString());
	}
	
	public String getMaxplayersMessagesShutdown() {
		return getString(MAXPLAYERS_MESSAGES_SHUTDOWN)
				.replace("%d", getMaxplayersDelay().toString());
	}

	// ============================== //
	// ========== VERSION  ========== //
	// ============================== //
	
	public Integer getVersion() {
		return getInteger(VERSION);
	}
	
}
