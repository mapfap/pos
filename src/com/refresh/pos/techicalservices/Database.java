package com.refresh.pos.techicalservices;

import java.util.List;

/**
 * Interface of CRUD operation.
 * 
 * @author Refresh Team
 *
 */
public interface Database {
	
	/**
	 * Selects something in database.
	 * @param queryString query string for select in database. 
	 * @return list of object.
	 */
	public List<Object> select(String queryString);
	
	/**
	 * Inserts something in database.
	 * @param tableName name of table in database.
	 * @param content string for using in database.
	 * @return id of row data.
	 */
	public int insert(String tableName, Object content);
	
	/**
	 * Updates something in database.
	 * @param tableName name of table in database.
	 * @param content string for using in database.
	 * @return true if updates success ; otherwise false.
	 */
	boolean update(String tableName, Object content);
	
	/**
	 * Deletes something in database.
	 * @param tableName name of table in database.
	 * @param id a specific id of row data for deletion.
	 * @return true if deletion success ; otherwise false.
	 */
	boolean delete(String tableName, int id);
	
	/**
	 * Directly execute to database.
	 * @param queryString query string for execute.
	 * @return true if executes success ; otherwise false.
	 */
	boolean execute(String queryString);
}
