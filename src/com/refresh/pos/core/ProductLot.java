package com.refresh.pos.core;

import com.refresh.pos.database.NoDaoSetException;

public class ProductLot {
	
	
	private int id;
	private String dateAdded;
	private double amount;
	private int productId;
	private double cost;

	public ProductLot(int id, String dateAdded, double amount, int productId, double cost) {
		this.id = id;
		this.dateAdded = dateAdded;
		this.amount = amount;
		this.productId = productId;
		this.cost = cost;
	}
	
	public String getDateAdded() {
		return dateAdded;
	}
	
	public double getAmount() {
		return amount;
	}
	
	public Product getProduct() {
		try {
			return Inventory.getInstance().getProductCatalog().getProductById(productId);
		} catch (NoDaoSetException e) {
			e.printStackTrace();
		}
		return Product.UNDEFINED_PRODUCT;
	}
	
	public double getCost() {
		return cost;
	}

	public int getId() {
		return id;
	}

	public int getProductId() {
		return productId;
	}
}
