package org.serversmc.autorestart.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.serversmc.autorestart.core.Main;
import org.serversmc.autorestart.utils.Config;
import org.serversmc.autorestart.utils.MemoryUtils;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		Config config = new Config();
		if (MemoryUtils.isRestarting()) {
			event.setQuitMessage(config.getMainShutdown());
		}
		else if (MemoryUtils.isWaiting()) {
			if (Bukkit.getOnlinePlayers().size() <= config.getMaxplayersAmount()) {
				Main.shutdownServer(Main.DELAYED);
			}
		}
	}
	
}
