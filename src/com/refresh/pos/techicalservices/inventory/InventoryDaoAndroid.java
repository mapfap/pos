package com.refresh.pos.techicalservices.inventory;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.util.Log;

import com.refresh.pos.domain.inventory.Product;
import com.refresh.pos.domain.inventory.ProductLot;
import com.refresh.pos.techicalservices.Database;
import com.refresh.pos.techicalservices.DatabaseContents;

/**
 * DAO used by android for Inventory.
 * 
 * @author Refresh Team
 *
 */
public class InventoryDaoAndroid implements InventoryDao {

	private Database database;
	
	/**
	 * Constructs InventoryDaoAndroid.
	 * @param database database for use in InventoryDaoAndroid.
	 */
	public InventoryDaoAndroid(Database database) {
		this.database = database;
	}

	@Override
	public int addProduct(Product product) {
		ContentValues content = new ContentValues();
        content.put("name", product.getName());
        content.put("barcode", product.getBarcode());
        content.put("unit_price", product.getUnitPrice());
        content.put("status", "ACTIVE");
        
        int id = database.insert(DatabaseContents.TABLE_PRODUCT_CATALOG.toString(), content);
        
        
    	ContentValues content2 = new ContentValues();
        content2.put("_id", id);
        content2.put("quantity", 0);
        database.insert(DatabaseContents.TABLE_STOCK_SUM.toString(), content2);
        
        return id;
	}
	
	/**
	 * Converts list of object to list of product.
	 * @param objectList list of object.
	 * @return list of product.
	 */
	private List<Product> toProductList(List<Object> objectList) {
		List<Product> list = new ArrayList<Product>();
        for (Object object: objectList) {
        	ContentValues content = (ContentValues) object;
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
        return getAllProduct(" WHERE status = 'ACTIVE'");
	}
	
	/**
	 * Returns list of all products in inventory.
	 * @param condition specific condition for getAllProduct.
	 * @return list of all products in inventory.
	 */
	private List<Product> getAllProduct(String condition) {
		String queryString = "SELECT * FROM " + DatabaseContents.TABLE_PRODUCT_CATALOG.toString() + condition + " ORDER BY name";
        List<Product> list = toProductList(database.select(queryString));
        return list;
	}
	
	/**
	 * Returns product from inventory finds by specific reference. 
	 * @param reference reference value.
	 * @param value value for search.
	 * @return list of product.
	 */
	private List<Product> getProductBy(String reference, String value) {
        String condition = " WHERE " + reference + " = " + value + " ;";
        return getAllProduct(condition);
	}
	
	/**
	 * Returns product from inventory finds by similar name.
	 * @param reference reference value.
	 * @param value value for search.
	 * @return list of product.
	 */
	private List<Product> getSimilarProductBy(String reference, String value) {
        String condition = " WHERE " + reference + " LIKE '%" + value + "%' ;";
        return getAllProduct(condition);
	}

	@Override
	public Product getProductByBarcode(String barcode) {
		List<Product> list = getProductBy("barcode", barcode);
        if (list.isEmpty()) return null;
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
        content.put("status", "ACTIVE");
        content.put("unit_price", product.getUnitPrice());
		return database.update(DatabaseContents.TABLE_PRODUCT_CATALOG.toString(), content);
	}
	
	
	@Override
	public int addProductLot(ProductLot productLot) {
		 ContentValues content = new ContentValues();
         content.put("date_added", productLot.getDateAdded());
         content.put("quantity",  productLot.getQuantity());
         content.put("product_id",  productLot.getProduct().getId());
         content.put("cost",  productLot.unitCost());
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
	
	/**
	 * Returns list of all ProductLot in inventory.
	 * @param condition specific condition for get ProductLot.
	 * @return list of all ProductLot in inventory.
	 */
	private List<ProductLot> getAllProductLot(String condition) {
		String queryString = "SELECT * FROM " + DatabaseContents.TABLE_STOCK.toString() + condition;
        List<ProductLot> list = toProductLotList(database.select(queryString));
        return list;
	}

	/**
	 * Converts list of object to list of ProductLot.
	 * @param objectList list of object.
	 * @return list of ProductLot.
	 */
	private List<ProductLot> toProductLotList(List<Object> objectList) {
		List<ProductLot> list = new ArrayList<ProductLot>();
		for (Object object: objectList) {
			ContentValues content = (ContentValues) object;
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
		String queryString = "SELECT * FROM " + DatabaseContents.TABLE_STOCK_SUM + " WHERE _id = " + id;
		List<Object> objectList = (database.select(queryString));
		ContentValues content = (ContentValues) objectList.get(0);
		int quantity = content.getAsInteger("quantity");
		Log.d("inventoryDaoAndroid", "stock sum of "+ id + " is " + quantity);
		return quantity;
	}

	@Override
	public void updateStockSum(int productId, double quantity) {
		 ContentValues content = new ContentValues();
         content.put("_id", productId);
         content.put("quantity", getStockSumById(productId) - quantity);
         database.update(DatabaseContents.TABLE_STOCK_SUM.toString(), content);   
	}

	@Override
	public void clearProductCatalog() {		
		database.execute("DELETE FROM " + DatabaseContents.TABLE_PRODUCT_CATALOG);
	}

	@Override
	public void clearStock() {
		database.execute("DELETE FROM " + DatabaseContents.TABLE_STOCK);
		database.execute("DELETE FROM " + DatabaseContents.TABLE_STOCK_SUM);
	}

	@Override
	public void suspendProduct(Product product) {
		ContentValues content = new ContentValues();
		content.put("_id", product.getId());
		content.put("name", product.getName());
		content.put("barcode", product.getBarcode());
		content.put("status", "INACTIVE");
		content.put("unit_price", product.getUnitPrice());
		database.update(DatabaseContents.TABLE_PRODUCT_CATALOG.toString(), content);
	}
	



}
