package com.refresh.pos.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.ContentValues;

import com.refresh.pos.database.Dao;

public class ProductCatalog {

	private Dao dao;
	private static final String TABLE_NAME = "product_catalog";

	public ProductCatalog(Dao dao) {
		this.dao = dao;
	}

	private static ProductFactory productFactory = ProductFactory.getInstance();

	public boolean addNewProduct(String name, String barcode, double salePrice) {
		ContentValues content = new ContentValues();
		content.put("name", name);
		content.put("barcode", barcode);
		content.put("sale_price", salePrice);
		
		long respond = dao.insert(TABLE_NAME, content);
		return respond != -1;
	}

	public boolean updateItem() {
		return false;
	}
	
	private List<Product> getProductBy(String reference, String value) {
		String queryString = "SELECT * FROM " + TABLE_NAME + " WHERE " + reference + " = " + value + " ;";
		@SuppressWarnings("unchecked")
		List<ContentValues> contents = (List) dao.select(queryString);
		List<Product> productList = new ArrayList<Product>();
		for (ContentValues content: contents) {
			productList.add(
				productFactory.createProduct( content.getAsInteger("_id"),
						content.getAsString("name"), content.getAsString("barcode"),content.getAsDouble("sale_price")
						 )
			);
		}
		return productList;
	}
	
	public Product getProductByBarcode(String barcode) {
		List<Product> list = getProductBy("barcode", barcode);
		if (list.isEmpty()) return Product.UNDEFINED_PRODUCT;
		return list.get(0);
	}
	
	public Product getProductById(int id) {
		return getProductBy("_id", id+"").get(0);
	}
	
	public List<Product> getProductByName(String name) {
		return getProductBy("name", name);
	}

	public List<Product> getAllProductAsList() {
		String queryString = "SELECT * FROM " + TABLE_NAME;
		@SuppressWarnings("unchecked")
		List<ContentValues> contents = (List) dao.select(queryString);
		List<Product> productList = new ArrayList<Product>();
		for (ContentValues content: contents) {
			productList.add( 
				productFactory.createProduct( content.getAsInteger("_id"),
						content.getAsString("name"), content.getAsString("barcode")
						,content.getAsDouble("sale_price"))
			);
		}
		return productList;
	}
	
	public List<HashMap<String, String>> getAllProductAsMap() {
		List<HashMap<String, String>> productListOfMap = new ArrayList<HashMap<String, String>>();
		List<Product> productList = getAllProductAsList();
		for (Product p : productList) {
			HashMap<String, String> map = new HashMap<String, String>();
	        map.put("_id", p.getId()+"");
	         map.put("name", p.getName());
	         map.put("barcode", p.getBarcode());
	         map.put("sale_price", p.getSalePrice()+"");
	         productListOfMap.add(map);
		}
		return productListOfMap;
	}
	
	public long getSize(){
		return dao.getSize();
	}
	
	public boolean setSalePrice(int id) {
		return false;
	}
	
	public boolean setName(int id) {
		return false;
	}
	
	public boolean setBarcode(int id) {
		return false;
	}

	
}
