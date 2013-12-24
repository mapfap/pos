package com.refresh.pos.techicalservices.sale;

import java.util.Calendar;
import java.util.List;

import com.refresh.pos.domain.inventory.LineItem;
import com.refresh.pos.domain.sale.Sale;

/**
 * DAO for Sale process.
 * 
 * @author Refresh Team
 *
 */
public interface SaleDao {

	Sale initiateSale(String startTime);

	void endSale(Sale sale, String endTime);

	int addLineItem(int saleId, LineItem lineItem);
	
	List<Sale> getAllSale();

	Sale getSaleById(int id);

	void clearSaleLedger();

	List<LineItem> getLineItem(int saleId);

	void updateLineItem(int saleId, LineItem lineItem);

	List<Sale> getAllSaleDuring(Calendar start, Calendar end);
	
	void cancelSale(Sale sale,String endTime);

	void removeLineItem(int id);


}
