package com.refresh.pos.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DataParser {

	public static List<HashMap<String, String>> parseMap(List list) {
		List<HashMap<String, String>> listOfMap = new ArrayList<HashMap<String, String>>();
		for (Object obj : list) {
			HashMap<String, String> map = new HashMap<String, String>();
			listOfMap.add(map);
		}
		return listOfMap;
	}

}
