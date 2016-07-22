package org.serversmc.autorestart.core;

import java.io.File;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.serversmc.autorestart.commands.CmdAutoRestart;
import org.serversmc.autorestart.events.PlayerKick;
import org.serversmc.autorestart.events.PlayerQuit;
import org.serversmc.autorestart.utils.Config;
import org.serversmc.autorestart.utils.Messenger;
import me.dennis.updatecheck.core.UpdateCheck;

public class Main extends JavaPlugin implements Runnable {

	public static final Integer FORCED = 1;
	public static final Integer DELAYED = 2;
 	
	public static final String TITLEAPI_V = "2.1.3";
 	public static final String PACKETLISTENERAPI_V = "3.4.4";
 	public static final String PLAYERVERSION_V = "1.2.3";

	public static final String TITLE_V = "";
	
	public static String VERSION;

	private static Main plugin;
	public Logger log = Bukkit.getLogger();

	@Override
	public void onEnable() {
		// Pre-Startup Stuff
		plugin = this;
		setupFiles();
		saveConfig();
		Config.setConfig(getConfig());
		new Thread(this).start();
		updateConfig();
		VERSION = getDescription().getVersion();

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
		resources.add("instructions.txt");
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
		
		List<String> versions = new ArrayList<String>();
		versions.add(PACKETLISTENERAPI_V);
		versions.add(PLAYERVERSION_V);
		versions.add(TITLEAPI_V);
		List<String> dependies = new ArrayList<String>();
		dependies.add("PacketListenerApi.jar");
		dependies.add("PlayerVersion.jar");
		dependies.add("TitleAPI.jar");
		List<String> pluginUpdate = new ArrayList<String>();
		Boolean disable = false;
		for (int i = 0; i < dependies.size(); i++) {
			String dependy = dependies.get(i);
			saveResource(dependy, true);
			File pluginFile = new File("plugins/" + dependy);
			File dataFile = new File(getDataFolder(), dependy);
			Plugin plugin = Bukkit.getPluginManager().getPlugin(dependy.replaceFirst(".jar", ""));
			System.out.println(dependy + " " + pluginFile.exists());
			if (pluginFile.exists()) {
				try {
					if (!plugin.getDescription().getVersion().equalsIgnoreCase(versions.get(i))) {
						pluginUpdate.add(dependy);
						disable = true;
					}
				} catch (NullPointerException ex) {}
			}
			else {
				dataFile.renameTo(pluginFile);
				disable = true;
			}
		}
		if (disable) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				@Override
				public void run() {
					Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[AutoRestart] Disabled... Please restart plugin!");
					for (String pluginUpdate : pluginUpdate) {
						Plugin plugin = Bukkit.getPluginManager().getPlugin(pluginUpdate.replaceFirst(".jar", ""));
						Bukkit.getPluginManager().disablePlugin(plugin);
						Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[AutoRestart] Please Delete " + pluginUpdate + " to Update File!");
					}
					getPluginLoader().disablePlugin(plugin);
				}
			}, 0);
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
		Bukkit.getConsoleSender().sendMessage(ChatColor.GOLD + "[AutoRestart] checking for update!");
		Boolean b = update.needsUpdate(version);
		if (b != null) {
			if (b) {
				String url = "https://www.spigotmc.org/resources/autorestart.2538/";
				Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[AutoRestart] please go to \"" + url + "\" to get the latest update! New version " + update.getVersion());
			}
			else {
				Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[AutoRestart] is up to date!");
			}
		}
		else {
			Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[AutoRestart] we cannot check your update right now. Please check your firewall, and/or your internet connection. If this problem is still continuing, then there is a problem with the update server.");
		}
	}

	public static void shutdownServer(Integer action) {
		Config config = new Config();
		if (action == DELAYED) {
			try {
				Thread.sleep(1000 * config.getMaxplayersDelay());
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (World world : Bukkit.getWorlds()) {
			if (!world.isAutoSave()) {
				world.save();
			}
		}
		Bukkit.savePlayers();
		Messenger.popupShutdown();
		Messenger.broadcastShutdown();
		for (Player player : Bukkit.getOnlinePlayers()) {
			try {
				Boolean wasOp = false;
				if (player.isOp()) {
					player.setOp(false);
					wasOp = true;
				}
				player.kickPlayer(config.getMainShutdown());
				if (wasOp) {
					player.setOp(true);
				}
			}
			catch (IllegalStateException ex) {
			}
		}
		Bukkit.shutdown();
	}

	public static void reloadConfigStatic() {
		plugin.reloadConfig();
	}

}
