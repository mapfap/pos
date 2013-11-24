package com.refresh.pos.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.util.Log;

import com.refresh.pos.domain.Product;
import com.refresh.pos.domain.ProductLot;

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
        content.put("unit_price", product.getUnitPrice());
        
        int id = database.insert(DatabaseContents.TABLE_PRODUCT_CATALOG.toString(), content);
        
        
    	ContentValues content2 = new ContentValues();
        content2.put("_id", id);
        content2.put("quantity", 0);
        database.insert(DatabaseContents.TABLE_STOCK_SUM.toString(), content2);
        
        return id;
	}
	
	
	
	private List<Product> toProductList(List<ContentValues> contents) {
		List<Product> list = new ArrayList<Product>();
        for (ContentValues content: contents) {
                list.add(new Product(
                		content.getAsInteger("_id"),
                        content.getAsString("name"),
                        content.getAsString("barcode"),
                        content.getAsDouble("unit_price"))
                );
        }
        return list;
	}

	@Override
	public List<Product> getAllProduct() {
        return getAllProduct("");
	}
	
	private List<Product> getAllProduct(String condition) {
		String queryString = "SELECT * FROM " + DatabaseContents.TABLE_PRODUCT_CATALOG.toString() + condition;
        @SuppressWarnings("unchecked")
        List<Product> list = toProductList((List) database.select(queryString));
        return list;
	}
	
	private List<Product> getProductBy(String reference, String value) {
        String condition = " WHERE " + reference + " = " + value + " ;";
        return getAllProduct(condition);
	}
	
	private List<Product> getSimilarProductBy(String reference, String value) {
        String condition = " WHERE " + reference + " LIKE '%" + value + "%' ;";
        return getAllProduct(condition);
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
		ContentValues content = new ContentValues();
		content.put("_id", product.getId());
		content.put("name", product.getName());
        content.put("barcode", product.getBarcode());
        content.put("unit_price", product.getUnitPrice());
		return database.update(DatabaseContents.TABLE_PRODUCT_CATALOG.toString(), content);
	}
	
	
	@Override
	public int addProductLot(ProductLot productLot) {
		 ContentValues content = new ContentValues();
         content.put("date_added", productLot.getDateAdded());
         content.put("quantity",  productLot.getQuantity());
         content.put("product_id",  productLot.getProduct().getId());
         content.put("cost",  productLot.getCost());
         int id = database.insert(DatabaseContents.TABLE_STOCK.toString(), content);
         
         int productId = productLot.getProduct().getId();
         ContentValues content2 = new ContentValues();
         content2.put("_id", productId);
         content2.put("quantity", getStockSumById(productId) + productLot.getQuantity());
         Log.d("inventory dao android","" + getStockSumById(productId) + " " + productId + " " +productLot.getQuantity() );
         database.update(DatabaseContents.TABLE_STOCK_SUM.toString(), content2);   
         
         return id;
	}


	@Override
	public List<Product> getProductByName(String name) {
		return getSimilarProductBy("name", name);
	}

	@Override
	public List<Product> searchProduct(String search) {
		String condition = " WHERE name LIKE '%" + search + "%' OR barcode LIKE '%" + search + "%' ;";
        return getAllProduct(condition);
	}
	
	private List<ProductLot> getAllProductLot(String condition) {
		String queryString = "SELECT * FROM " + DatabaseContents.TABLE_STOCK.toString() + condition;
        @SuppressWarnings("unchecked")
        List<ProductLot> list = toProductLotList((List) database.select(queryString));
        return list;
	}

	private List<ProductLot> toProductLotList(List<ContentValues> contents) {
		List<ProductLot> list = new ArrayList<ProductLot>();
		for (ContentValues content: contents) {
			int productId = content.getAsInteger("product_id");
			Product product = getProductById(productId);
					list.add( 
					new ProductLot(content.getAsInteger("_id"),
							content.getAsString("date_added"),
							content.getAsInteger("quantity"),
							product,
							content.getAsDouble("cost"))
					);
		}
		return list;
	}

	@Override
	public List<ProductLot> getProductLotByProductId(int id) {
		return getAllProductLot(" WHERE product_id = " + id);
	}
	
	@Override
	public List<ProductLot> getProductLotById(int id) {
		return getAllProductLot(" WHERE _id = " + id);
	}

	@Override
	public List<ProductLot> getAllProductLot() {
		return getAllProductLot("");
	}

	@Override
	public int getStockSumById(int id) {
//		return 11;
		String queryString = "SELECT * FROM " + DatabaseContents.TABLE_STOCK_SUM.toString() + " WHERE _id = " + id;
        @SuppressWarnings("unchecked")
		List<ContentValues> contents = ((List) database.select(queryString));
		int quantity = contents.get(0).getAsInteger("quantity");
		Log.d("inventoryDaoAndroid", "stock sum of "+ id + " is " + quantity);
		return quantity;
	}

	@Override
	public void updateStockSum(int productId, double quantity) {
		 ContentValues content = new ContentValues();
         content.put("_id", productId);
         content.put("quantity", getStockSumById(productId) - quantity);
//         Log.d("fff","" + getStockSumById(id) + " " + id + " " +productLot.getQuantity() );
         database.update(DatabaseContents.TABLE_STOCK_SUM.toString(), content);   
	}

	@Override
	public void clearProductCatalog() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void clearStock() {
		// TODO Auto-generated method stub
		
	}


}
