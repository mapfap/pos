package com.refresh.pos.core;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;

import com.refresh.pos.database.Dao;

public class ProductCatalog {

	private Dao dao;
	private static final String TABLE_NAME = "product_catalog";

	public ProductCatalog(Dao dao) {
		this.dao = dao;
	}

	private static ProductFactory productFactory = ProductFactory.getInstance();

	public boolean addNewProduct(Product product) {
		ContentValues content = new ContentValues();
		content.put("name", product.getName());
		content.put("barcode", product.getBarcode());
		content.put("sale_price", product.getSalePrice());
		
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
		if (list.isEmpty()) return productFactory.createProduct(Product.UNDEFINED,"name","barcode",0);
		return list.get(0);
	}
	
	public Product getProductById(int id) {
		return getProductBy("_id", id+"").get(0);
	}
	
	public List<Product> getProductByName(String name) {
		return getProductBy("name", name);
	}

	public List<Product> getAllProduct() {
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
