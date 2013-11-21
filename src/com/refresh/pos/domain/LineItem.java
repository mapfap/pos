package com.refresh.pos.domain;

import java.util.HashMap;
import java.util.Map;

public class LineItem {
	
	private final Product product;
	private int quantity;

	public LineItem(Product product) {
		this(product, 1);
	}

	public LineItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public int getProductId() {
		return product.getId();
	}
	
	public Product getProduct() {
		return product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void addQuantity(int amount) {
		this.quantity += amount;
	}

	public double getTotal() {
		return product.getUnitPrice() * quantity;
	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", product.getName());
		map.put("quantity",quantity+"");
		map.put("price",getTotal()+"");
		return map;
		
	}
}
