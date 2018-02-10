package me.dennis.updatecheck.core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class UpdateCheck {

	private String url;
	private String latestVersion;
	
	public UpdateCheck(String url) {
		this.url = url;
	}
	
	public Boolean needsUpdate(String checkVersion) {
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(new URL(url).openStream()));
			String line;
			while ((line = reader.readLine()) != null) {
				if (line.startsWith("version:")) {
					latestVersion = line.split("version:")[1].trim();
					break;
				}
			}
			reader.close();
			if (latestVersion.replace("v", "").equalsIgnoreCase(checkVersion)) {
				return false;
			}
			else {
				return true;
			}
		} catch (IOException ex) {}
		return null;
	}
	
	public String getVersion() {
		return latestVersion;
	}
	
}
