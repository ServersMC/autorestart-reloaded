package org.serversmc.autorestart.core;

import java.util.List;

public class TimerThread implements Runnable {

	private static Integer time;
	private static List<Integer> reminders;
	private static Boolean running = true;
	
	@Override
	public void run() {
		setupVariables();
		while (true) {
			
		}
	}
	
	public static void setupVariables() {
		// SETUP THANGS WITH CONFIG API
	}
	
	public static Integer getTime() {
		return time;
	}
	
	public static List<Integer> getReminders() {
		return reminders;
	}
	
	public static Boolean isRunning() {
		return running;
	}
	
	public static void flipRunning() {
		running = !running;
	}
	
}
