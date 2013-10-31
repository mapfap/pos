package com.refresh.pos.core;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;


public class Sale extends Observable {
	
	private Calendar startTime;
	private Calendar endTime;
	private String getNote;
	private List<LineItem> items;
	private final int id;

	public Sale(int id, Calendar startTime) {
		this.id = id;
		items = new ArrayList<LineItem>( );
		startTime = Calendar.getInstance();
	}

	public boolean addLineItem(Product product, int quantity) {
		
		for (LineItem lineItem : items) {
			if (lineItem.getProductId() == product.getId()) {
				lineItem.addQuantity(quantity);
				super.setChanged();
				super.notifyObservers();
				return true;
			}
		}
		
		if ( items.add(new LineItem(product, quantity)) ) {
			super.setChanged();
			super.notifyObservers();
			return true;
		}
		return false;
	}
	
	public int size() {
		return items.size();
	}
	
	public LineItem getLineItem(int index) {
		if (index >= 0 && index < items.size())
			return items.get(index);
		return null;
	}

	public Calendar getDate() {
		return startTime;
	}

	public double getTotal() {
		double total = 0.0;
		for( LineItem lineItem : items ) {
			total += lineItem.getTotal();
		}
		return total;
	}



}
