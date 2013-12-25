package com.refresh.pos.domain.inventory;

import java.util.List;

import com.refresh.pos.techicalservices.inventory.InventoryDao;

/**
 * Import log of ProductLot come in to store.
 * 
 * @author Refresh Team
 *
 */
public class Stock {

	private InventoryDao inventoryDao;

	/**
	 * Constructs Data Access Object of inventory in ProductCatalog.
	 * @param inventoryDao DAO of inventory.
	 */
	public Stock(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}
	
	/**
	 * Constructs ProductLot and adds ProductLot to inventory.
	 * @param dateAdded date added of ProductLot.
	 * @param quantity quantity of ProductLot.
	 * @param product product of ProductLot.
	 * @param cost cost of ProductLot.
	 * @return
	 */
	public boolean addProductLot(String dateAdded, int quantity, Product product, double cost) {
		ProductLot productLot = new ProductLot(ProductLot. UNDEFINED_ID, dateAdded, quantity, product, cost);
		int id = inventoryDao.addProductLot(productLot);
		return id != -1;
	}

	/**
	 * Returns list of ProductLot in inventory finds by id. 
	 * @param id id of ProductLot.
	 * @return list of ProductLot in inventory finds by id.
	 */
	public List<ProductLot> getProductLotByProductId(int id) {
		return inventoryDao.getProductLotByProductId(id);
	}

	/**
	 * Returns all ProductLots in inventory.
	 * @return all ProductLots in inventory.
	 */
	public List<ProductLot> getAllProductLot() {
		return inventoryDao.getAllProductLot();
	}

	/**
	 * Returns Stock in inventory finds by id.
	 * @param id id of Stock.
	 * @return Stock in inventory finds by id.
	 */
	public int getStockSumById(int id) {
		return inventoryDao.getStockSumById(id);
	}

	/**
	 * Updates quantity of product.
	 * @param productId id of product.
	 * @param quantity quantity of product.
	 */
	public void updateStockSum(int productId, int quantity) {
		inventoryDao.updateStockSum(productId,quantity);
		
	}

	/**
	 * Clear Stock.
	 */
	public void clearStock() {
		inventoryDao.clearStock();
		
	}
	

}
