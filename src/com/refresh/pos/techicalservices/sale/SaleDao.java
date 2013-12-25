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

	/*
	 * CREATE A NEW SALE
	 */
	Sale initiateSale(String startTime);

	/*
	 * END THE CURRENT SALE
	 */
	void endSale(Sale sale, String endTime);

	/*
	 * ADD NEW LINE ITEM
	 */
	int addLineItem(int saleId, LineItem lineItem);
	
	/*
	 * GET ALL SALE IN DATABASE
	 */
	List<Sale> getAllSale();

	/*
	 *GET SALE BY ID
	 */
	Sale getSaleById(int id);

	/*
	 *CLEAR SALE HISTORY
	 */
	void clearSaleLedger();

	/*
	 *GET LINE ITEM BY ID
	 */
	List<LineItem> getLineItem(int saleId);

	/*
	 * UPDATE LINE ITEM
	 */
	void updateLineItem(int saleId, LineItem lineItem);

	/*
	 * GET SALE BY TIME
	 */
	List<Sale> getAllSaleDuring(Calendar start, Calendar end);
	
	/*
	 * CANCEL THE CURRENT SALE
	 */
	void cancelSale(Sale sale,String endTime);

	/*
	 * REMOVE LINE ITEM BY ID
	 */
	void removeLineItem(int id);


}
