package com.refresh.pos.domain;

import java.util.HashMap;
import java.util.Map;

public class ProductLot {
	
	
	private int id;
	private String dateAdded;
	private int quantity;
	private int left;
	private Product product;
	private double cost;
	public static final int UNDEFINED_ID = -1;

	public ProductLot(int id, String dateAdded, int quantity, Product product, double cost, int left) {
		this.id = id;
		this.dateAdded = dateAdded;
		this.quantity = quantity;
		this.product = product;
		this.cost = cost;
		this.left = left;
	}
	
	public String getDateAdded() {
		return dateAdded;
	}
	
	public int getQuantity() {
		return quantity;
	}
	
	public int getLeft() {
		return left;
	}
	
	public double getCost() {
		return cost;
	}

	public int getId() {
		return id;
	}

	public Product getProduct() {
		return product;
	}

	public Map<String, String> toMap() {	
		Map<String, String> map = new HashMap<String, String>();
		map.put("id",id+"");
		map.put("dateAdded", DateTimeStrategy.format(dateAdded));
		map.put("quantity", quantity+"");
		map.put("productName", product.getName());
		map.put("cost", cost+"");
		map.put("left", left +"");
		return map;
	}
}
