package com.refresh.pos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.util.Log;

public class ProductDaoAndroid extends SQLiteOpenHelper implements ProductDao {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "pos_database";
	private static final String TABLE_NAME = "product_catalog";

	public ProductDaoAndroid(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {

		database.execSQL("CREATE TABLE product_catalog"
				+ "(_id INTEGER PRIMARY KEY," + "name TEXT(100),"
				+ "barcode TEXT(100));");

		database.execSQL("CREATE TABLE stock" + "(_id INTEGER PRIMARY KEY,"
				+ "product_id INTEGER," + "amount INTEGER," + "cost FLOAT,"
				+ "date_added DATETIME);");

		database.execSQL("CREATE TABLE sale_price"
				+ "(_id INTEGER PRIMARY KEY," + "product_id INTEGER,"
				+ "price FLOAT);");

		Log.d("CREATE DATABASE", "Create Database Successfully.");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

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
						content.put("id",
								cursor.getInt(cursor.getColumnIndex("_id")));
						content.put("name",
								cursor.getString(cursor.getColumnIndex("name")));
						content.put("barcode", cursor.getString(cursor
								.getColumnIndex("barcode")));
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

	public long getSize() {
		SQLiteDatabase database = this.getWritableDatabase();
		SQLiteStatement byteStatement = database
				.compileStatement("SELECT SUM(LENGTH(_id)) FROM product_catalog");
		long bytes = byteStatement.simpleQueryForLong();
		return bytes;
	}

	@Override
	public ArrayList<HashMap<String, String>> selectAllData() {
		try {

			ArrayList<HashMap<String, String>> MyArrList = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> map;

			SQLiteDatabase db;
			db = this.getReadableDatabase(); // Read Data

			String strSQL = "SELECT  * FROM " + TABLE_NAME;
			Cursor cursor = db.rawQuery(strSQL, null);

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						map = new HashMap<String, String>();
						map.put("_id", cursor.getString(0));
						map.put("name", cursor.getString(1));
						map.put("barcode", cursor.getString(2));
						MyArrList.add(map);
					} while (cursor.moveToNext());
				}
			}
			cursor.close();
			db.close();
			return MyArrList;

		} catch (Exception e) {
			return null;
		}
	}

}
