package com.refresh.pos;

public class ItemFactory {
	
	private static ItemFactory instance;
	private ItemFactory() {
		
	}
	
	public static ItemFactory getInstance() {
		if (instance == null) {
			instance = new ItemFactory();
		}
		return instance;
	}
	
	public Item createItem(String name, String barcode, double price) {
		//  lastID = "SELECT id FROM inventory ORDER BY id DESC LIMIT 1"
		int lastID = 0;
		Item item = new Item(++lastID, name, barcode, price);
		return item;
	}
}
