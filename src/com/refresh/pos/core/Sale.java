package com.refresh.pos.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;


public class Sale extends Observable {
	
	private final int id;
	private String startTime;
	private String endTime;
	private String status;
	private List<LineItem> items;

	public Sale(int id, String startTime) {
		this.id = id;
		this.startTime = startTime;
		this.status = "???";
		items = new ArrayList<LineItem>( );
	}
	
	public Sale(int id, String startTime, String endTime, String status) {
		this.id = id;
		this.startTime = startTime;
		this.status = status;
		this.endTime = endTime;
	}
	
	public LineItem addLineItem(Product product, int quantity) {
		
		for (LineItem lineItem : items) {
			if (lineItem.getProductId() == product.getId()) {
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
	
	public LineItem getLineItem(int index) {
		if (index >= 0 && index < items.size())
			return items.get(index);
		return null;
	}


	public double getTotal() {
		double total = 0;
		for(LineItem lineItem : items) {
			total += lineItem.getTotal();
		}
		return total;
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

}
