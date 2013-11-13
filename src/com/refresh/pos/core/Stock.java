package com.refresh.pos.core;

import java.util.List;

import com.refresh.pos.database.InventoryDao;

public class Stock {

	private InventoryDao inventoryDao;

	public Stock(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}
	
	public boolean addProductLot(String dateAdded, double amount, int productId, double cost) {
		ProductLot productLot = new ProductLot(ProductLot.UNDEFINED_ID,dateAdded,amount,productId,cost);
		int id = inventoryDao.addProductLot(productLot);
		return id != -1;
	}
	

	public ProductLot getProductLotById(int id) {
		return null;
	}

	public List<ProductLot> getProductLotByProductId(int id) {
		return getAllProductLot();
	}

	public List<ProductLot> getAllProductLot() {

		return inventoryDao.getAllProductLot();
	}
	

}
