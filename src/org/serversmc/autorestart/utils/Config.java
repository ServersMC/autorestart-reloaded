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
	public static String getMessageMinutes() {
		return convert(config.getString("config.messages.minutes"));
	}

	/**
	 * Reminder messages in seconds
	 * @return String
	 */
	public static String getMessageSeconds() {
		return convert(config.getString("config.messages.seconds"));
	}

	/**
	 * Broadcast time message
	 * @return String
	 */
	public static String getMessageTime() {
		return convert(config.getString("config.messages.time"));
	}

	/**
	 * Broadcast time message when time changes
	 * @return String
	 */
	public static String getMessageChange() {
		return convert(config.getString("config.messages.change"));
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
	public static String getPopupMessageMinutesTitle() {
		return config.getString("config.popup-messages.minutes.title");
	}
	
	/**
	 * Subtitle message for minute reminders
	 * @return String
	 */
	public static String getPopupMessageMinutesSubTitle() {
		return config.getString("config.popup-messages.minutes.subtitle");
	}
	
	/**
	 * Title message for second reminders
	 * @return String
	 */
	public static String getPopupMessageSecondsTitle() {
		return config.getString("config.popup-messages.seconds.title");
	}
	
	/**
	 * Subtitle message for second reminders
	 * @return String
	 */
	public static String getPopupMessageSecondsSubTitle() {
		return config.getString("config.popup-messages.seconds.subtitle");
	}
	
	/**
	 * Title message for private time popup
	 * @return String
	 */
	public static String getPopupMessageTimeTitle() {
		return config.getString("config.popup-messages.time.title");
	}
	
	/**
	 * Subtitle message for private time popup
	 * @return String
	 */
	public static String getPopupMessageTimeSubTitle() {
		return config.getString("config.popup-messages.time.subtitle");
	}
	
	/**
	 * Title message for time start
	 * @return String
	 */
	public static String getPopupMessageStatusStartTitle() {
		return config.getString("config.popup-messages.status.start..title");
	}
	
	/**
	 * Subtitle message for time start
	 * @return String
	 */
	public static String getPopupMessageStatusStartSubTitle() {
		return config.getString("config.popup-messages.status.start.subtitle");
	}
	
	/**
	 * Title message for time pause
	 * @return String
	 */
	public static String getPopupMessageStatusPauseTitle() {
		return config.getString("config.popup-messages.status.pause.title");
	}
	
	/**
	 * Subitle message for time pause
	 * @return String
	 */
	public static String getPopupMessageStatusPauseSubTitle() {
		return config.getString("config.popup-messages.status.pause.subtitle");
	}
	
	/**
	 * Title message for time change
	 * @return String
	 */
	public static String getPopupMessageChangeTitle() {
		return config.getString("config.popup-messages.change.title");
	}
	
	/**
	 * Subtitle message for time change
	 * @return String
	 */
	public static String getPopupMessageChangeSubTitle() {
		return config.getString("config.popup-messages.change.subtitle");
	}
	
	/**
	 * The message when the shudown limit is met
	 * @return String
	 */
	public static String getMaxPlayersMessage() {
		return convert(config.getString("config.max-players.message"));
	}
	
	/**
	 * The message when the server shudowns after player wait
	 * @return String
	 */
	public static String getMaxPlayersShutdownMessage() {
		return convert(config.getString("config.max-players.shutdown"));
	}
	
}
