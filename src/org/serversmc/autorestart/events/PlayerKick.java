package org.serversmc.autorestart.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.serversmc.autorestart.utils.Config;
import org.serversmc.autorestart.utils.MemoryUtils;

public class PlayerKick implements Listener {

	@EventHandler
	public void onPlayerKick(PlayerKickEvent event) {
		if (MemoryUtils.isRestarting()) {
			if (event.isCancelled()) {
				event.setCancelled(false);
			}
			Config config = new Config();
			event.setLeaveMessage(config.getMainShutdown());
			event.setReason(config.getMainShutdown());
		}
	}
	
}
