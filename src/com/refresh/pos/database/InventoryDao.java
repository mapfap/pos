package com.refresh.pos.database;

import java.util.List;

import com.refresh.pos.core.Product;
import com.refresh.pos.core.ProductLot;

public interface InventoryDao {

	int addProduct(Product product);
	List<Product> getAllProduct();
	Product getProductByBarcode(String barcode);
	Product getProductById(int id);
	boolean editProduct(Product product);

	int addProductLot(ProductLot productLot);
	List<ProductLot> getAllProductLot();
	List<Product> getProductByName(String name);
	
}
