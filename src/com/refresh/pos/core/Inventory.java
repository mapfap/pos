package com.refresh.pos.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.refresh.pos.database.Dao;
import com.refresh.pos.database.NoDaoSetException;

public class Inventory {
	private Stock stock;
	private ProductCatalog productCatalog;
	private static Inventory instance = null;
	private static Dao dao = null;
	private static ProductFactory productFactory;
	
	private Inventory() throws NoDaoSetException {
		if (!isInventoryDaoSet()) {
			throw new NoDaoSetException();
		}
		stock = new Stock(dao);
		productCatalog = new ProductCatalog(dao);
		productFactory = ProductFactory.getInstance();
	}
	
	public static boolean isInventoryDaoSet() {
		return dao != null;
	}
	
	public static void setProductDao(Dao d) {
		dao = d;
	}
	
	public ProductCatalog getProductCatalog() {
		return productCatalog;
	}
	
	public Stock getStock() {
		return stock;
	}
	
	public static Inventory getInstance() throws NoDaoSetException {
		if (instance == null) instance = new Inventory();
		return instance;
	}

	public boolean addNewProduct(String name, String barcode, int price) {
		return productCatalog.addNewProduct(productFactory.createProduct(name, barcode, price));
	}

	// Show All Data
	public ArrayList<HashMap<String, String>> SelectAllStock() {
		ArrayList<HashMap<String, String>> stockList = new ArrayList<HashMap<String, String>>();
		List<Product> productList = this.getProductCatalog().getAllProduct();
		for (Product product : productList) {
			HashMap<String, String> map = new HashMap<String, String>();
	        map.put("_id", product.getId()+"");
 	        map.put("name", product.getName());
 	        map.put("barcode", product.getBarcode());
 	        map.put("sale_price", product.getSalePrice()+"");
 	        stockList.add(map);
		}
		return stockList;

	}

}
