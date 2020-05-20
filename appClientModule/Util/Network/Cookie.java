package Util.Network;

import java.text.SimpleDateFormat;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.Date;
import java.util.TimeZone;

public class Cookie {
	
	String value;
	String name;
	String domain;
	long maxAge;
	String path;
	String expiryTime;
	
	public Cookie (String name, String value) {		
		this.name = name;
		this.value = value;
	}
	
	public String getValue () {
		return value;
	}
	
	public String getName () {
		return name;
	}
	
	public String getDomain () {
		return domain;
	}
	
	public long getMaxAge () {
		return maxAge;
	}
	
	public Date getDate () {
		return null;
	}
	
	public void setDomain (String domain) {
		this.domain = domain;
	}
	
	
}
