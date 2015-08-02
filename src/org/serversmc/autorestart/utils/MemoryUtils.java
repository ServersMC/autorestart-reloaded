package org.serversmc.autorestart.utils;

public class MemoryUtils {

	private static Boolean waiting = false;
	
	public static Boolean isWaiting() {
		return waiting;
	}
	
	public static void setWaiting() {
		waiting = true;
	}
	
	private static Boolean multicraft = false;
	
	public static Boolean isMutlicraft() {
		return multicraft;
	}
	
	public static void setMutlticraft() {
		multicraft = true;
	}
	
}
