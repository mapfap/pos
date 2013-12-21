package com.refresh.pos.domain.inventory;

import java.util.HashMap;
import java.util.Map;

/**
 * LineItem of Sale.
 * @author Refresh Team
 *
 */
public class LineItem {
	
	private final Product product;
	private int quantity;
	private int id;
	private double unitPriceAtSale;
	
	/**
	 * Static value for UNDEFINED ID.
	 */
	public static final int UNDEFINED = -1;

	public LineItem(Product product, int quantity) {
		this(UNDEFINED, product, quantity, product.getUnitPrice());
	}
	
	/**
	 * Constructs a new LineItem. 
	 * @param id ID of this LineItem, This value should be assigned from database.
	 * @param product product of this LineItem.
	 * @param quantity product quantity of this LineItem.
	 * @param unitPriceAtSale unit price at sale time. default is price from Product Catalog.
	 */
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
	
	/**
	 * Returns the description of this LineItem in Map format. 
	 * @return the description of this LineItem in Map format.
	 */
	public Map<String, String> toMap() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", product.getName());
		map.put("quantity", quantity + "");
		map.put("price", getTotalPriceAtSale() + "");
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
	
	/**
	 * Determines whether two objects are equal or not.
	 * @return true if Object is a LineItem with same ID.
	 */
	@Override
	public boolean equals(Object object) {
		if (object == null) return false;
		if (!(object instanceof LineItem)) return false;
		LineItem lineItem = (LineItem) object;
		return lineItem.getId() == this.getId();
	}
}
