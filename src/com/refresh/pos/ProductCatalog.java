package com.refresh.pos;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;

public class ProductCatalog {

	private ProductDao productDao;
	private static final String TABLE_NAME = "inventory";

	public ProductCatalog(ProductDao productDao) {
		this.productDao = productDao;
	}

	private static ProductFactory productFactory = ProductFactory.getInstance();

	public boolean addNewProduct(Product product) {
		ContentValues content = new ContentValues();
		content.put("name", product.name);
		content.put("barcode", product.barcode);
		content.put("price", product.price);
		long respond = productDao.insert(TABLE_NAME, content);
		return respond != -1;
	}

	public boolean updateItem() {
		return false;
	}

	public List<Product> getProduct() {
		String queryString = "SELECT * FROM " + TABLE_NAME;
		@SuppressWarnings("unchecked")
		List<ContentValues> contents = (List) productDao.select(queryString);
		List<Product> itemList = new ArrayList<Product>();
		for (ContentValues content: contents) {
			itemList.add(
				productFactory.createProduct( content.getAsInteger("id"),
						content.getAsString("name"), content.getAsString("barcode"),
						content.getAsDouble("price") )
			);
		}
		return itemList;
	}
	public long getSize(){
		return productDao.getSize();
	}
}
