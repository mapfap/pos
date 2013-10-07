package com.refresh.pos;


public class Product {

	public int id;
	public final String name;
	public final String barcode;
	
	public static final int UNDEFINED = -1;
	
	public Product(String name,String barcode){
		this.name = name;
		this.barcode = barcode;
	}
	
	public Product(int id, String name, String barcode) {
		this.id = id;
		this.name = name;
		this.barcode = barcode;
	}
	
	
	
}
