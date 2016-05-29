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
	
	public static final String TITLEAPI_V = "2.1.2";
	public static final String PACKETLISTENERAPI_V = "3.4.1";
	public static final String PLAYERVERSION_V = "1.2.3";
	
	public static String VERSION;

	private static Main plugin;
	public Logger log = Bukkit.getLogger();
	
	@Override
	public void onEnable() {
		// Pre-Startup Stuff
		setupFiles();
		saveConfig();
		Config.setConfig(getConfig());
		new Thread(this).start();
		updateConfig();
		plugin = this;
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
		resources.add("TitleAPI.jar");
		resources.add("PacketListenerAPI.jar");
		resources.add("PlayerVersion.jar");
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
		// Installation for Dependencies
		File title = new File(getDataFolder(), "TitleAPI.jar");
		File packet = new File(getDataFolder(), "PacketListenerAPI.jar");
		File player = new File(getDataFolder(), "PlayerVersion.jar");
		
		Plugin pluginTitle = null;
		Plugin pluginPacket = null;
		Plugin pluginPlayer = null;
		Boolean updateRestart = false;
		// Installation Check
		try {
			(pluginTitle = Bukkit.getPluginManager().getPlugin("TitleAPI")).isEnabled();
			(pluginPacket = Bukkit.getPluginManager().getPlugin("PacketListenerApi")).isEnabled();
			(pluginPlayer = Bukkit.getPluginManager().getPlugin("PlayerVersion")).isEnabled();
			// Update Check
			if (!pluginTitle.getDescription().getVersion().equals(TITLEAPI_V)) {
				title.renameTo(new File(title.getAbsoluteFile(), "../../TitleAPI.jar"));
				updateRestart = true;
			}
			if (!pluginPacket.getDescription().getVersion().equals(PACKETLISTENERAPI_V)) {
				packet.renameTo(new File(packet.getAbsoluteFile(), "../../PacketListenerAPI.jar"));
				updateRestart = true;
			}
			if (!pluginPlayer.getDescription().getVersion().equals(PLAYERVERSION_V)) {
				player.renameTo(new File(player.getAbsoluteFile(), "../../PlayerVersion.jar"));
				updateRestart = true;
			}
		} catch(NullPointerException ex) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				@Override
				public void run() {
					Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[AutoRestart] ------ ------ ------   IMPORTANT MESSAGE   ------ ------ ------");
					Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[AutoRestart] PLEASE RESTART SERVER TO FINISH INSTALLATION FOR AUTORESTART!!!");
					Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[AutoRestart] ------ ------ ------   IMPORTANT MESSAGE   ------ ------ ------");
					title.renameTo(new File(title.getAbsoluteFile(), "../../TitleAPI.jar"));
					packet.renameTo(new File(packet.getAbsoluteFile(), "../../PacketListenerAPI.jar"));
					player.renameTo(new File(player.getAbsoluteFile(), "../../PlayerVersion.jar"));
				}
			}, 0);
		}
		if (updateRestart) {
			Bukkit.getScheduler().scheduleSyncDelayedTask(this, new Runnable() {
				@Override
				public void run() {
					Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[AutoRestart] ------ ------ ------   IMPORTANT MESSAGE   ------ ------ ------");
					Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[AutoRestart]    PLEASE RESTART SERVER TO FINISH UPDATE FOR AUTORESTART!!!");
					Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[AutoRestart] ------ ------ ------   IMPORTANT MESSAGE   ------ ------ ------");
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
			} catch (InterruptedException e) {
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
			} catch (IllegalStateException ex) {}
		}
		Bukkit.shutdown();
	}

	public static void reloadConfigStatic() {
		plugin.reloadConfig();
	}

}
