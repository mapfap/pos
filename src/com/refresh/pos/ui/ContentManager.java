package com.refresh.pos.ui;

import java.util.HashMap;
import java.util.Map;

public class ContentManager {
	public static final String UNDEFINED = "-1";
	private static Map<String, String> content;
	static {
		content = new HashMap<String, String>();
		content.put("id", UNDEFINED);
	}
	
	public static String get(String key) {
		return content.get(key);
	}
	
	public static void put(String key, String value) {
		content.put(key, value);
	}
}
