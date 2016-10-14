package org.serversmc.autorestart.core;

import org.bukkit.plugin.java.JavaPlugin;
import org.serversmc.autorestart.utils.Console;
import org.serversmc.autorestart.utils.MemoryUtils;
import org.serversmc.autorestart.utils.PluginUtils;

public class Main extends JavaPlugin {
    
    public static Main plugin;
    
    public static String VERSION;
    
    public static TimerThread timerThread = new TimerThread();
    
    @Override
    public void onEnable() {
        PluginUtils.init(this);
        PluginUtils.setupFolders();
        PluginUtils.setupFiles();
        PluginUtils.updateConfigFile();
        PluginUtils.checkUpdate();
        
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
