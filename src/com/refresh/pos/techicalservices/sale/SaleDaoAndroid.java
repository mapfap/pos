package com.refresh.pos.techicalservices.sale;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import android.content.ContentValues;

import com.refresh.pos.domain.DateTimeStrategy;
import com.refresh.pos.domain.inventory.LineItem;
import com.refresh.pos.domain.inventory.Product;
import com.refresh.pos.domain.sale.QuickLoadSale;
import com.refresh.pos.domain.sale.Sale;
import com.refresh.pos.techicalservices.Database;
import com.refresh.pos.techicalservices.DatabaseContents;


/**
 * DAO used by android for Sale process.
 * 
 * @author Refresh Team
 *
 */
public class SaleDaoAndroid implements SaleDao {

	Database database;
	public SaleDaoAndroid(Database database) {
		this.database = database;
	}

	@Override
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
        content.put("unit_price", lineItem.getPriceAtSale());
        int id = database.insert(DatabaseContents.TABLE_SALE_LINEITEM.toString(), content);
        return id;
	}

	@Override
	public void updateLineItem(int saleId, LineItem lineItem) {
		ContentValues content = new ContentValues();		
		content.put("_id", lineItem.getId());
		content.put("sale_id", saleId);
		content.put("product_id", lineItem.getProduct().getId());
		content.put("quantity", lineItem.getQuantity());
		content.put("unit_price", lineItem.getPriceAtSale());
		database.update(DatabaseContents.TABLE_SALE_LINEITEM.toString(), content);
	}

	@Override
	public List<Sale> getAllSale() {
		return getAllSale(" WHERE status = 'ENDED'");
	}
	
	@Override
	public List<Sale> getAllSaleDuring(Calendar start, Calendar end) {
		String startBound = DateTimeStrategy.getSQLDateFormat(start);
		String endBound = DateTimeStrategy.getSQLDateFormat(end);
		List<Sale> list = getAllSale(" WHERE end_time BETWEEN '" + startBound + " 00:00:00' AND '" + endBound + " 23:59:59' AND status = 'ENDED'"); 
		return list;
	}
	
	/**
	 * This method get all Sale *BUT* no LineItem will be loaded.
	 * @param condition
	 * @return
	 */
	public List<Sale> getAllSale(String condition) {
		String queryString = "SELECT * FROM " + DatabaseContents.TABLE_SALE + condition;
        List<Object> objectList = database.select(queryString);
        List<Sale> list = new ArrayList<Sale>();
        for (Object object: objectList) {
        	ContentValues content = (ContentValues) object;
        	list.add(new QuickLoadSale(
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
		String queryString = "SELECT * FROM " + DatabaseContents.TABLE_SALE + " WHERE _id = " + id;
        List<Object> objectList = database.select(queryString);
        List<Sale> list = new ArrayList<Sale>();
        for (Object object: objectList) {
        	ContentValues content = (ContentValues) object;
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
		String queryString = "SELECT * FROM " + DatabaseContents.TABLE_SALE_LINEITEM + " WHERE sale_id = " + saleId;
		List<Object> objectList = database.select(queryString);
		List<LineItem> list = new ArrayList<LineItem>();
		for (Object object: objectList) {
			ContentValues content = (ContentValues) object;
			int productId = content.getAsInteger("product_id");
			String queryString2 = "SELECT * FROM " + DatabaseContents.TABLE_PRODUCT_CATALOG + " WHERE _id = " + productId;
			List<Object> objectList2 = database.select(queryString2);
			
			List<Product> productList = new ArrayList<Product>();
			for (Object object2: objectList2) {
				ContentValues content2 = (ContentValues) object2;
				productList.add(new Product(productId, content2.getAsString("name"), content2.getAsString("barcode"), content2.getAsDouble("unit_price")));
			}
			list.add(new LineItem(content.getAsInteger("_id") , productList.get(0), content.getAsInteger("quantity"), content.getAsDouble("unit_price")));
		}
		return list;
	}

	@Override
	public void clearSaleLedger() {
		database.execute("DELETE FROM " + DatabaseContents.TABLE_SALE);
		database.execute("DELETE FROM " + DatabaseContents.TABLE_SALE_LINEITEM);
	}

	@Override
	public void cancelSale(Sale sale,String endTime) {
		ContentValues content = new ContentValues();
        content.put("_id", sale.getId());
        content.put("status", "CANCELED");
        content.put("payment", "n/a");
        content.put("total", sale.getTotal());
        content.put("orders", sale.getOrders());
        content.put("start_time", sale.getStartTime());
        content.put("end_time", endTime);
		database.update(DatabaseContents.TABLE_SALE.toString(), content);
		
	}

	@Override
	public void removeLineItem(int id) {
		database.delete(DatabaseContents.TABLE_SALE_LINEITEM.toString(), id);
	}



}
