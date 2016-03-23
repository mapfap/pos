package com.refresh.pos.domain.sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.refresh.pos.domain.inventory.LineItem;
import com.refresh.pos.domain.inventory.Product;

/**
 * Sale represents sale operation.
 * 
 * @author Refresh Team
 *
 */
public class Sale {
	
	private final int id;
	private String startTime;
	private String endTime;
	private String status;
	private List<LineItem> items;
	

	public Sale(int id, String startTime) {
		this(id, startTime, startTime, "", new ArrayList<LineItem>());
	}
	
	/**
	 * Constructs a new Sale.
	 * @param id ID of this Sale.
	 * @param startTime start time of this Sale.
	 * @param endTime end time of this Sale.
	 * @param status status of this Sale.
	 * @param items list of LineItem in this Sale.
	 */
	public Sale(int id, String startTime, String endTime, String status, List<LineItem> items) {
		this.id = id;
		this.startTime = startTime;
		this.status = status;
		this.endTime = endTime;
		this.items = items;
	}
	
	/**
	 * Returns list of LineItem in this Sale.
	 * @return list of LineItem in this Sale.
	 */
	public List<LineItem> getAllLineItem(){
		return items;
	}
	
	/**
	 * Add Product to Sale.
	 * @param product product to be added.
	 * @param quantity quantity of product that added.
	 * @return LineItem of Sale that just added.
	 */
	public LineItem addLineItem(Product product, int quantity) {
		
		for (LineItem lineItem : items) {
			if (lineItem.getProduct().getId() == product.getId()) {
				lineItem.addQuantity(quantity);
				return lineItem;
			}
		}
		
		LineItem lineItem = new LineItem(product, quantity);
		items.add(lineItem);
		return lineItem;
	}
	
	public int size() {
		return items.size();
	}
	
	/**
	 * Returns a LineItem with specific index.
	 * @param index of specific LineItem.
	 * @return a LineItem with specific index.
	 */
	public LineItem getLineItemAt(int index) {
		if (index >= 0 && index < items.size())
			return items.get(index);
		return null;
	}

	/**
	 * Returns the total price of this Sale.
	 * @return the total price of this Sale.
	 */
	public double getTotal() {
		double amount = 0;
		for(LineItem lineItem : items) {
			amount += lineItem.getTotalPriceAtSale();
		}
		return amount;
	}

	public int getId() {
		return id;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public String getStatus() {
		return status;
	}
	
	/**
	 * Returns the total quantity of this Sale.
	 * @return the total quantity of this Sale.
	 */
	public int getOrders() {
		int orderCount = 0;
		for (LineItem lineItem : items) {
			orderCount += lineItem.getQuantity();
		}
		return orderCount;
	}

	/**
	 * Returns the description of this Sale in Map format. 
	 * @return the description of this Sale in Map format.
	 */
	public Map<String, String> toMap() {	
		Map<String, String> map = new HashMap<String, String>();
		map.put("id",id + "");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("status", getStatus());
		map.put("total", getTotal() + "");
		map.put("orders", getOrders() + "");
		
		return map;
	}

	/**
	 * Removes LineItem from Sale.
	 * @param lineItem lineItem to be removed.
	 */
	public void removeItem(LineItem lineItem) {
		items.remove(lineItem);
	}

}