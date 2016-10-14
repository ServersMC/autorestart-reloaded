package org.serversmc.autorestart.core;

import java.util.List;

import org.serversmc.autorestart.enums.ActionEnum;
import org.serversmc.autorestart.objects.CalendarDeserializer;
import org.serversmc.autorestart.threads.TimerUtils;
import org.serversmc.autorestart.utils.Config;
import org.serversmc.autorestart.utils.Console;
import org.serversmc.autorestart.utils.MemoryUtils;
import org.serversmc.autorestart.utils.PluginUtils;

public class TimerThread implements Runnable {

	public Thread thread = new Thread(this);
	public List<Integer> reminders;
    public Boolean running = true;
    public Boolean timestamp = false;
    public Integer paused = 60;
    public Integer time = 0;

    public void start() {
    	thread.start();
    }
    
    @Override
    public void run() {
        TimerUtils utils = new TimerUtils(this);
        reminders = Config.REMINDER.TIMER.MINUTES();
        if (Config.MAIN.TIMESTAMP.ENABLED()) {
            CalendarDeserializer cal = new CalendarDeserializer(Config.MAIN.TIMESTAMP.TIME());
            if (cal.isCanceled()) {
                time = (int) (Config.MAIN.INTERVAL() * 3600);
                Console.err("TimeStamp is not configured properly. Back to interval restart!");
            }
            else {
            	time = cal.time;
            	timestamp = true;
            }
        }
        else {
            time = (int) (Config.MAIN.INTERVAL() * 3600);
        }
        while (!MemoryUtils.isRestarting()) {
            if (time > 0) {
                if (running) {
                    utils.minutesReminder();
                    utils.secondsReminder();
                    utils.commandExecutor();
                    time--;
                }
                else {
                    utils.pausedTimerMessage();
                }
            }
            else {
                if (utils.waitingForMaxPlayers()) {
                    continue;
                }
                PluginUtils.shutdownServer(ActionEnum.FORCED);
            }
            try {
                Thread.sleep(1000);
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    
}
