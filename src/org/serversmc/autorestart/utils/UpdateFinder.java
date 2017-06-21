package org.serversmc.autorestart.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipFile;

public class UpdateFinder {

	private static List<PluginLite> plugins = new ArrayList<PluginLite>();
	private static List<PluginLite> pluginsOld = new ArrayList<PluginLite>();
	private static List<PluginLite> pluginsUpdated = new ArrayList<PluginLite>();

	public static void init() {
		File[] files = new File("plugins/").listFiles();
		for (File file : files) {
			if (file.getName().endsWith(".jar")) {
				try {
					loadPluginFromFile(file.getAbsolutePath());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	public static void checkUpdates() {
		pluginsOld.addAll(plugins);
		plugins.clear();
		init();
		for (PluginLite plugin : plugins) {
			if (isUpdated(plugin)) {
				pluginsUpdated.add(plugin);
			}
		}
	}

	public static List<PluginLite> getUpdatedPlugins() {
		return pluginsUpdated;
	}

	private static boolean isUpdated(PluginLite input) {
		boolean found = false;
		boolean updated = false;
		for (PluginLite plugin : pluginsOld) {
			if (plugin.NAME.equalsIgnoreCase(input.NAME)) {
				found = true;
				if (!plugin.VERSION.equalsIgnoreCase(input.VERSION)) {
					updated = true;
				}
				if (plugin.SIZE != input.SIZE) {
					updated = true;
				}
				break;
			}
		}
		if (!found) {
			updated = true;
		}
		return updated;
	}

	private static void loadPluginFromFile(String path) throws IOException {
		PluginLite plugin = new PluginLite();

		ZipFile zip = new ZipFile(path);
		plugin.SIZE = zip.size();
		BufferedReader reader = new BufferedReader(
				new InputStreamReader(zip.getInputStream(zip.getEntry("plugin.yml"))));
		String line;
		while ((line = reader.readLine()) != null) {
			if (line.trim().startsWith("name")) {
				plugin.NAME = line.split(":")[1].trim();
			}
			if (line.trim().startsWith("version")) {
				plugin.VERSION = line.split(":")[1].trim();
			}
		}
		reader.close();
		zip.close();
		plugins.add(plugin);
	}

	public static class PluginLite {

		public String PATH;
		public String NAME;
		public String VERSION;
		public long SIZE;

	}
}
