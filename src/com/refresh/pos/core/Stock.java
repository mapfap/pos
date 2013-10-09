package com.refresh.pos.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;

import com.refresh.pos.database.Dao;

class Stock {

	private Dao dao;
	private static final String TABLE_NAME = "stock";

	public Stock(Dao dao) {
		this.dao = dao;
	}
	
	public boolean addNewProductLot(String dateAdded, double amount, int productId, double cost) {
		ContentValues content = new ContentValues();
		content.put("date_added", dateAdded);
		content.put("amount", amount);
		content.put("productId", productId);
		content.put("cost", cost);
		long respond = dao.insert(TABLE_NAME, content);
		return respond != -1;
	}
	

	public List<ProductLot> getAllProductLotAsList() {
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

	public List<HashMap<String, String>> getAllProductLotAsMap() {
		List<HashMap<String, String>> productLotListOfMap = new ArrayList<HashMap<String, String>>();
		List<ProductLot> productList = getAllProductLotAsList();
		for (ProductLot p : productList) {
			String name = p.getProduct().getName();
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("name", name);
			map.put("amount", p.getAmount()+"");
			map.put("cost", p.getCost()+"");
			map.put("date_added", p.getDateAdded()+"");
			productLotListOfMap.add(map);
		}
		return productLotListOfMap;
	}

}
