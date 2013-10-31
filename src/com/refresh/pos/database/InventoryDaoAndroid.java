package com.refresh.pos.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;

import com.refresh.pos.core.Product;
import com.refresh.pos.core.ProductLot;

public class InventoryDaoAndroid implements InventoryDao {

	private Database database;
	
	public InventoryDaoAndroid(Database database) {
		this.database = database;
	}

	@Override
	public int addProduct(Product product) {
		ContentValues content = new ContentValues();
        content.put("name", product.getName());
        content.put("barcode", product.getBarcode());
        content.put("sale_price", product.getSalePrice());
        
        int id = database.insert(DatabaseContents.TABLE_PRODUCT_CATALOG.toString(), content);
        return id;
	}


	@Override
	public List<Product> getAllProduct() {
		String queryString = "SELECT * FROM " + DatabaseContents.TABLE_PRODUCT_CATALOG.toString();
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
        String queryString = "SELECT * FROM " + DatabaseContents.TABLE_PRODUCT_CATALOG.toString() + " WHERE " + reference + " = " + value + " ;";
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
	public int addProductLot(ProductLot productLot) {
		 ContentValues content = new ContentValues();
         content.put("date_added", productLot.getDateAdded());
         content.put("quantity",  productLot.getQuantity());
         content.put("product_Id",  productLot.getProductId());
         content.put("cost",  productLot.getCost());
         int id = database.insert(DatabaseContents.TABLE_STOCK.toString(), content);
         return id;
	}

	@Override
	public List<ProductLot> getAllProductLot() {
		String queryString = "SELECT * FROM " + DatabaseContents.TABLE_STOCK.toString();
        @SuppressWarnings("unchecked")
        List<ContentValues> contents = (List) database.select(queryString);
        List<ProductLot> list = new ArrayList<ProductLot>();
        for (ContentValues content: contents) {
                list.add( 
                        new ProductLot(content.getAsInteger("_id"),
                                        content.getAsString("date_added"),
                                        content.getAsDouble("quantity"),
                                        content.getAsInteger("product_id"),
                                        content.getAsDouble("cost"))
                );
        }
        return list;
	}



}
