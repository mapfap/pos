package com.refresh.pos.domain;

import java.util.HashMap;
import java.util.Map;

import com.refresh.pos.database.NoDaoSetException;

public class ProductLot {
	
	
	private int id;
	private String dateAdded;
	private double quantity;
	private int productId;
	private double cost;
	public static final int UNDEFINED_ID = -1;

	public ProductLot(int id, String dateAdded, double quantity, int productId, double cost) {
		this.id = id;
		this.dateAdded = dateAdded;
		this.quantity = quantity;
		this.productId = productId;
		this.cost = cost;
	}
	
	public String getDateAdded() {
		return dateAdded;
	}
	
	public double getQuantity() {
		return quantity;
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

	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("id",id+"");
		map.put("dateAdded", dateAdded);
		map.put("quantity", quantity+"");
		map.put("productName", productId+"");
		map.put("cost", cost+"");
		return map;
	}
}
