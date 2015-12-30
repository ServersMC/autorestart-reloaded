package org.serversmc.autorestart.core;

import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import me.dennis.updatecheck.core.UpdateCheck;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.serversmc.autorestart.commands.CmdAutoRestart;
import org.serversmc.autorestart.events.PlayerKick;
import org.serversmc.autorestart.events.PlayerQuit;
import org.serversmc.autorestart.utils.Config;
import org.serversmc.autorestart.utils.Messenger;

public class Main extends JavaPlugin implements Runnable {

	public static final Integer FORCED = 1;
	public static final Integer DELAYED = 2;

	private static Main plugin;
	public Logger log = Bukkit.getLogger();

	@Override
	public void onEnable() {
		// Pre Startup Stuff
		setupFiles();
		saveConfig();
		Config.setConfig(getConfig());
		new Thread(this).start();
		plugin = this;

		// Register Setup
		PluginManager pm = Bukkit.getPluginManager();
		getCommand("autore").setExecutor(new CmdAutoRestart());
		pm.registerEvents(new PlayerQuit(), this);
		pm.registerEvents(new PlayerKick(), this);

		// Sub-Commands Setup
		CmdAutoRestart.setupSubCommands();

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
		if (System.getProperty("os.name").contains("Win")) {
			resources.add("start_server.bat");
		}
		else {
			resources.add("start_server.sh");
		}
		for (String resource : resources) {
			if (!new File(getDataFolder(), resource).exists()) {
				saveResource(resource, false);
			}
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
	
	@Override
	public void run() {
		checkUpdate();
	}
	
	public void checkUpdate() {
		UpdateCheck update = new UpdateCheck("https://gitlab.com/dennislysenko/AutoRestart-Reloaded/tags");
		String version = getDescription().getVersion();
		log.warning("[AutoRestart] checking for update!");
		Boolean b = update.needsUpdate(version);
		if (b != null) {
			if (b) {
				String url = "https://www.spigotmc.org/resources/autorestart.2538/";
				log.severe("[AutoRestart] please go to \"" + url + "\" to get the latest update! New version " + update.getVersion());
			}
			else {
				log.info("[AutoRestart] is up to date!");
			}
		}
		else {
			log.warning("[AutoRestart] we cannot check your update right now. Please check your firewall, and/or your internet connection. If this problem is still continuing, then there is a problem with the update server.");
		}
	}

	public static void shutdownServer(Integer action) {
		Config config = new Config();
		if (action == DELAYED) {
			try {
				Thread.sleep(1000 * config.getMaxplayersDelay());
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (World world : Bukkit.getWorlds()) {
			world.save();
		}
		Bukkit.savePlayers();
		Messenger.popupShutdown();
		Messenger.broadcastShutdown();
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.kickPlayer(config.getMainShutdown());
		}
		Bukkit.shutdown();
	}

	public static void reloadConfigStatic() {
		plugin.reloadConfig();
	}

}
