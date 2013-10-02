package com.refresh.pos;

import java.util.List;

public interface Database {
	public List<Object> select(String queryString);
	public long insert(String tableName, Object content);
	public boolean update();
	public boolean delete();
	public long getSize();
}
