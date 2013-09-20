package com.refresh.pos;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class POSDatabase extends SQLiteOpenHelper {

	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "POSDatabase";
	private static final String TABLE_INVENTORY = "inventory";

	public POSDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase database) {
		database.execSQL("CREATE TABLE " + TABLE_INVENTORY
				+ "(itemID INTEGER PRIMARY KEY," + " Name TEXT(100),"
				+ " price REAL);");

		Log.d("CREATE TABLE", "Create " + TABLE_INVENTORY + " Successfully.");
	}

	@Override
	public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

	}

	public long addItem(Item item) {
		try {
			SQLiteDatabase database = this.getWritableDatabase();
			ContentValues content = new ContentValues();
			content.put("id", item.id);
			content.put("name", item.name);
			content.put("barcode", item.barcode);
			content.put("price", item.price);

			long rows = database.insert(TABLE_INVENTORY, null, content);
			database.close();
			return rows; // return rows inserted.
		} catch (Exception e) {
			e.printStackTrace();
			return -1;
		}
	}

	public List<Item> SelectAllData() {
		try {
			SQLiteDatabase database = this.getWritableDatabase();
			List<Item> itemList = new ArrayList<Item>();
			String sql = "SELECT  * FROM " + TABLE_INVENTORY;
			Cursor cursor = database.rawQuery(sql, null);

			if (cursor != null) {
				if (cursor.moveToFirst()) {
					do {
						Item item = new Item(
							cursor.getInt(cursor.getColumnIndex("id")),
							cursor.getString(cursor.getColumnIndex("name")),
							cursor.getString(cursor.getColumnIndex("barcode")),
							cursor.getDouble(cursor.getColumnIndex("price"))
						);
						itemList.add(item);
					} while (cursor.moveToNext());
				}
			}
			cursor.close();
			database.close();
			return itemList;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
