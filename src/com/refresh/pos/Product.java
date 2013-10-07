package com.refresh.pos;


public class Product {

	public int id;
	public final String name;
	public final String barcode;
	public double price;
	
	public static final int UNDEFINED = -1;
	
	public Product(String name,String barcode){
		this.name = name;
		this.barcode = barcode;
	}
	
	public Product(int id, String name, String barcode, double price) {
		this.id = id;
		this.name = name;
		this.barcode = barcode;
		this.price = price;
	}
	
	public Product(String name, String barcode, double price) {
		this.id = UNDEFINED;
		this.name = name;
		this.barcode = barcode;
		this.price = price;
	}

	
	
}
