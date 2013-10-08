package com.refresh.pos.core;


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
	
	public Product createProduct(int id, String name, String barcode, double salePrice) {
		Product product = new Product(id, name, barcode, salePrice);
		return product;
	}
	
	public Product createProduct(String name, String barcode, double salePrice) {
		Product product = new Product(Product.UNDEFINED, name, barcode, salePrice);
		return product;
	}
	
}
