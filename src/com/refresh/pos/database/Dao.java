package com.refresh.pos.database;

import java.util.List;

public interface Dao {
	public List<Object> select(String queryString);
	public long insert(String tableName, Object content);
	public boolean update();
	public boolean delete();
	public long getSize();
}
