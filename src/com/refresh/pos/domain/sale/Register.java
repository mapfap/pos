package com.refresh.pos.domain.sale;

import com.refresh.pos.domain.DateTimeStrategy;
import com.refresh.pos.domain.inventory.Inventory;
import com.refresh.pos.domain.inventory.LineItem;
import com.refresh.pos.domain.inventory.Product;
import com.refresh.pos.domain.inventory.Stock;
import com.refresh.pos.techicalservices.NoDaoSetException;
import com.refresh.pos.techicalservices.sale.SaleDao;

/**
 * Handles all Sale processes.
 * 
 * @author Refresh Team
 *
 */
public class Register {
	private static Register instance = null;
	private static SaleDao saleDao = null;
	private static Stock stock = null;
	
	private Sale currentSale;
	
	private Register() throws NoDaoSetException {
		if (!isDaoSet()) {
			throw new NoDaoSetException();
		}
		stock = Inventory.getInstance().getStock();
		
	}
	
	public static boolean isDaoSet() {
		return saleDao != null;
	}
	
	public static Register getInstance() throws NoDaoSetException {
		if (instance == null) instance = new Register();
		return instance;
	}

	/*
	 *SET SALE DAO
	 */
	public static void setSaleDao(SaleDao dao) {
		saleDao = dao;	
	}
	/*
	 *CRATE A NEW SALE
	 */	
	public Sale initiateSale(String startTime) {
		if (currentSale != null) {
			return currentSale;
		}
		currentSale = saleDao.initiateSale(startTime);
		return currentSale;
	}
	
	/*
	 *ADD NEW ITEM INTO SALE
	 */
	public LineItem addItem(Product product, int quantity) {
//		if (quantity <= 0 || currentSale == null)
//			return -1;
		if (currentSale == null)
			initiateSale(DateTimeStrategy.getCurrentTime());
		
		LineItem lineItem = currentSale.addLineItem(product, quantity);
		
		if (lineItem.getId() == LineItem.UNDEFINED) {
			int lineId = saleDao.addLineItem(currentSale.getId(), lineItem);
			lineItem.setId(lineId);
		} else {
			saleDao.updateLineItem(currentSale.getId(), lineItem);
		}
		
		return lineItem;
	}
	
	/*
	 *GET TOTAL PRICE OF THE SALE
	 */	
	public double getTotal() {
		if (currentSale == null) return 0;
		return currentSale.getTotal();
	}

	/*
	 *END THE SALE
	 */
	public void endSale(String endTime) {
		if (currentSale != null) {
			saleDao.endSale(currentSale, endTime);
			for(LineItem line : currentSale.getAllLineItem()){
				stock.updateStockSum(line.getProduct().getId(), line.getQuantity());
			}
			currentSale = null;
		}
	}
	
	/*
	 *GET CURRENT SALE
	 */
	public Sale getCurrentSale() {
		if (currentSale == null)
			initiateSale(DateTimeStrategy.getCurrentTime());
		return currentSale;
	}

	/*
	 *SET CURRENT SALE
	 */	
	public boolean setCurrentSale(int id) {
//		if (currentSale == null)
//			initiateSale(DateTimeStrategy.getCurrentTime());
//		return currentSale;
		
		currentSale = saleDao.getSaleById(id);
		return false;
	}

	/*
	 *Now OnSale or not
	 */	
	public boolean hasSale(){
		if(currentSale == null)return false;
		return true;
	}
	
	/*
	 *CANCLE THE CURRENT SALE
	 */	
	public void cancleSale() {
		if (currentSale != null){
			saleDao.cancelSale(currentSale,DateTimeStrategy.getCurrentTime());
			currentSale = null;
		}
	}

	/*
	 *UPDATE LINE ITEM 
	 */	
	public void updateItem(int saleId, LineItem lineItem, int quantity, double priceAtSale) {
		lineItem.setUnitPriceAtSale(priceAtSale);
		lineItem.setQuantity(quantity);
		saleDao.updateLineItem(saleId, lineItem);
	}

	/*
	 *REMOVE LINE ITEM
	 */	
	public void removeItem(LineItem lineItem) {
		saleDao.removeLineItem(lineItem.getId());
		currentSale.removeItem(lineItem);
		if (currentSale.getAllLineItem().isEmpty()) {
			cancleSale();
		}
	}
	
}