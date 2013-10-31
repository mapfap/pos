package com.refresh.pos.core;

import java.util.Calendar;

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
	
	public void initiateSale() {
		if (currentSale != null) {
			// end the sell and create new one
		}
		currentSale = saleDao.initiateSale(Calendar.getInstance());
		// add observers
//		for(Observer view: saleobservers ) {
//			sale.addObserver(view);
//			if (view instanceof POSUI) ((POSUI)view).setSale(sale);
//		}
	}
	

	public boolean addItem(Product product, int quantity) {
		if (quantity <= 0)
			return false;
		if (currentSale == null)
			initiateSale();
		currentSale.addLineItem(product, quantity);
		return true;
	}
	
	public double getTotal() {
		if (currentSale == null) return 0;
		return currentSale.getTotal();
		
	}

	public void endSale() {
		double total = currentSale.getTotal();
		saleDao.endSale(Calendar.getInstance());
//		currentSale.deleteObservers();
		this.currentSale = null;
	}
	
	
//	public void addSaleObserver(Observer obs) {
//		if ( !saleobservers.contains(obs) ) {
//			saleobservers.add(obs);
//			if (sale != null) sale.addObserver(obs);
////			view.setSale(sale);
//		}
//	}

}