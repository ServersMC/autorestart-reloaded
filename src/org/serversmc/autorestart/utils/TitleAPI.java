package org.serversmc.autorestart.utils;

import java.lang.reflect.Constructor;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TitleAPI {

	public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title) {
		try {
			title = ChatColor.translateAlternateColorCodes('&', title);
			Object packetTiming = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get((Object) null);
			Object packetTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get((Object) null);
			Object objectTiming = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke((Object) null, new Object[] { "{\"text\":\"" + title + "\"}" });
			Object objectTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke((Object) null, new Object[] { "{\"text\":\"" + title + "\"}" });
			Constructor<?> cTiming = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE });
			Constructor<?> cTitle = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent") });
			sendPacket(player, cTiming.newInstance(new Object[] { packetTiming, objectTiming, fadeIn, stay, fadeOut }));
			sendPacket(player, cTitle.newInstance(new Object[] { packetTitle, objectTitle }));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void sendSubTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String subtitle) {
		try {
			subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
			Object packetTiming = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get((Object) null);
			Object packetTitle = getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get((Object) null);
			Object objectTiming = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke((Object) null, new Object[] { "{\"text\":\"" + subtitle + "\"}" });
			Object objectTitle = getNMSClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke((Object) null, new Object[] { "{\"text\":\"" + subtitle + "\"}" });
			Constructor<?> cTiming = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE });
			Constructor<?> cTitle = getNMSClass("PacketPlayOutTitle").getConstructor(new Class[] { getNMSClass("PacketPlayOutTitle").getDeclaredClasses()[0], getNMSClass("IChatBaseComponent") });
			sendPacket(player, cTiming.newInstance(new Object[] { packetTiming, objectTiming, fadeIn, stay, fadeOut }));
			sendPacket(player, cTitle.newInstance(new Object[] { packetTitle, objectTitle }));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void clearTitle(Player player) {
		sendTitle(player, 0, 0, 0, "");
		sendSubTitle(player, 0, 0, 0, "");
	}

	public static Class<?> getNMSClass(String name) {
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		try {
			return Class.forName("net.minecraft.server." + version + "." + name);
		}
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	public static void sendPacket(Player player, Object packet) {
		try {
			Object handle = player.getClass().getMethod("getHandle").invoke(player);
			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
			playerConnection.getClass().getMethod("sendPacket", getNMSClass("Packet")).invoke(playerConnection, packet);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
