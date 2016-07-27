package org.serversmc.autorestart.events;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.serversmc.autorestart.enums.ActionEnum;
import org.serversmc.autorestart.utils.Config;
import org.serversmc.autorestart.utils.MemoryUtils;
import org.serversmc.autorestart.utils.PluginUtils;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onPlayerQuit(PlayerQuitEvent event) {
		if (MemoryUtils.isRestarting()) {
			event.setQuitMessage(null);
		}
		else if (MemoryUtils.isWaiting()) {
			if (Bukkit.getOnlinePlayers().size() <= Config.MAXPLAYERS.AMOUNT()) {
                PluginUtils.shutdownServer(ActionEnum.DELAYED);
			}
		}
	}
	
}
