package com.refresh.pos.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class AndroidDatabase extends SQLiteOpenHelper implements Database {

	private static final int DATABASE_VERSION = 1;

	public AndroidDatabase(Context context) {
		super(context, DatabaseContents.DATABASE.toString(), null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		
		database.execSQL("CREATE TABLE " + DatabaseContents.TABLE_PRODUCT_CATALOG.toString() + "("
				
				+ "_id INTEGER PRIMARY KEY,"
				+ "name TEXT(100),"
				+ "barcode TEXT(100),"
				+ "unit_price DOUBLE"
				
				+ ");");
		Log.d("CREATE DATABASE", "Create " + DatabaseContents.TABLE_PRODUCT_CATALOG.toString() + " Successfully.");
		
		database.execSQL("CREATE TABLE "+ DatabaseContents.TABLE_STOCK.toString() + "(" 
				
				+ "_id INTEGER PRIMARY KEY,"
				+ "product_id INTEGER,"
				+ "quantity INTEGER,"
				+ "cost DOUBLE,"
				+ "date_added DATETIME"
				
				+ ");");
		Log.d("CREATE DATABASE", "Create " + DatabaseContents.TABLE_STOCK.toString() + " Successfully.");
		
		database.execSQL("CREATE TABLE "+ DatabaseContents.TABLE_SALE.toString() + "("
				
				+ "_id INTEGER PRIMARY KEY,"
				+ "status TEXT(40),"
				+ "payment TEXT(50),"
				+ "total_price DOUBLE,"
				+ "start_time DATETIME,"
				+ "end_time DATETIME"
				
				+ ");");
		Log.d("CREATE DATABASE", "Create " + DatabaseContents.TABLE_SALE.toString() + " Successfully.");
		
		database.execSQL("CREATE TABLE "+ DatabaseContents.TABLE_SALE_LINEITEM.toString() + "("
				
				+ "_id INTEGER PRIMARY KEY,"
				+ "sale_id INTEGER,"
				+ "product_id INTEGER,"
				+ "quantity INTEGER,"
				+ "unit_price DOUBLE"
				
				+ ");");
		Log.d("CREATE DATABASE", "Create " + DatabaseContents.TABLE_SALE_LINEITEM.toString() + " Successfully.");


		// this _id is product_id but for update method, it is easier to use name _id
		database.execSQL("CREATE TABLE " + DatabaseContents.TABLE_STOCK_SUM.toString() + "("
				
				+ "_id INTEGER PRIMARY KEY,"
				+ "quantity INTEGER"
				
				+ ");");
		Log.d("CREATE DATABASE", "Create " + DatabaseContents.TABLE_STOCK_SUM.toString() + " Successfully.");
		
		Log.d("CREATE DATABASE", "Create Database Successfully.");

	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

	@Override
	public List<Object> select(String queryString) {
		try {
			SQLiteDatabase database = this.getWritableDatabase();
			List<Object> list = new ArrayList<Object>();
			Cursor cursor = database.rawQuery(queryString, null);

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						ContentValues content = new ContentValues();
						String[] columnNames = cursor.getColumnNames();
						for (String columnName : columnNames) {
							content.put(columnName, cursor.getString(cursor
									.getColumnIndex(columnName)));
						}
						list.add(content);
					} while (cursor.moveToNext());
				}
			}
			cursor.close();
			database.close();
			return list;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public int insert(String tableName, Object content) {
		try {
			SQLiteDatabase database = this.getWritableDatabase();
			int id = (int) database.insert(tableName, null,
					(ContentValues) content);
			database.close();
			return id;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	@Override
	public boolean update(String tableName, Object content) {
		try {
			SQLiteDatabase database = this.getWritableDatabase();
			ContentValues cont = (ContentValues) content;
			// this array will always contains only one element. 
			String[] array = new String[]{cont.get("_id")+""};
			database.update(tableName, cont, " _id = ?", array);
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(String tableName, int id) {
		try {
			SQLiteDatabase database = this.getWritableDatabase();
			database.delete(tableName, " _id = ?", new String[]{id+""});
			return true;
			
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
//	public void destroy() {
//		database.execSQL("DROP DATABASE " + DatabaseContents.DATABASE.toString());
//	}
	
	

}
