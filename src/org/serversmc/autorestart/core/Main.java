package org.serversmc.autorestart.core;

import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.serversmc.autorestart.commands.CmdAutoRestart;
import org.serversmc.autorestart.utils.Config;
import org.serversmc.autorestart.utils.MemoryUtils;

public class Main extends JavaPlugin {

	public static final Integer TIMED = 0;
	public static final Integer FORCED = 1;
	public static final Integer DELAYED = 2;
	
	public Logger log = Bukkit.getLogger();
	
	@Override
	public void onEnable() {
		// Pre Startup Stuff
		setupFiles();
		saveConfig();
		updateConfig();
		Config.setConfig(getConfig());
		if (new Config().isMainMulticraft()) {
			MemoryUtils.setMutlticraft();
		}
		
		// Command Setup
		getCommand("autore").setExecutor(new CmdAutoRestart());
		
		// Loop Starter
		new Thread(new TimerThread()).start();
	}
	
	public void setupFiles() {
		List<File> folders = new ArrayList<File>();
		folders.add(getDataFolder());
		for (File file : folders) {
			if (!file.exists()) {
				file.mkdirs();
			}
		}
		List<String> resources = new ArrayList<String>();
		resources.add("config.yml");
		for (String resource : resources) {
			saveResource(resource, false);
		}
	}
	
	public void updateConfig() {
		Integer configVersion = getConfig().getDefaults().getInt("version");
		Integer configVersionLoaded = new Config().getVersion();
		if (configVersion != configVersionLoaded) {
			log.warning("[AutoRestart] the config has updated since the last update!");
			while (true) {
				File config = new File(getDataFolder(), "config.yml");
				Integer r = new SecureRandom().nextInt(100);
				File rename = new File(getDataFolder(), "config_bkp" + r + ".yml");
				if (!rename.exists()) {
					config.renameTo(rename);
					break;
				}
				log.warning("[AutoRestart] config file has been backed up to " + rename.getName() + "!");
			}
		}
	}
	
	public static void shutdownServer(Integer action) {
		if (action == TIMED) {
			
		}
		else if (action == FORCED) {
			
		}
		else if (action == DELAYED) {
			
		}
	}
	
}
