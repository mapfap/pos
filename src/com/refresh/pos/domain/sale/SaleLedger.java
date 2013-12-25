package com.refresh.pos.domain.sale;

import java.util.Calendar;
import java.util.List;

import com.refresh.pos.techicalservices.NoDaoSetException;
import com.refresh.pos.techicalservices.sale.SaleDao;

/**
 * Book that keeps sale record.
 * 
 * @author Refresh Team
 *
 */
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

	/*
	 *SET SALE DAO
	 */
	public static void setSaleDao(SaleDao dao) {
		saleDao = dao;	
	}
	
	/*
	 *GET ALL SALE
	 */
	public List<Sale> getAllSale() {
		return saleDao.getAllSale();
	}
	
	/*
	 *GET SALE BY SLAE ID
	 */
	public Sale getSaleById(int id) {
		return saleDao.getSaleById(id);
	}

	/*
	 *CLEAR SALE LEDGER
	 */
	public void clearSaleLedger() {
		saleDao.clearSaleLedger();
	}

	/*
	 *GET SALE BY TIME
	 */
	public List<Sale> getAllSaleDuring(Calendar start, Calendar end) {
		return saleDao.getAllSaleDuring(start, end);
	}
}
