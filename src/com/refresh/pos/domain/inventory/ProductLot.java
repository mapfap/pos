package com.refresh.pos.domain.inventory;

import java.util.HashMap;
import java.util.Map;

import com.refresh.pos.domain.DateTimeStrategy;

public class ProductLot {
	
	
	private int id;
	private String dateAdded;
	private int quantity;
	private Product product;
	private double cost;
	public static final int UNDEFINED_ID = -1;

	public ProductLot(int id, String dateAdded, int quantity, Product product, double cost) {
		this.id = id;
		this.dateAdded = dateAdded;
		this.quantity = quantity;
		this.product = product;
		this.cost = cost;
	}
	
	public String getDateAdded() {
		return dateAdded;
	}
	
	public int getQuantity() {
		return quantity;
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
		return map;
	}
}
