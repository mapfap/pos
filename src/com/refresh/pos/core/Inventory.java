package com.refresh.pos.core;

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

}
