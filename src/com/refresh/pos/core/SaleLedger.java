package com.refresh.pos.core;

import java.util.List;

import com.refresh.pos.database.SaleLedgerDao;

public class SaleLedger {
	
	private SaleLedgerDao saleLedgerDao;

	public SaleLedger(SaleLedgerDao SaleLedgerDao) {
		this.saleLedgerDao = saleLedgerDao;
	}
	
	public List<Sale> getAllSale() {
		return saleLedgerDao.getAllSale();
	}
	
	public Sale getSaleById(int id) {
		return saleLedgerDao.getSaleById(id);
	}
}
