package com.refresh.pos.techicalservices.inventory;

import java.util.List;

import com.refresh.pos.domain.inventory.Product;
import com.refresh.pos.domain.inventory.ProductLot;

/**
 * DAO for Inventory.
 * 
 * @author Refresh Team
 *
 */
public interface InventoryDao {

	int addProduct(Product product);
	int addProductLot(ProductLot productLot);

	boolean editProduct(Product product);

	Product getProductById(int id);
	Product getProductByBarcode(String barcode);
	
	List<Product> getAllProduct();
	List<Product> getProductByName(String name);
	List<Product> searchProduct(String search);
	
	List<ProductLot> getAllProductLot();
	List<ProductLot> getProductLotById(int id);
	List<ProductLot> getProductLotByProductId(int id);
	
	int getStockSumById(int id);
	void updateStockSum(int productId, double quantity);
	void clearProductCatalog();
	void clearStock();
	void suspendProduct(Product product);

}
