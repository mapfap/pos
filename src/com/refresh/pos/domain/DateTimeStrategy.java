package com.refresh.pos.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

/**
 * A static class, global access for how to handle with date format.
 * 
 * @author Refresh Team
 *
 */
public class DateTimeStrategy {
	
	private static Locale locale;
	private static SimpleDateFormat dateFormat;
	
	private DateTimeStrategy() {
		// Static class
	}
	
	public static void setLocale(String lang, String reg) {
		locale = new Locale(lang, reg);
		dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss", locale);
	}
	
	public static String format(String date) {
		return dateFormat.format(Calendar.getInstance(locale).getTime());
	}
	
	public static String getCurrentTime() {
		return dateFormat.format(Calendar.getInstance().getTime()).toString();
	}
	
	public static String getSQLDateFormat(Calendar instance) {
		return dateFormat.format(instance.getTime()).toString().substring(0,10);
	}

}
