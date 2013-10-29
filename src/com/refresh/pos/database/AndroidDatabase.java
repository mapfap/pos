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
	private static final String DATABASE_NAME = "pos_database";

	public AndroidDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL("CREATE TABLE product_catalog"
				+ "(_id INTEGER PRIMARY KEY," + "name TEXT(100),"
				+ "barcode TEXT(100)," + " sale_price DOUBLE );");

		database.execSQL("CREATE TABLE stock" + "(_id INTEGER PRIMARY KEY,"
				+ "product_id INTEGER," + "amount INTEGER," + "cost DOUBLE,"
				+ "date_added DATETIME);");

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
	public long insert(String tableName, Object content) {
		try {
			SQLiteDatabase database = this.getWritableDatabase();
			long rows = database.insert(tableName, null,
					(ContentValues) content);
			database.close();
			return rows;
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}

	}

	@Override
	public boolean update() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete() {
		// TODO Auto-generated method stub
		return false;
	}

}
