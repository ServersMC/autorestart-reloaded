package me.dennis.updatecheck.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.bukkit.plugin.Plugin;

public class UpdateCheck {

	private String url;
	private Plugin plugin;
	private Boolean updated = true;
	
	public UpdateCheck(String url, Plugin plugin) {
		this.url = url;
		this.plugin = plugin;
	}
	
	public void checkSpigot() {
		try {
			HttpURLConnection con = (HttpURLConnection) (new URL(url)).openConnection();
			con.setRequestMethod("GET");
			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			BufferedReader reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String version = null;
			String line;
			while ((line = reader.readLine()) != null)
				if (line.contains("<span class=\"muted\">") && line.contains("h1"))
					version = line.split("<span class=\"muted\">")[1].split("<")[0];
			reader.close();
			String name = plugin.getDescription().getName();
			if (version.equalsIgnoreCase(plugin.getDescription().getVersion())) {
				System.out.println("[" + name + "] " + name + " is up to date!");
			} else {
				updated = false;
				System.out.println((new StringBuilder("[" + name + "] " + name + " came out with a new version! v")).append(version).toString());
				System.out.println(url);
			}
		} catch (IOException e) {
			String name = plugin.getDescription().getName();
			System.out.println("[" + name + "] has failed to check for updates! Please check your firewall, or internet connection.");
			System.out.println("[" + name + "] if everything works, then Spigotmc is unable to be reached.");
		}
	}
	
	public Boolean isUpdated() {
		return updated;
	}
	
}
