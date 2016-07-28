package org.serversmc.autorestart.objects;

public class TimeDeserializer {

	public Integer h;
	public Integer m;
	public Integer s;
	
	public TimeDeserializer(Integer time) {
		h = time / 3600;
		m = (time / 60) - (h * 60);
		s = time - (h * 3600) - (m * 60);
	}
	
}
