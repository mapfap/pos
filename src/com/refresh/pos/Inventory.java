package com.refresh.pos;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;

public class Inventory {

	private Database database;
	private static final String TABLE_NAME = "inventory";

	public Inventory(Database database) {
		this.database = database;
	}

	private static ItemFactory itemFactory = ItemFactory.getInstance();

	public boolean addNewItem(Item item) {
		ContentValues content = new ContentValues();
		content.put("name", item.name);
		content.put("barcode", item.barcode);
		content.put("price", item.price);
		long respond = database.insert(TABLE_NAME, content);
		return respond != -1;
	}

	public boolean updateItem() {

		return false;
	}

	public List<Item> getAllItem() {
		String queryString = "SELECT * FROM " + TABLE_NAME;
		@SuppressWarnings("unchecked")
		List<ContentValues> contents = (List) database.select(queryString);
		List<Item> itemList = new ArrayList<Item>();
		for (ContentValues content: contents) {
			itemList.add(
				itemFactory.createItem( content.getAsInteger("id"),
						content.getAsString("name"), content.getAsString("barcode"),
						content.getAsDouble("price") )
			);
		}
		return itemList;
	}
}
