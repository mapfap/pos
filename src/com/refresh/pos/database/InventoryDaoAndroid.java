package com.refresh.pos.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;

import com.refresh.pos.core.Product;
import com.refresh.pos.core.ProductLot;

public class InventoryDaoAndroid implements InventoryDao {

	private Database database;
	private static final String TABLE_PRODUCT_CATALOG = "product_catalog";
	private static final String TABLE_STOCK = "stock";
	
	public InventoryDaoAndroid(Database database) {
		this.database = database;
	}

	@Override
	public long addProduct(Product product) {
		ContentValues content = new ContentValues();
        content.put("name", product.getName());
        content.put("barcode", product.getBarcode());
        content.put("sale_price", product.getSalePrice());
        
        long respond = database.insert(TABLE_PRODUCT_CATALOG, content);
        return respond;
	}


	@Override
	public List<Product> getAllProduct() {
		String queryString = "SELECT * FROM " + TABLE_PRODUCT_CATALOG;
        @SuppressWarnings("unchecked")
        List<ContentValues> contents = (List) database.select(queryString);
        List<Product> list = new ArrayList<Product>();
        for (ContentValues content: contents) {
                list.add( 
                        new Product( content.getAsInteger("_id"),
                                        content.getAsString("name"), content.getAsString("barcode")
                                        ,content.getAsDouble("sale_price"))
                );
        }
        return list;
	}
	
	private List<Product> getProductBy(String reference, String value) {
        String queryString = "SELECT * FROM " + TABLE_PRODUCT_CATALOG + " WHERE " + reference + " = " + value + " ;";
        @SuppressWarnings("unchecked")
        List<ContentValues> contents = (List) database.select(queryString);
        List<Product> productList = new ArrayList<Product>();
        for (ContentValues content: contents) {
                productList.add(
                        new Product(content.getAsInteger("_id"),
                                        content.getAsString("name"), content.getAsString("barcode"),content.getAsDouble("sale_price")
                                         )
                );
        }
        return productList;
}

	@Override
	public Product getProductByBarcode(String barcode) {
		List<Product> list = getProductBy("barcode", barcode);
        if (list.isEmpty()) return Product.UNDEFINED_PRODUCT;
        return list.get(0);
	}

	@Override
	public Product getProductById(int id) {
		return getProductBy("_id", id+"").get(0);
	}

	@Override
	public boolean editProduct(Product product) {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	@Override
	public long addProductLot(ProductLot productLot) {
		 ContentValues content = new ContentValues();
         content.put("date_added", productLot.getDateAdded());
         content.put("amount",  productLot.getAmount());
         content.put("product_Id",  productLot.getProductId());
         content.put("cost",  productLot.getCost());
         long respond = database.insert(TABLE_STOCK, content);
         return respond;
	}

	@Override
	public List<ProductLot> getAllProductLot() {
		String queryString = "SELECT * FROM " + TABLE_STOCK;
        @SuppressWarnings("unchecked")
        List<ContentValues> contents = (List) database.select(queryString);
        List<ProductLot> list = new ArrayList<ProductLot>();
        for (ContentValues content: contents) {
                list.add( 
                        new ProductLot(content.getAsInteger("_id"),
                                        content.getAsString("date_added"),
                                        content.getAsDouble("amount"),
                                        content.getAsInteger("product_id"),
                                        content.getAsDouble("cost"))
                );
        }
        return list;
	}



}
