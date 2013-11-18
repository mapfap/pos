package com.refresh.pos.database;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;

import com.refresh.pos.domain.LineItem;
import com.refresh.pos.domain.Sale;

public class SaleLedgerDaoAndroid implements SaleLedgerDao {
	
	Database database;
	public SaleLedgerDaoAndroid(Database database) {
		this.database = database;
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
	
	

}
