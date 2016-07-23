package org.serversmc.autorestart.types;

public class TimeDeserializer {

	private Integer time;
	private Integer h;
	private Integer m;
	private Integer s;
	
	public TimeDeserializer(Integer timein) {
		time = timein;
		h = time / 3600;
		m = (time / 60) - (h * 60);
		s = time - (h * 3600) - (m * 60);
	}
	
	public Integer getH() {
		return h;
	}
	
	public Integer getM() {
		return m;
	}
	
	public Integer getS() {
		return s;
	}
	
	public Integer getTime() {
		return time;
	}
	
}
