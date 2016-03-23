package com.refresh.pos.techicalservices;

/**
 * Uses to directly access to database.
 * 
 * @author Refresh Team
 *
 */
public class DatabaseExecutor {

	private static Database database;
	private static DatabaseExecutor instance;
	
	private DatabaseExecutor() {
		
	}
	
	/**
	 * Sets database for use in DatabaseExecutor.
	 * @param db database.
	 */
	public static void setDatabase(Database db) {
		database = db;
	}
	
	public static DatabaseExecutor getInstance() {
		if (instance == null)
			instance = new DatabaseExecutor();
		return instance;
	}
	
	/**
	 * Drops all data in database.
	 */
	public void dropAllData() {
		execute("DELETE FROM " + DatabaseContents.TABLE_PRODUCT_CATALOG + ";");
		execute("DELETE FROM " + DatabaseContents.TABLE_SALE + ";");
		execute("DELETE FROM " + DatabaseContents.TABLE_SALE_LINEITEM + ";");
		execute("DELETE FROM " + DatabaseContents.TABLE_STOCK + ";");
		execute("DELETE FROM " + DatabaseContents.TABLE_STOCK_SUM + ";");
		execute("VACUUM;");
	}
	
	/**
	 * Directly execute to database.
	 * @param queryString query string for execute.
	 */
	private void execute(String queryString) {
		database.execute(queryString);
	}
	
	
}
