package com.refresh.pos.domain;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;


public class Sale {
	
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
	public List<LineItem> getAllLineItem(){
		return items;
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

	public Map<String, String> toMap() {	
		Map<String, String> map = new HashMap<String, String>();
		map.put("id",id+"");
		map.put("startTime", DateTimeStrategy.format(startTime));
		map.put("endTime", DateTimeStrategy.format(endTime));
		map.put("status", getStatus());
		return map;
	}

}
