package com.refresh.pos.core;

import java.util.ArrayList;
import java.util.List;

import com.refresh.pos.database.Dao;
 class Stock {
	
	private Dao dao;
	public Stock(Dao dao) {
		
	}

	public List<ProductLot> getAllProductLot() {
		return new ArrayList<ProductLot>();
	}
	
	public ProductLot getProductLotById(int id) {
		return null;
	}
	
	public ProductLot getProductLotByProductId(int id) {
		return null;
	}
	
	public List<ProductLot> getProductLotByCost(double cost) {
		return null;
	}
	
	public List<ProductLot> getProductLotByAmount(long amount) {
		return null;
	}
	
	public List<ProductLot> getProductLotByDateAdded(String date) {
		return new ArrayList<ProductLot>();
	}
	
}
