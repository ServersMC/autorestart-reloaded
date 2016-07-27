package org.serversmc.autorestart.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class Console {
    
    public static void info(String s) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.GREEN + "[AutoRestart] " + s);
    }
    
    public static void warn(String s) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.YELLOW + "[AutoRestart] " + s);
    }
    
    public static void err(String s) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[AutoRestart] " + s);
    }
    
}
