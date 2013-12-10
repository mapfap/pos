package com.refresh.pos.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DateTimeStrategy {
	
	private static Locale locale;
	
	private DateTimeStrategy() {
		// Static class
	}
	
	public static void setLocale(String lang, String reg) {
		locale = new Locale(lang, reg);
	}
	
	public static String format(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy", locale);
		return formatter.format(Calendar.getInstance(locale).getTime());
	}
	
	public static String getCurrentTime() {
		return (Calendar.getInstance(locale).getTime()).toString();
	}

}
