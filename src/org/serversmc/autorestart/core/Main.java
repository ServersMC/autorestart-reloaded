package org.serversmc.autorestart.core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.serversmc.autorestart.commands.CmdAutoRestart;
import org.serversmc.autorestart.events.PlayerKick;
import org.serversmc.autorestart.events.PlayerQuit;
import org.serversmc.autorestart.utils.Config;
import org.serversmc.autorestart.utils.MemoryUtils;
import org.serversmc.autorestart.utils.Messenger;
import me.dennis.updatecheck.core.UpdateCheck;

public class Main extends JavaPlugin implements Runnable {

    private static Main plugin;
    
	public static final Integer FORCED = 1;
	public static final Integer DELAYED = 2;

    public static String VERSION;

	public Logger log = Bukkit.getLogger();
	
	public static TimerThread timerThread = new TimerThread();

	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[AutoRestart] Enabled!");
		// Pre-Startup Stuff
		plugin = this;
		setupFiles();
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
		new Thread(timerThread).start();
	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[AutoRestart] Disabled!");
		MemoryUtils.setRestarting();
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
			Boolean b = true;
			if (resource.equals("config.yml")) {
				b = false;
			}
			saveResource(resource, b);
		}
	}

	public void updateConfig() {
		Integer configVersion = getConfig().getDefaults().getInt("version");
		Integer configVersionLoaded = Config.VERSION;
		if (configVersion != configVersionLoaded) {
			log.warning("[AutoRestart] the config has updated since the last update!");
			while (true) {
				File config = new File(getDataFolder(), "Config.yml");
				File rename = new File(getDataFolder(), "config_bkp" + configVersionLoaded + ".yml");
				if (!rename.exists()) {
					config.renameTo(rename);
					saveResource(config.toString(), true);
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
		if (action == DELAYED) {
			try {
				Thread.sleep(1000 * Config.MAXPLAYERS.DELAY());
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
		MemoryUtils.setRestarting();
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
				player.kickPlayer(Config.MAIN.SHUTDOWN());
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
