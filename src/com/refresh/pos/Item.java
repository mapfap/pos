package com.refresh.pos;


public class Item {

	public int id;
	public final String name;
	public final String barcode;
	public final double price;
	
	public static final int UNDEFINED = -1;
	
	public Item(int id, String name, String barcode, double price) {
		this.id = id;
		this.name = name;
		this.barcode = barcode;
		this.price = price;
	}
	
	public Item(String name, String barcode, double price) {
		this.id = UNDEFINED;
		this.name = name;
		this.barcode = barcode;
		this.price = price;
	}

	
	
}
