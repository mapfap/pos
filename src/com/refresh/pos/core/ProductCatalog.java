package com.refresh.pos.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.refresh.pos.database.ProductDao;

import android.content.ContentValues;

public class ProductCatalog {

	private ProductDao productDao;
	private static final String TABLE_NAME = "product_catalog";

	public ProductCatalog(ProductDao productDao) {
		this.productDao = productDao;
	}

	private static ProductFactory productFactory = ProductFactory.getInstance();

	public boolean addNewProduct(Product product) {
		ContentValues content = new ContentValues();
		content.put("name", product.name);
		content.put("barcode", product.barcode);
		long respond = productDao.insert(TABLE_NAME, content);
		return respond != -1;
	}

	public boolean updateItem() {
		return false;
	}
	
	private List<Product> getProductBy(String reference, String value) {
		String queryString = "SELECT * FROM " + TABLE_NAME + "WHERE " + reference + " = " + value;
		@SuppressWarnings("unchecked")
		List<ContentValues> contents = (List) productDao.select(queryString);
		List<Product> productList = new ArrayList<Product>();
		for (ContentValues content: contents) {
			productList.add(
				productFactory.createProduct( content.getAsInteger("id"),
						content.getAsString("name"), content.getAsString("barcode")
						 )
			);
		}
		return productList;
	}
	
	public Product getProductByBarcode(String barcode) {
		return getProductBy("barcode", barcode).get(0);
	}
	
	public Product getProductById(String id) {
		return getProductBy("_id", id).get(0);
	}
	
	public List<Product> getProductByName(String name) {
		return getProductBy("name", name);
	}

	public List<Product> getAllProduct() {
		String queryString = "SELECT * FROM " + TABLE_NAME;
		@SuppressWarnings("unchecked")
		List<ContentValues> contents = (List) productDao.select(queryString);
		List<Product> productList = new ArrayList<Product>();
		for (ContentValues content: contents) {
			productList.add(
				productFactory.createProduct( content.getAsInteger("id"),
						content.getAsString("name"), content.getAsString("barcode")
						 )
			);
		}
		return productList;
	}
	
	public long getSize(){
		return productDao.getSize();
	}
	
	public ArrayList<HashMap<String, String>> selectAllData() {
		return productDao.selectAllData();
	}

	
}
