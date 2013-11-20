package com.refresh.pos.domain;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateTimeStrategy {
	
	private static String language = "en";
	private static String region = "US";
	private static Locale locale;
	
	private DateTimeStrategy() {
		// Static class
	}
	
	public static void setLocale(String lang, String reg) {
//		language = lang;
//		region = reg;
		locale = new Locale(lang, reg);
	}
	
	public static String format(String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yy", locale);
		Date ndate = new Date(date);
		return formatter.format(ndate);
	}
	
	public static String getCurrentTime() {
		return (new Date()).toString();
	}

}
