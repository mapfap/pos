package com.refresh.pos.core;

import com.refresh.pos.database.NoDaoSetException;
import com.refresh.pos.database.SaleDao;

public class Sale {
	private static Sale instance = null;
	private static SaleDao saleDao = null;
	
	private Sale() throws NoDaoSetException {
		if (!isDaoSet()) {
			throw new NoDaoSetException();
		}
	}
	
	public static boolean isDaoSet() {
		return saleDao != null;
	}
	
	public static Sale getInstance() throws NoDaoSetException {
		if (instance == null) instance = new Sale();
		return instance;
	}

	public static void setSaleDao(SaleDao dao) {
		saleDao = dao;	
	}
}