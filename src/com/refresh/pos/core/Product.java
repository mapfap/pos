package com.refresh.pos.core;


public class Product {

	private int id;
	private String name;
	private String barcode;
	private double salePrice;
	public static final int UNDEFINED = -1;
	
	public Product(int id, String name, String barcode, double salePrice) {
		this.id = id;
		this.name = name;
		this.barcode = barcode;
		this.salePrice = salePrice;
	}
	
	public String getName() {
		return name;
	}
	
	public int getId() {
		return id;
	}
	
	public String getBarcode() {
		return barcode;
	}
	
	public double getSalePrice() {
		return salePrice;
	}
	
}
