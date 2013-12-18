package com.refresh.pos.domain.sale;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.refresh.pos.domain.inventory.LineItem;
import com.refresh.pos.domain.inventory.Product;


public class Sale {
	
	private final int id;
	private String startTime;
	private String endTime;
	private String status;
	private List<LineItem> items;
	
	// this total and orders will be used only when the Sale is loaded without lineitem.
	private Double total;
	private Integer orders;
	

	public Sale(int id, String startTime) {
		this(id, startTime, startTime, "???", new ArrayList<LineItem>(), null, null);
	}
	
	public Sale(int id, String startTime, String endTime, String status, List<LineItem> items, Double total, Integer orders) {
		this.id = id;
		this.startTime = startTime;
		this.status = status;
		this.endTime = endTime;
		this.items = items;
		this.total = total;
		this.orders = orders;
	}
	
	public Sale(int id, String startTime, String endTime, String status, Double total, Integer orders) {
		this(id, startTime, endTime, status, null, total, orders);
	}
	
	public Sale(int id, String startTime, String endTime, String status, List<LineItem> items) {
		this(id, startTime, endTime, status, items, null, null);
	}
	
	public List<LineItem> getAllLineItem(){
		return items;
	}
	
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
	
	public LineItem getLineItemAt(int index) {
		if (index >= 0 && index < items.size())
			return items.get(index);
		return null;
	}


	public double getTotal() {
		
		// in case that sale didn't load items.
		if (items == null) return this.total;
		
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

	public String getPayment() {
		return "CASH";
	}

	public String getStatus() {
		return status;
	}
	public int getOrders() {
		if (items == null) return this.orders;
		
		int orderCount = 0;
		for (LineItem lineItem : items) {
			orderCount += lineItem.getQuantity();
		}
		return orderCount;
	}

	public Map<String, String> toMap() {	
		Map<String, String> map = new HashMap<String, String>();
		map.put("id",id+"");
		map.put("startTime", startTime);
		map.put("endTime", endTime);
		map.put("status", getStatus());
		map.put("total", getTotal() + "");
		map.put("orders", getOrders() + "");
		
		return map;
	}

}
