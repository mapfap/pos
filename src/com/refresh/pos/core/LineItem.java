package com.refresh.pos.core;

public class LineItem {
	
	private final Product product;
	private double quantity;

	public LineItem(Product product) {
		this(product, 1);
	}

	public LineItem(Product product, double quantity) {
		this.product = product;
		this.quantity = quantity;
	}

	public long getProductId() {
		return product.getId();
	}
	
	public Product getProduct() {
		return product;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void addQuantity(int amount) {
		this.quantity += amount;
	}

	public double getTotal() {
		return product.getSalePrice() * quantity;
	}
}
