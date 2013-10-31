package com.refresh.pos.database;

import java.util.Calendar;

import com.refresh.pos.core.Sale;

public interface SaleDao {

	Sale initiateSale(Calendar startTime);

}
