package com.refresh.pos.domain;

import com.refresh.pos.database.NoDaoSetException;
import com.refresh.pos.database.SaleDao;

public class Register {
	private static Register instance = null;
	private static SaleDao saleDao = null;
	
	private Sale currentSale;
	
	private Register() throws NoDaoSetException {
		if (!isDaoSet()) {
			throw new NoDaoSetException();
		}
	}
	
	public static boolean isDaoSet() {
		return saleDao != null;
	}
	
	public static Register getInstance() throws NoDaoSetException {
		if (instance == null) instance = new Register();
		return instance;
	}

	public static void setSaleDao(SaleDao dao) {
		saleDao = dao;	
	}
	
	public Sale initiateSale(String startTime) {
		if (currentSale != null) {
			return currentSale;
		}
		currentSale = saleDao.initiateSale(startTime);
		return currentSale;
	}
	
	public int addItem(Product product, int quantity) {
		if (quantity <= 0 || currentSale == null)
			return -1;
		LineItem lineItem = currentSale.addLineItem(product, quantity);
		return saleDao.addLineItem(currentSale.getId(), lineItem);
	}
	
	public double getTotal() {
		if (currentSale == null) return 0;
		return currentSale.getTotal();
	}

	public void endSale(String endTime) {
		double total = currentSale.getTotal();
		saleDao.endSale(currentSale, endTime);
		currentSale = null;
	}
	public void clear(){
		
	}
	
}