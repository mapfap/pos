package com.refresh.pos;

import java.util.ArrayList;
import java.util.HashMap;

public class ProductCatalogController {
	private ProductFactory productFactory;
	private ProductCatalog productCatalog;

	public ProductCatalogController(ProductDao productDao) {
		productCatalog = new ProductCatalog(productDao);
		productFactory = ProductFactory.getInstance();
	}

	public boolean add(String name,String barcode) {
		Product product = productFactory.createProduct(name,
				barcode);
		boolean success = productCatalog.addNewProduct(product);
		return success;
	}

	public long getSize() {
		return productCatalog.getSize();
	}
	
	public ArrayList<HashMap<String, String>> selectAllData() {
		return productCatalog.selectAllData();
	}

	
}
