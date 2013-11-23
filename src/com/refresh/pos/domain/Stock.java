package com.refresh.pos.domain;

import java.util.List;

import com.refresh.pos.database.InventoryDao;

public class Stock {

	private InventoryDao inventoryDao;

	public Stock(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}
	
	public boolean addProductLot(String dateAdded, int quantity, Product product, double cost) {
		ProductLot productLot = new ProductLot(ProductLot. UNDEFINED_ID, dateAdded, quantity, product, cost);
		int id = inventoryDao.addProductLot(productLot);
		return id != -1;
	}
	

	public ProductLot getProductLotById(int id) {
		return null;
	}

	public List<ProductLot> getProductLotByProductId(int id) {
		return inventoryDao.getProductLotByProductId(id);
	}

	public List<ProductLot> getAllProductLot() {

		return inventoryDao.getAllProductLot();
	}

	public int getStockSumById(int id) {
		return inventoryDao.getStockSumById(id);
	}

	public void updateStockSum(int productId, int quantity) {
		inventoryDao.updateStockSum(productId,quantity);
		
	}

	public void clearStock() {
		inventoryDao.clearStock();
		
	}
	

}
