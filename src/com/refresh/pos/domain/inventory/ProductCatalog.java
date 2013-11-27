package com.refresh.pos.domain.inventory;

import java.util.List;

import com.refresh.pos.techicalservices.invnetory.InventoryDao;

public class ProductCatalog {

	private InventoryDao inventoryDao;

	public ProductCatalog(InventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
	}

	public boolean addProduct(String name, String barcode, double salePrice) {
		Product product = new Product(Product.UNDEFINED_ID, name, barcode, salePrice);		
		int id = inventoryDao.addProduct(product);
		return id != -1;
	}

	public boolean editProduct(Product product) {
		boolean respond = inventoryDao.editProduct(product);
		return respond;
	}
		
	public Product getProductByBarcode(String barcode) {
		return inventoryDao.getProductByBarcode(barcode);
	}
	
	public Product getProductById(int id) {
		return inventoryDao.getProductById(id);
	}
	
	public List<Product> getAllProduct() {
		return inventoryDao.getAllProduct();
	}

	public List<Product> getProductByName(String name) {
		return inventoryDao.getProductByName(name);
	}

	public List<Product> searchProduct(String search) {
		return inventoryDao.searchProduct(search);
	}

	public void clearProductCatalog() {
		inventoryDao.clearProductCatalog();
	}
	
	public void suspendProduct(Product product) {
		inventoryDao.suspendProduct(product);
	}

	
}
