package com.refresh.pos.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.ContentValues;

import com.refresh.pos.database.Dao;

class Stock {

	private Dao dao;
	private static final String TABLE_NAME = "stock";
	private static ProductLotFactory productLotFactory = ProductLotFactory.getInstance();

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

	public List<ProductLot> getAllProductLotAsList() {
		String queryString = "SELECT * FROM " + TABLE_NAME;
		@SuppressWarnings("unchecked")
		List<ContentValues> contents = (List) dao.select(queryString);
		List<ProductLot> productLotList = new ArrayList<ProductLot>();
		for (ContentValues content: contents) {
			productLotList.add( 
				productLotFactory.createProductLot( content.getAsInteger("_id"),
						content.getAsString("date_added"),
						content.getAsDouble("amount"),
						content.getAsInteger("product_id"),
						content.getAsDouble("cost"))
			);
		}
		return productLotList;
	}
	
	public List<HashMap<String, String>> getAllProductLotAsMap() {
		List<HashMap<String, String>> productLotListOfMap = new ArrayList<HashMap<String, String>>();
		List<ProductLot> productLotList = getAllProductLotAsList();
		for (ProductLot pl : productLotList) {
			
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("_id", pl.getId()+"");
	        map.put("date_added", pl.getDateAdded());
	        map.put("amount", pl.getAmount()+"");
	        map.put("product_id", pl.getProductId()+"");
	        map.put("cost", pl.getCost()+"");
			productLotListOfMap.add(map);
		}
		return productLotListOfMap;
	}

}
