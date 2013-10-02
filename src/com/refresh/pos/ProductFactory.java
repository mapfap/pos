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
	
	public Product createProduct(int id, String name, String barcode, double price) {
		Product item = new Product(id, name, barcode, price);
		return item;
	}
	
	public Product createProduct(String name, String barcode, double price) {
		Product item = new Product(name, barcode, price);
		return item;
	}
}
