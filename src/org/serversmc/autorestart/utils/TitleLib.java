package org.serversmc.autorestart.utils;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import de.inventivegames.util.title.TitleManager;

public class TitleLib {

	public static void broadcastReminder(String m) {
		if (Config.isPopupMinutesEnabled()) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				TitleManager.sendTimings(player, 20, 40, 20);
				TitleManager.sendTitle(player, Config.getPopupMessageMinutesTitle(m));
				TitleManager.sendSubTitle(player, Config.getPopupMessageMinutesSubTitle(m));
			}
		}
	}

}
