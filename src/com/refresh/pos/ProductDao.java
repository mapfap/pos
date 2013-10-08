package com.refresh.pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public interface ProductDao {
	public List<Object> select(String queryString);
	public long insert(String tableName, Object content);
	public boolean update();
	public boolean delete();
	public long getSize();
	public ArrayList<HashMap<String, String>> selectAllData();
}
