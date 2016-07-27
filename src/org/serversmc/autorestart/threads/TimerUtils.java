package org.serversmc.autorestart.threads;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.serversmc.autorestart.core.Main;
import org.serversmc.autorestart.core.TimerThread;
import org.serversmc.autorestart.utils.Config;
import org.serversmc.autorestart.utils.MemoryUtils;
import org.serversmc.autorestart.utils.Messenger;

public class TimerUtils {

    private TimerThread thread;
    
    public TimerUtils(TimerThread thread) {
        this.thread = thread;
    }
    
    public void minutesReminder() {
        for (Integer reminder : thread.reminders) {
            if (thread.time == reminder * 60) {
                Messenger.popupMinutes(reminder);
                Messenger.broadcastMinutes(reminder);
            }
        }
    }
    
    public void secondsReminder() {
        if (thread.time <= Config.REMINDER.TIMER.SECONDS()) {
            Messenger.popupSeconds(thread.time);
            Messenger.broadcastSeconds(thread.time);
        }
    }

    public void commandExecutor() {
        if (Config.COMMANDS.ENABLED()) {
            if (thread.time == Config.COMMANDS.TIME()) {
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
    }

    public void pausedTimerMessage() {
        if (thread.paused > 0) {
            thread.paused--;
        }
        else {
            for (Player player : Bukkit.getOnlinePlayers()) {
                if (player.isOp()) {
                    String prefix = Config.BROADCAST.MESSAGES.PREFIX();
                    player.sendMessage(prefix + ChatColor.RED + "Timer is still paused!");
                }
            }
            thread.paused = 60;
        }
    }

    public boolean waitingForMaxPlayers() {
        if (Config.MAXPLAYERS.ENABLED() && !Config.MAIN.MULTICRAFT()) {
            if (Bukkit.getOnlinePlayers().size() > Config.MAXPLAYERS.AMOUNT()) {
                MemoryUtils.setWaiting();
                Messenger.popupStatusPause();
                Messenger.broadcastMaxplayersCanceled();
                return true;
            }
        }
        return false;
    }
    
}
