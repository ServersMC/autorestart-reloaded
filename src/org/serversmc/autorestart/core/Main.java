package org.serversmc.autorestart.core;

import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import org.serversmc.autorestart.utils.Console;
import org.serversmc.autorestart.utils.MemoryUtils;
import org.serversmc.autorestart.utils.PluginUtils;

public class Main extends JavaPlugin {
    
    public static Main plugin;
    
    public static String VERSION;
    
    public Logger log = Bukkit.getLogger();
    
    public static TimerThread timerThread = new TimerThread();
    
    @Override
    public void onEnable() {
        PluginUtils.init(this);
        PluginUtils.setupFolders();
        PluginUtils.setupFiles();
        PluginUtils.checkUpdate();
        PluginUtils.updateConfigFile();
        
        PluginUtils.registerCommands(); 
        PluginUtils.registerEvents();
        
        timerThread.start();
        Console.info("Enabled!");
    }
    
    @Override
    public void onDisable() {
        MemoryUtils.setRestarting();
        Console.info("Disabled!");
    }
    
}
