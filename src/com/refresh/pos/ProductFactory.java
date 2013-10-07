package com.refresh.pos;

public class ProductFactory {
	
	private static ProductFactory instance;
	private ProductFactory() {
		
	}
	
	public static ProductFactory getInstance() {
		if (instance == null) {
			instance = new ProductFactory();
		}
		return instance;
	}
	
	public Product createProduct(int id, String name, String barcode) {
		Product product = new Product(id, name, barcode);
		return product;
	}
	
	public Product createProduct(String name, String barcode) {
		Product product = new Product(name, barcode);
		return product;
	}
}
