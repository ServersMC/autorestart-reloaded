package org.serversmc.autorestart.utils;

import java.lang.reflect.Constructor;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class TitleAPI {

	public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title) {
		try {
			title = ChatColor.translateAlternateColorCodes('&', title);
			Object packetTiming = getMcClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get(null);
			Object packetTitle = getMcClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TITLE").get(null);
			Object objectTiming = getMcClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + title + "\"}");
			Object objectTitle = getMcClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + title + "\"}");
			//Constructor<?> cTiming = getMcClass("PacketPlayOutTitle").getConstructor(getMcClass("PacketPlayOutTitle").getDeclaredClasses()[0], getMcClass("IChatBaseComponent"), Integer.class, Integer.class, Integer.class);
			//Constructor<?> cTitle = getMcClass("PacketPlayOutTitle").getConstructor(getMcClass("PacketPlayOutTitle").getDeclaredClasses()[0], getMcClass("IChatBaseComponent"));
			Constructor<?> cTiming = getMcClass("PacketPlayOutTitle").getConstructors()[0];
			Constructor<?> cTitle = getMcClass("PacketPlayOutTitle").getConstructors()[2];
			sendPacket(player, cTiming.newInstance(packetTiming, objectTiming, fadeIn, stay, fadeOut));
			sendPacket(player, cTitle.newInstance(packetTitle, objectTitle));
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static void sendSubTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String subtitle) {
		try {
			subtitle = ChatColor.translateAlternateColorCodes('&', subtitle);
			Object packetTiming = getMcClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("TIMES").get(null);
			Object packetTitle = getMcClass("PacketPlayOutTitle").getDeclaredClasses()[0].getField("SUBTITLE").get(null);
			Object objectTiming = getMcClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + subtitle + "\"}");
			Object objectTitle = getMcClass("IChatBaseComponent").getDeclaredClasses()[0].getMethod("a", String.class).invoke(null, "{\"text\":\"" + subtitle + "\"}");
			//Constructor<?> cTiming = getMcClass("PacketPlayOutTitle").getConstructor(getMcClass("PacketPlayOutTitle").getDeclaredClasses()[0], getMcClass("IChatBaseComponent"), Integer.class, Integer.class, Integer.class);
			//Constructor<?> cTitle = getMcClass("PacketPlayOutTitle").getConstructor(getMcClass("PacketPlayOutTitle").getDeclaredClasses()[0], getMcClass("IChatBaseComponent"));
			Constructor<?> cTiming = getMcClass("PacketPlayOutTitle").getConstructors()[0];
			Constructor<?> cTitle = getMcClass("PacketPlayOutTitle").getConstructors()[2];

			for (int i = 0; i < getMcClass("PacketPlayOutTitle").getConstructors().length; i++) {
				Constructor<?> con = getMcClass("PacketPlayOutTitle").getConstructors()[i];
				player.sendMessage(ChatColor.RED.toString() + i + " = " + con.getParameterCount());
			}
			
			sendPacket(player, cTiming.newInstance(packetTiming, objectTiming, fadeIn, stay, fadeOut));
			sendPacket(player, cTitle.newInstance(packetTitle, objectTitle));
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
