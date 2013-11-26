package com.refresh.pos.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.util.Log;

import com.refresh.pos.domain.LineItem;
import com.refresh.pos.domain.Product;
import com.refresh.pos.domain.Sale;



public class SaleDaoAndroid implements SaleDao {

	Database database;
	public SaleDaoAndroid(Database database) {
		this.database = database;
	}
	
	public Sale initiateSale(String startTime) {
		ContentValues content = new ContentValues();
        content.put("start_time", startTime.toString());
        content.put("status", "ON PROCESS");
        content.put("payment", "n/a");
        content.put("total", "0.0");
        content.put("orders", "0");
        content.put("end_time", startTime.toString());
        
        int id = database.insert(DatabaseContents.TABLE_SALE.toString(), content);
		return new Sale(id,startTime);
	}

	@Override
	public void endSale(Sale sale, String endTime) {
		ContentValues content = new ContentValues();
        content.put("_id", sale.getId());
        content.put("status", "ENDED");
        content.put("payment", "n/a");
        content.put("total", sale.getTotal());
        content.put("orders", sale.getOrders());
        content.put("start_time", sale.getStartTime());
        content.put("end_time", endTime);
		database.update(DatabaseContents.TABLE_SALE.toString(), content);
	}
	
	@Override
	public int addLineItem(int saleId, LineItem lineItem) {
		ContentValues content = new ContentValues();
        content.put("sale_id", saleId);
        content.put("product_id", lineItem.getProduct().getId());
        content.put("quantity", lineItem.getQuantity());
        content.put("unit_price", lineItem.getTotal());
        int id = database.insert(DatabaseContents.TABLE_SALE_LINEITEM.toString(), content);
        return id;
	}

	@Override
	public List<Sale> getAllSale() {
		return getAllSale("");
	}
	
	@Override
	public List<Sale> getAllSaleDuring(String start, String end) {
		// TODO : not yet implemeted.
		return getAllSale("");
	}
	
	
	/**
	 * This method get all Sale *BUT* no LineItem will be loaded.
	 * @param condition
	 * @return
	 */
	public List<Sale> getAllSale(String condition) {
		String queryString = "SELECT * FROM " + DatabaseContents.TABLE_SALE.toString() + condition;
        @SuppressWarnings("unchecked")
        List<ContentValues> contents = (List) database.select(queryString);
        Log.d("saleDaoAndr", "all sale = "+ contents.size());
        List<Sale> list = new ArrayList<Sale>();
        for (ContentValues content: contents) {
                list.add(new Sale(
                		content.getAsInteger("_id"),
                        content.getAsString("start_time"),
                        content.getAsString("end_time"),
                        content.getAsString("status"),
                        content.getAsDouble("total"),
                        content.getAsInteger("orders")
                        
                		)
                );
        }
        return list;
	}
	
	/**
	 * This load complete data of Sale.
	 * @param id Sale ID.
	 * @return Sale of specific ID.
	 */
	@Override
	public Sale getSaleById(int id) {
		String queryString = "SELECT * FROM " + DatabaseContents.TABLE_SALE.toString() + " WHERE _id = " + id;
        @SuppressWarnings("unchecked")
        List<ContentValues> contents = (List) database.select(queryString);
        List<Sale> list = new ArrayList<Sale>();
        for (ContentValues content: contents) {
                list.add(new Sale(
                		content.getAsInteger("_id"),
                        content.getAsString("start_time"),
                        content.getAsString("end_time"),
                        content.getAsString("status"),
                        getLineItem(content.getAsInteger("_id")))
                );
        }
        return list.get(0);
	}

	@Override
	public List<LineItem> getLineItem(int saleId) {
		String queryString = "SELECT * FROM " + DatabaseContents.TABLE_SALE_LINEITEM.toString() + " WHERE sale_id = " + saleId;
		@SuppressWarnings("unchecked")
		List<ContentValues> contents = (List) database.select(queryString);
		List<LineItem> list = new ArrayList<LineItem>();
		for (ContentValues content: contents) {

			int productId = content.getAsInteger("product_id");
			String queryString2 = "SELECT * FROM " + DatabaseContents.TABLE_PRODUCT_CATALOG.toString() + " WHERE _id = " + productId;
			@SuppressWarnings("unchecked")
			List<ContentValues> contents2 = (List) database.select(queryString2);
			List<Product> productList = new ArrayList<Product>();
			for (ContentValues content2: contents2) {   	
				productList.add(new Product(productId, content2.getAsString("name"), content2.getAsString("barcode"), content2.getAsDouble("unit_price")));
			}
			list.add(new LineItem(productList.get(0), content.getAsInteger("quantity")));
		}
		return list;
	}

	@Override
	public void clearSaleLedger() {
		// TODO: don't forget to DELETE in line item 
//		database.delete(DatabaseContents.TABLE_SALE.toString(), "");
	}

}
