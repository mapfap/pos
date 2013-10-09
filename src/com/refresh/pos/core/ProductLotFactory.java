package com.refresh.pos.core;


public class ProductLotFactory {
	
	private static ProductLotFactory instance;
	private ProductLotFactory() {
		
	}
	
	public static ProductLotFactory getInstance() {
		if (instance == null) {
			instance = new ProductLotFactory();
		}
		return instance;
	}
	
	public ProductLot createProductLot(int id, String dateAdded, double amount, int productId, double cost) {
		return new ProductLot(id,dateAdded,amount,productId,cost);
		
	}
	
}
