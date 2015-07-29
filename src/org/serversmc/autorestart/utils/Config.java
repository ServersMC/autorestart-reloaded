package org.serversmc.autorestart.utils;

import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

	private static FileConfiguration config;

	public static void setConfig(FileConfiguration config) {
		Config.config = config;
	}

	private static String convert(String in) {
		return ChatColor.translateAlternateColorCodes('&', in);
	}

	// ============================== //
	// =========== INTEGER ========== //
	// ============================== //
	
	/**
	 * Config version
	 * @return
	 */
	public static Integer getConfigVersion() {
		return config.getInt("version");
	}
	
	/**
	 * Time set by hours
	 * @return Integer
	 */
	public static Integer getTime() {
		return config.getInt("config.time");
	}

	/**
	 * Last second reminders
	 * @return Integer
	 */
	public static Integer getSecondCountdown() {
		return config.getInt("config.seconds-countdown");
	}
	
	/**
	 * The time in seconds when the commands should be called
	 * @return Integer
	 */
	public static Integer getCommandsTime() {
		return config.getInt("config.commands-time");
	}
	
	/**
	 * The limit on when the shutdown should be delayed
	 * @return Integer
	 */
	public static Integer getMaxPlayersAmount() {
		return config.getInt("config.max-players.amount");
	}
	
	/**
	 * Shutdown delay after server is wait for players to leave
	 * @return Integer
	 */
	public static Integer getMaxPlayersDelay() {
		return config.getInt("config.max-players.amount");
	}

	// ============================== //
	// =========== BOOLEAN ========== //
	// ============================== //

	/**
	 * If is using online hosting
	 * @return Boolean
	 */
	public static Boolean isMulticraftEnabled() {
		return config.getBoolean("config.multicraft");
	}

	/**
	 * If last second reminders is enabled
	 * @return Boolean
	 */
	public static Boolean isRemindingSeconds() {
		return config.getBoolean("config.remind-seconds");
	}

	/**
	 * If popups for minute broadcasts are enabled
	 * @return Boolean
	 */
	public static Boolean isPopupMinutesEnabled() {
		return config.getBoolean("config.popup-enabled.minutes");
	}

	/**
	 * If popups for seconds broadcasts are enabled
	 * @return Boolean
	 */
	public static Boolean isPopupSecondsEnabled() {
		return config.getBoolean("config.popup-enabled.seconds");
	}

	/**
	 * If popups for private time broadcasts are enabled
	 * @return Boolean
	 */
	public static Boolean isPopupTimeEnabled() {
		return config.getBoolean("config.popup-enabled.time");
	}

	/**
	 * If popups for time changed broadcasts are enabled
	 * @return Boolean
	 */
	public static Boolean isPopupChangeEnabled() {
		return config.getBoolean("config.popup-enabled.change");
	}
	
	/**
	 * If last second command execution is enabled
	 * @return
	 */
	public static Boolean isCommandsEnabled() {
		return config.getBoolean("config.commands-enabled");
	}
	
	/**
	 * If maxplayers limit enabled
	 * @return
	 */
	public static Boolean isMaxPlayeresEnabled() {
		return config.getBoolean("config.max-players.enabled");
	}

	// ============================== //
	// ============ LIST ============ //
	// ============================== //

	/**
	 * Reminder minute time stamps
	 * @return List<Integer>
	 */
	public static List<Integer> getReminders() {
		return config.getIntegerList("config.reminder");
	}
	
	/**
	 * Command list for last minute execution
	 * @return List<String>
	 */
	public static List<String> getCommands() {
		return config.getStringList("config.commands");
	}

	// ============================== //
	// =========== STRING =========== //
	// ============================== //

	/**
	 * Plugins broadcast prefix
	 * @return String
	 */
	public static String getMessagePrefix() {
		return convert(config.getString("config.messages.prefix"));
	}

	/**
	 * Reminder messages in minutes
	 * @return String
	 */
	public static String getMessageMinutes(String m) {
		return convert(config.getString("config.messages.minutes").replace("%m", m));
	}

	/**
	 * Reminder messages in seconds
	 * @return String
	 */
	public static String getMessageSeconds(String s) {
		return convert(config.getString("config.messages.seconds").replace("%s", s));
	}

	/**
	 * Broadcast time message
	 * @return String
	 */
	public static String getMessageTime(String h, String m, String s) {
		return convert(config.getString("config.messages.time").replace("%h", h).replace("%m", m).replace("%s", s));
	}

	/**
	 * Broadcast time message when time changes
	 * @return String
	 */
	public static String getMessageChange(String h, String m, String s) {
		return convert(config.getString("config.messages.change").replace("%h", h).replace("%m", m).replace("%s", s));
	}

	/**
	 * Broadcast message when time starts
	 * @return String
	 */
	public static String getMessageStatusStart() {
		return convert(config.getString("config.messages.status.start"));
	}

	/**
	 * Broadcast message when time pauses
	 * @return String
	 */
	public static String getMessageStatusPause() {
		return convert(config.getString("config.messages.status.pause"));
	}
	
	/**
	 * Title message for minute reminders
	 * @return String
	 */
	public static String getPopupMessageMinutesTitle(String m) {
		return convert(config.getString("config.popup-messages.minutes.title").replace("%m", m));
	}
	
	/**
	 * Subtitle message for minute reminders
	 * @return String
	 */
	public static String getPopupMessageMinutesSubTitle(String m) {
		return convert(config.getString("config.popup-messages.minutes.subtitle").replace("%m", m));
	}
	
	/**
	 * Title message for second reminders
	 * @return String
	 */
	public static String getPopupMessageSecondsTitle(String s) {
		return convert(config.getString("config.popup-messages.seconds.title").replace("%s", s));
	}
	
	/**
	 * Subtitle message for second reminders
	 * @return String
	 */
	public static String getPopupMessageSecondsSubTitle(String s) {
		return convert(config.getString("config.popup-messages.seconds.subtitle").replace("%s", s));
	}
	
	/**
	 * Title message for private time popup
	 * @return String
	 */
	public static String getPopupMessageTimeTitle(String h, String m, String s) {
		return convert(config.getString("config.popup-messages.time.title").replace("%h", h).replace("%m", m).replace("%s", s));
	}
	
	/**
	 * Subtitle message for private time popup
	 * @return String
	 */
	public static String getPopupMessageTimeSubTitle(String h, String m, String s) {
		return convert(config.getString("config.popup-messages.time.subtitle").replace("%h", h).replace("%m", m).replace("%s", s));
	}
	
	/**
	 * Title message for time start
	 * @return String
	 */
	public static String getPopupMessageStatusStartTitle() {
		return convert(config.getString("config.popup-messages.status.start..title"));
	}
	
	/**
	 * Subtitle message for time start
	 * @return String
	 */
	public static String getPopupMessageStatusStartSubTitle() {
		return convert(config.getString("config.popup-messages.status.start.subtitle"));
	}
	
	/**
	 * Title message for time pause
	 * @return String
	 */
	public static String getPopupMessageStatusPauseTitle() {
		return convert(config.getString("config.popup-messages.status.pause.title"));
	}
	
	/**
	 * Subitle message for time pause
	 * @return String
	 */
	public static String getPopupMessageStatusPauseSubTitle() {
		return convert(config.getString("config.popup-messages.status.pause.subtitle"));
	}
	
	/**
	 * Title message for time change
	 * @return String
	 */
	public static String getPopupMessageChangeTitle(String h, String m, String s) {
		return convert(config.getString("config.popup-messages.change.title").replace("%h", h).replace("%m", m).replace("%s", s));
	}
	
	/**
	 * Subtitle message for time change
	 * @return String
	 */
	public static String getPopupMessageChangeSubTitle(String h, String m, String s) {
		return convert(config.getString("config.popup-messages.change.subtitle").replace("%h", h).replace("%m", m).replace("%s", s));
	}
	
	/**
	 * The message when the shudown limit is met
	 * @return String
	 */
	public static String getMaxPlayersMessage(String a) {
		return convert(config.getString("config.max-players.message").replace("%a", a));
	}
	
	/**
	 * The message when the server shudowns after player wait
	 * @return String
	 */
	public static String getMaxPlayersShutdownMessage(String d) {
		return convert(config.getString("config.max-players.shutdown").replace("%d", d));
	}
	
}
