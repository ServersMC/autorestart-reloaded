package org.serversmc.autorestart.utils;

public class MemoryUtils {

	private static Boolean waiting = false;
	private static Boolean restarting = false;
	
	public static Boolean isWaiting() {
		return waiting;
	}
	
	public static void setWaiting() {
		waiting = true;
	}

	public static Boolean isRestarting() {
		return restarting;
	}
	
	public static void setRestarting() {
		restarting = true;
	}
	
}
