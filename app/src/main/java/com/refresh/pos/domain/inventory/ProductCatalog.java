package com.refresh.pos.domain.inventory;

import java.util.List;

import com.refresh.pos.techicalservices.inventory.InventoryDao;

/**
 * Book that keeps list of Product.
 * 
 * @author Refresh Team
 *
 */
public class ProductCatalog {

	private InventoryDao inventoryDao;

	/**
	 * Constructs Data Access Object of inventory in ProductCatalog.
	 * @param inventoryDao DAO of inventory.
	 */
	public ProductCatalog(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	/**
	 * Constructs product and adds product to inventory.
	 * @param name name of product.
	 * @param barcode barcode of product.
	 * @param salePrice price of product.
	 * @return true if product adds in inventory success ; otherwise false.
	 */
	public boolean addProduct(String name, String barcode, double salePrice) {
		Product product = new Product(name, barcode, salePrice);		
		int id = inventoryDao.addProduct(product);
		return id != -1;
	}

	/**
	 * Edits product.
	 * @param product the product to be edited.
	 * @return true if product edits success ; otherwise false.
	 */
	public boolean editProduct(Product product) {
		boolean respond = inventoryDao.editProduct(product);
		return respond;
	}
		
	/**
	 * Returns product from inventory finds by barcode.
	 * @param barcode barcode of product.
	 * @return product
	 */
	public Product getProductByBarcode(String barcode) {
		return inventoryDao.getProductByBarcode(barcode);
	}
	
	/**
	 * Returns product from inventory finds by id.
	 * @param id id of product.
	 * @return product
	 */
	public Product getProductById(int id) {
		return inventoryDao.getProductById(id);
	}
	
	/**
	 * Returns list of all products in inventory.
	 * @return list of all products in inventory.
	 */
	public List<Product> getAllProduct() {
		return inventoryDao.getAllProduct();
	}

	/**
	 * Returns list of product in inventory finds by name.
	 * @param name name of product.
	 * @return list of product in inventory finds by name.
	 */
	public List<Product> getProductByName(String name) {
		return inventoryDao.getProductByName(name);
	}

	/**
	 * Search product from string in inventory.
	 * @param search string for searching.
	 * @return list of product.
	 */
	public List<Product> searchProduct(String search) {
		return inventoryDao.searchProduct(search);
	}

	/**
	 * Clears ProductCatalog.
	 */
	public void clearProductCatalog() {
		inventoryDao.clearProductCatalog();
	}
	
	/**
	 * Hidden product from inventory.
	 * @param product The product to be hidden.
	 */
	public void suspendProduct(Product product) {
		inventoryDao.suspendProduct(product);
	}

	
}
