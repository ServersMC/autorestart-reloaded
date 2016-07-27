package org.serversmc.autorestart.utils;

import java.io.File;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginManager;
import org.serversmc.autorestart.commands.CmdAutoRestart;
import org.serversmc.autorestart.core.Main;
import org.serversmc.autorestart.enums.ActionEnum;
import org.serversmc.autorestart.enums.FileEnum;
import org.serversmc.autorestart.events.PlayerKick;
import org.serversmc.autorestart.events.PlayerQuit;
import me.dennis.updatecheck.core.UpdateCheck;

public class PluginUtils {
    
    public static void init(Main plugin) {
        Main.plugin = plugin;
        Main.VERSION = Main.plugin.getDescription().getVersion();
    }
    
    public static void setupFolders() {
        Main.plugin.getDataFolder().mkdirs();
    }
    
    public static void setupFiles() {
        for (FileEnum file : FileEnum.values()) {
            file.setup();
        }
        Config.setConfig(Main.plugin.getConfig());
    }
    
    public static void updateConfigFile() {
        Integer configVersion = Main.plugin.getConfig().getDefaults().getInt("version");
        Integer configVersionLoaded = Config.VERSION;
        if (configVersion != configVersionLoaded) {
            Console.warn("The config has updated since the last update!");
            while (true) {
                File config = new File(Main.plugin.getDataFolder(), "Config.yml");
                File rename = new File(Main.plugin.getDataFolder(), "config_bkp" + configVersionLoaded + ".yml");
                if (!rename.exists()) {
                    config.renameTo(rename);
                    Main.plugin.saveResource(config.toString(), true);
                    break;
                }
                Console.warn("Config file has been backed up to " + rename.getName() + "!");
            }
        }
    }
    
    public static void registerCommands() {
        Main.plugin.getCommand("autore").setExecutor(new CmdAutoRestart());
        CmdAutoRestart.setupSubCommands();
    }
    
    public static void registerEvents() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerQuit(), Main.plugin);
        pm.registerEvents(new PlayerKick(), Main.plugin);
    }
    
    public static void checkUpdate() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                UpdateCheck update = new UpdateCheck("https://gitlab.com/dennislysenko/AutoRestart-Reloaded/tags");
                String version = Main.plugin.getDescription().getVersion();
                Console.info("Checking for update!");
                Boolean b = update.needsUpdate(version);
                if (b != null) {
                    if (b) {
                        String url = "https://www.spigotmc.org/resources/autorestart.2538/";
                        Console.err("Please go to \"" + url + "\" to get the latest update! New version " + update.getVersion());
                    }
                    else {
                        Console.info(ChatColor.GREEN + "is up to date!");
                    }
                }
                else {
                    Console.warn(ChatColor.YELLOW + "We cannot check your update right now. Please check your firewall, and/or your internet connection. If this problem is still continuing, then there is a problem with the update server.");
                }
            }
        }).start();
    }
    
    public static void shutdownServer(ActionEnum action) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(100);
                }
                catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
                if (action.equals(ActionEnum.DELAYED)) {
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
                Player[] array = Bukkit.getOnlinePlayers().toArray(new Player[0]);
                for (int i = 0; i < array.length; i++) {
                    Player player = array[i];
                    try {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                            @Override
                            public void run() {
                                player.kickPlayer(ChatColor.translateAlternateColorCodes('&', Config.MAIN.SHUTDOWN()));
                            }
                        }, 1L);
                    }
                    catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                while (true) {
                    if (Bukkit.getOnlinePlayers().size() == 0) {
                        break;
                    }
                }
                Bukkit.shutdown();
            }
        }).start();
    }
}
