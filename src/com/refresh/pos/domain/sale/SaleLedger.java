package com.refresh.pos.domain.sale;

import java.util.Calendar;
import java.util.List;

import com.refresh.pos.techicalservices.NoDaoSetException;
import com.refresh.pos.techicalservices.sale.SaleDao;

public class SaleLedger {
	
	private static SaleLedger instance = null;
	private static SaleDao saleDao = null;
	
	private SaleLedger() throws NoDaoSetException {
		if (!isDaoSet()) {
			throw new NoDaoSetException();
		}
	}
	
	public static boolean isDaoSet() {
		return saleDao != null;
	}
	
	public static SaleLedger getInstance() throws NoDaoSetException {
		if (instance == null) instance = new SaleLedger();
		return instance;
	}

	public static void setSaleDao(SaleDao dao) {
		saleDao = dao;	
	}
	
	public List<Sale> getAllSale() {
		return saleDao.getAllSale();
	}
	
	public Sale getSaleById(int id) {
		return saleDao.getSaleById(id);
	}

	public void clearSaleLedger() {
		saleDao.clearSaleLedger();
	}

	public List<Sale> getAllSaleDuring(Calendar start,
			Calendar end) {
		return saleDao.getAllSaleDuring(start, end);
	}
}
