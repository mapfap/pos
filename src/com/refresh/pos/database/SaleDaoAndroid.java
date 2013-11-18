package com.refresh.pos.database;

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
        
        int id = database.insert(DatabaseContents.TABLE_SALE.toString(), content);
		return new Sale(id,startTime);
	}

	@Override
	public void endSale(Sale sale, String endTime) {
		ContentValues content = new ContentValues();
        content.put("_id", sale.getId());
        content.put("status", sale.getStatus());
        content.put("payment", sale.getPayment());
        content.put("total_price", sale.getTotal());
        content.put("start_time", sale.getStartTime());
        content.put("end_time", sale.getEndTime());
		database.update(DatabaseContents.TABLE_SALE.toString(), content);
	}
	
	@Override
	public int addLineItem(int saleId, LineItem lineItem) {
		ContentValues content = new ContentValues();
        content.put("sale_id", saleId);
        content.put("product_id", lineItem.getProductId());
        content.put("quantity", lineItem.getQuantity());
        content.put("sale_price", lineItem.getTotal());
        int id = database.insert(DatabaseContents.TABLE_SALE_LINEITEM.toString(), content);
        return id;
	}


}
