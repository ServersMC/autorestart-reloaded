package org.serversmc.autorestart.objects;

import java.util.Calendar;

import org.bukkit.ChatColor;
import org.serversmc.autorestart.utils.Console;

public class CalendarDeserializer {

	public Integer time = null;
	
	public CalendarDeserializer(String time) {
		String[] vars = time.split(":");
		Integer timeHour = null;
		Integer timeMinute = null;
		try { timeHour = new Integer(vars[0]); }
		catch (NumberFormatException ex) { Console.err("TimeStamp HOUR is not a number! " + ChatColor.WHITE + vars[0]); return; }
		try { timeMinute = new Integer(vars[1]); }
		catch (NumberFormatException ex) { Console.err("TimeStamp MINUTE is not a number! " + ChatColor.WHITE + vars[0]); return; }
		if (!(timeHour >= 0 && timeHour <= 23)) {
			Console.err("TimeStamp HOUR can only be " + ChatColor.WHITE + "0" + ChatColor.RED + " or " + ChatColor.WHITE + "00" + ChatColor.RED + " to " + ChatColor.WHITE + "23" + ChatColor.RED + "!");
			return;
		}
		if (!(timeMinute >= 0 && timeMinute <= 59)) {
			Console.err("TimeStamp MINUTE can only be " + ChatColor.WHITE + "0" + ChatColor.RED + " or " + ChatColor.WHITE + "00" + ChatColor.RED + " to " + ChatColor.WHITE + "59" + ChatColor.RED + "!");
			return;
		}
		Calendar now = Calendar.getInstance();
		Calendar restart = Calendar.getInstance();
		restart.set(Calendar.HOUR_OF_DAY, timeHour);
		restart.set(Calendar.MINUTE, timeMinute);
		restart.set(Calendar.SECOND, 0);
		if (now.compareTo(restart) > 0) {
			restart.set(Calendar.DAY_OF_YEAR, restart.get(Calendar.DAY_OF_YEAR) + 1);
		}
		this.time = (int) (((restart.getTimeInMillis() - now.getTimeInMillis())) / 1000);
	}
	
	public Boolean isCanceled() {
		if (time == null) {
			return true;
		}
		return false;
	}
	
}
