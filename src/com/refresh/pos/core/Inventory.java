package com.refresh.pos.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.refresh.pos.database.Dao;
import com.refresh.pos.database.NoDaoSetException;

public class Inventory {
	private Stock stock;
	private ProductCatalog productCatalog;
	private static Inventory instance = null;
	private static Dao inventoryDao = null;
	private static ProductFactory productFactory;
	
	private Inventory() throws NoDaoSetException {
		if (!isInventoryDaoSet()) {
			throw new NoDaoSetException();
		}
		stock = new Stock(inventoryDao);
		productCatalog = new ProductCatalog(inventoryDao);
		productFactory = ProductFactory.getInstance();
	}
	
	public static boolean isInventoryDaoSet() {
		return inventoryDao != null;
	}
	
	public static void setProductDao(Dao dao) {
		inventoryDao = dao;
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
		productCatalog.addNewProduct(productFactory.createProduct(name, barcode, price));
		return false;
	}

	public Product getProductbyBarcode(String barcode) {
		return productCatalog.getProductByBarcode(barcode);
	}
	
	public Product getProductbyId(int id) {
		return productCatalog.getProductById(id);
	}

}
