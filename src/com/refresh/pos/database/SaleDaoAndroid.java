package com.refresh.pos.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;

import com.refresh.pos.domain.LineItem;
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
        content.put("total_price", "n/a");
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
        content.put("total_price", "n/a");
        content.put("start_time", sale.getStartTime());
        content.put("end_time", endTime);
		database.update(DatabaseContents.TABLE_SALE.toString(), content);
	}
	
	@Override
	public int addLineItem(int saleId, LineItem lineItem) {
		ContentValues content = new ContentValues();
        content.put("sale_id", saleId);
        content.put("product_id", lineItem.getProductId());
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
	public Sale getSaleById(int id) {
		return getSaleBy("_id", id+"").get(0);
	}
	
	private List<Sale> getSaleBy(String reference, String value) {
        String condition = " WHERE " + reference + " = " + value + " ;";
        return getAllSale(condition);
	}
	
	public List<Sale> getAllSale(String condition) {
		String queryString = "SELECT * FROM " + DatabaseContents.TABLE_SALE.toString() + condition;
        @SuppressWarnings("unchecked")
        List<ContentValues> contents = (List) database.select(queryString);
        List<Sale> list = new ArrayList<Sale>();
        for (ContentValues content: contents) {
                list.add(new Sale(
                		content.getAsInteger("_id"),
                        content.getAsString("start_time"),
                        content.getAsString("end_time"),
                        content.getAsString("status"))
                );
        }
        return list;
	}

	@Override
	public List<LineItem> getLineItem(Sale sale) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void clearSaleLedger() {
		// TODO: don't forget to DELETE in line item 
//		database.delete(DatabaseContents.TABLE_SALE.toString(), "");
	}

}
