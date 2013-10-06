package com.refresh.pos;

public class ProductCatalogController {
	private ProductFactory productFactory;
	private ProductCatalog productCatalog;

	public ProductCatalogController(ProductDao productDao) {
		productCatalog = new ProductCatalog(productDao);
		productFactory = ProductFactory.getInstance();
	}

	public boolean add(String name,String barcode,double price) {
		Product product = productFactory.createProduct(name,
				barcode, price);
		boolean success = productCatalog.addNewProduct(product);
		return success;
	}

	public long getSize() {
		return productCatalog.getSize();
	}
}
