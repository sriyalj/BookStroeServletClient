package Util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateTime {
	
	static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

	public static Date getUTCdatetimeAsDate() throws ParseException {
		return stringDateToDate(getUTCdatetimeAsString());
	}

	public static String getUTCdatetimeAsString() {
		final SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		final String utcTime = sdf.format(new Date());

		return utcTime;
	}

	public static Date stringDateToDate(String StrDate) throws ParseException {
		Date dateToReturn = null;
		SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

		dateToReturn = (Date)dateFormat.parse(StrDate);		
		return dateToReturn;
    }
}
