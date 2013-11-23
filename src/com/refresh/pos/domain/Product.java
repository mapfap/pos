package com.refresh.pos.domain;

import java.util.HashMap;
import java.util.Map;


public class Product {

	private int id;
	private String name;
	private String barcode;
	private double unitPrice;
	
	public static final String UNDEFINED = "UNDEFINED";
	public static final int UNDEFINED_ID = -1;
	public static final int UNDEFINED_PRICE = 0;
	public static final Product UNDEFINED_PRODUCT = new Product(UNDEFINED_ID, UNDEFINED, UNDEFINED, UNDEFINED_PRICE);
	
	public Product(int id, String name, String barcode, double salePrice) {
		this.id = id;
		this.name = name;
		this.barcode = barcode;
		this.unitPrice = salePrice;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getId() {
		return id;
	}
	
	public String getBarcode() {
		return barcode;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}

	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id",id+"");
		map.put("name", name);
		map.put("barcode", barcode);
		map.put("unitPrice", unitPrice+"");
		return map;
		
	}
	
}
