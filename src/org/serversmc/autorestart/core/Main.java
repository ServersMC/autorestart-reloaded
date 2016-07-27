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
        Console.info("Enabled!");
        
        PluginUtils.init(this);
        PluginUtils.setupFolders();
        PluginUtils.setupFiles();
        PluginUtils.checkUpdate();
        PluginUtils.updateConfigFile();
        
        PluginUtils.registerCommands(); 
        PluginUtils.registerEvents();
        
        new Thread(timerThread).start();
    }
    
    @Override
    public void onDisable() {
        Console.info("Disabled!");
        MemoryUtils.setRestarting();
    }
    
}
