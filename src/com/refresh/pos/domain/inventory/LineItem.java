package com.refresh.pos.domain.inventory;

import java.util.HashMap;
import java.util.Map;

public class LineItem {
	
	private final Product product;
	private int quantity;
	private int id;
	private int saleId;
	private double unitPriceAtSale;
	public static final int UNDEFINED = -1;

	public LineItem(Product product, int quantity) {
		this(UNDEFINED, product, quantity, product.getUnitPrice());
	}
	
	public LineItem(int id, Product product, int quantity, double unitPriceAtSale) {
		this.id = id;
		this.product = product;
		this.quantity = quantity;
		this.unitPriceAtSale = unitPriceAtSale;
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

	public double getTotalPriceAtSale() {
		return unitPriceAtSale * quantity;
	}
	
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", product.getName());
		map.put("quantity",quantity+"");
		map.put("price",getTotalPriceAtSale()+"");
		return map;
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public void setUnitPriceAtSale(double unitPriceAtSale) {
		this.unitPriceAtSale = unitPriceAtSale;
	}
	
	public Double getPriceAtSale() {
		return unitPriceAtSale;
	}
}
