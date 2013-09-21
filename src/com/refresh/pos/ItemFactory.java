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
	
	public Item createItem(int id, String name, String barcode, double price) {
		Item item = new Item(id, name, barcode, price);
		return item;
	}
	
	public Item createItem(String name, String barcode, double price) {
		Item item = new Item(name, barcode, price);
		return item;
	}
}
