package com.refresh.pos.database;

import java.util.Calendar;

import android.content.ContentValues;

import com.refresh.pos.core.Sale;



public class SaleDaoAndroid implements SaleDao {

	Database database;
	public SaleDaoAndroid(Database database) {
		this.database = database;
	}
	
	public Sale initiateSale(Calendar startTime) {
		ContentValues content = new ContentValues();
        content.put("start_time", startTime.toString());
        content.put("status", "ON PROCESS");
        
        int id = database.insert(DatabaseContents.TABLE_SALE.toString(), content);
		return new Sale(id,startTime);
	}

	@Override
	public void endSale(Calendar instance) {
		//database.update();
		
	}

}
