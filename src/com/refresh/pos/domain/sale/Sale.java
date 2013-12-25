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
	
	public Sale(int id, String startTime, String endTime, String status, List<LineItem> items) {
		this.id = id;
		this.startTime = startTime;
		this.status = status;
		this.endTime = endTime;
		this.items = items;
	}
	
	/**
	 *Get ALL Line Item in this sale
	 *@return item
	 */
	public List<LineItem> getAllLineItem(){
		return items;
	}
	
	/**
	 *Add LineLine into this sale
	 *@param product
	 *@param qantity
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
	
	/**
	 *Get total amount of line item in this sale
	 */
	public int size() {
		return items.size();
	}
	
	/**
	 *GET LINE ITEM BY ORDER
	 */
	public LineItem getLineItemAt(int index) {
		if (index >= 0 && index < items.size())
			return items.get(index);
		return null;
	}


	/**
	 *GET TOTAL PRICE OF ALL LINE ITEM IN THIS SALE
	 */
	public double getTotal() {
		double amount = 0;
		for(LineItem lineItem : items) {
			amount += lineItem.getTotalPriceAtSale();
		}
		return amount;
	}

	/*
	 *GET SALE ID
	 */
	public int getId() {
		return id;
	}

	/*
	 *GET START TIME OF THIS SALE
	 */
	public String getStartTime() {
		return startTime;
	}
	
	/*
	 *GET END TIME OF THIS SALE
	 */
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
		int orderCount = 0;
		for (LineItem lineItem : items) {
			orderCount += lineItem.getQuantity();
		}
		return orderCount;
	}

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

	/*
	 *REMOVE ITME FROM THE SALE
	 */
	public void removeItem(LineItem lineItem) {
		items.remove(lineItem);
	}

}