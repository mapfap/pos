package com.refresh.pos.core;

public class ProductLot {
	
	public String getDateAdded() {
		return "";
	}
	
	public long getAmount() {
		return 0;
	}
	
	public Product getProduct() {
		return ProductFactory.getInstance().createProduct("", "", 0);
	}
	
	public double getCost() {
		return 0;
	}
}
