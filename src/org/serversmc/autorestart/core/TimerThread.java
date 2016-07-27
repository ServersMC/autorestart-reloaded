package org.serversmc.autorestart.core;

import java.util.List;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.serversmc.autorestart.utils.Config;
import org.serversmc.autorestart.utils.MemoryUtils;
import org.serversmc.autorestart.utils.Messenger;

public class TimerThread implements Runnable {
    
    private Boolean running = true;
    private Integer paused = 60;
    public Integer time = 0;
    
    @Override
    public void run() {
        List<Integer> reminders = Config.REMINDER.TIMER.MINUTES();
        time = (int) (Config.MAIN.INTERVAL() * 3600);
        while (!MemoryUtils.isRestarting()) {
            if (time > 0) {
                if (running) {
                    // Minute Reminders
                    for (Integer reminder : reminders) {
                        if (time == reminder * 60) {
                            Messenger.popupMinutes(reminder);
                            Messenger.broadcastMinutes(reminder);
                        }
                    }
                    
                    // Second Reminders
                    if (time <= Config.REMINDER.TIMER.SECONDS()) {
                        Messenger.popupSeconds(time);
                        Messenger.broadcastSeconds(time);
                    }
                    
                    // Commands Executor
                    if (Config.COMMANDS.ENABLED()) {
                        if (time == Config.COMMANDS.TIME()) {
                            Bukkit.getScheduler().scheduleSyncDelayedTask(Main.plugin, new Runnable() {
                                @Override
                                public void run() {
                                    for (String command : Config.COMMANDS.COMMANDSLIST()) {
                                        if (command.startsWith("/")) {
                                            command = command.replaceFirst("/", "");
                                        }
                                        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command);
                                    }
                                }
                            }, 0L);
                        }
                    }
                    time--;
                    paused = 60;
                }
                else {
                    if (paused > 0) {
                        paused--;
                    }
                    else {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            if (player.isOp()) {
                                String prefix = Config.BROADCAST.MESSAGES.PREFIX();
                                player.sendMessage(prefix + ChatColor.RED + "Timer is still paused!");
                            }
                        }
                        paused = 60;
                    }
                }
            }
            else {
                if (Config.MAXPLAYERS.ENABLED() && !Config.MAIN.MULTICRAFT()) {
                    if (Bukkit.getOnlinePlayers().size() > Config.MAXPLAYERS.AMOUNT()) {
                        MemoryUtils.setWaiting();
                        Messenger.popupStatusPause();
                        Messenger.broadcastMaxplayersCanceled();
                        continue;
                    }
                }
                Main.shutdownServer(Main.FORCED);
            }
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
    public Integer getCurrentTime() {
        return time;
    }
    
    public Boolean isRunning() {
        return running;
    }
    
    public void startRunning() {
        running = true;
    }
    
    public void stopRunning() {
        running = false;
    }
    
    public void setTime(Integer time) {
        this.time = time;
    }
    
}
