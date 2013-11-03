package com.refresh.pos.database;

import java.util.List;

import com.refresh.pos.core.LineItem;
import com.refresh.pos.core.Sale;

public interface SaleLedgerDao {

	List<Sale> getAllSale();

	Sale getSaleById(int id);
	
	List<LineItem> getLineItem(Sale sale);
	

}
