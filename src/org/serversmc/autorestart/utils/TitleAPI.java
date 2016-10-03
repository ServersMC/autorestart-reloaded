package org.serversmc.autorestart.utils;

import java.lang.reflect.Constructor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TitleAPI {

	public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title) {
		try {
			title = ChatColor.translateAlternateColorCodes('&', title);
			Object packetTiming = getMcClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get((Object) null);
			Object packetTitle = getMcClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get((Object) null);
			Object objectTiming = getMcClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke((Object) null, new Object[] { "{\"text\":\"" + title + "\"}" });
			Object objectTitle = getMcClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke((Object) null, new Object[] { "{\"text\":\"" + title + "\"}" });
			Constructor<?> cTiming = getMcClass("PacketPlayOutTitle").getConstructor(new Class[] { getMcClass("PacketPlayOutTitle").getDeclaredClasses()[0], getMcClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE });
			Constructor<?> cTitle = getMcClass("PacketPlayOutTitle").getConstructor(new Class[] { getMcClass("PacketPlayOutTitle").getDeclaredClasses()[0], getMcClass("IChatBaseComponent") });
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
			Object packetTiming = getMcClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get((Object) null);
			Object packetTitle = getMcClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get((Object) null);
			Object objectTiming = getMcClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke((Object) null, new Object[] { "{\"text\":\"" + subtitle + "\"}" });
			Object objectTitle = getMcClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", new Class[] { String.class }).invoke((Object) null, new Object[] { "{\"text\":\"" + subtitle + "\"}" });
			Constructor<?> cTiming = getMcClass("PacketPlayOutTitle").getConstructor(new Class[] { getMcClass("PacketPlayOutTitle").getDeclaredClasses()[0], getMcClass("IChatBaseComponent"), Integer.TYPE, Integer.TYPE, Integer.TYPE });
			Constructor<?> cTitle = getMcClass("PacketPlayOutTitle").getConstructor(new Class[] { getMcClass("PacketPlayOutTitle").getDeclaredClasses()[0], getMcClass("IChatBaseComponent") });
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

	private static Class<?> getMcClass(String name) {
		String version = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
		try {
			return Class.forName("net.minecraft.server." + version + "." + name);
		}
		catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return null;
	}

	private static void sendPacket(Player player, Object packet) {
		try {
			Object handle = player.getClass().getMethod("getHandle").invoke(player);
			Object playerConnection = handle.getClass().getField("playerConnection").get(handle);
			playerConnection.getClass().getMethod("sendPacket", getMcClass("Packet")).invoke(playerConnection, packet);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
