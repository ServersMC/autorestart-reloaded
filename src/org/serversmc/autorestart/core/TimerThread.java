package org.serversmc.autorestart.core;

import java.util.List;
import org.serversmc.autorestart.enums.ActionEnum;
import org.serversmc.autorestart.threads.TimerUtils;
import org.serversmc.autorestart.utils.Config;
import org.serversmc.autorestart.utils.MemoryUtils;
import org.serversmc.autorestart.utils.PluginUtils;

public class TimerThread implements Runnable {

	public Thread thread = new Thread(this);
    public Boolean running = true;
    public Integer paused = 60;
    public List<Integer> reminders;
    public Integer time = 0;

    public void start() {
    	thread.start();
    }
    
    @Override
    public void run() {
        TimerUtils utils = new TimerUtils(this);
        reminders = Config.REMINDER.TIMER.MINUTES();
        if (Config.MAIN.TIMESTAMP.ENABLED()) {
        	
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
