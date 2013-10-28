package com.refresh.pos.database;

import java.util.List;

import com.refresh.pos.core.Product;
import com.refresh.pos.core.ProductLot;

public interface InventoryDao {

	long addProduct(Product product);

	long addProductLot(ProductLot productLot);

	List<Product> getAllProduct();

	Product getProductByBarcode(String barcode);

	Product getProductById(int id);

	List<ProductLot> getAllProductLot();

	boolean editProduct(Product product);
	
}
