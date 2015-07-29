package org.serversmc.autorestart.core;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	@Override
	public void onEnable() {
		new Thread(new TimerThread()).start();
		
	}
	
}
