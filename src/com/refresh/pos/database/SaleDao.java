package com.refresh.pos.database;

import com.refresh.pos.core.LineItem;
import com.refresh.pos.core.Sale;

public interface SaleDao {

	Sale initiateSale(String startTime);

	void endSale(Sale sale, String endTime);

	int addLineItem(int saleId, LineItem lineItem);

}
