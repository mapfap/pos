package com.refresh.pos.database;

public enum DatabaseContents {
	
	DATABASE("pos_database_02"),
	TABLE_PRODUCT_CATALOG("product_catalog"),
	TABLE_STOCK("stock"),
	TABLE_SALE("sale"),
	TABLE_SALE_LINEITEM("sale_lineitem");
	
	private String name;
	private DatabaseContents(String name) {
		this.name = name;
	}
	
	@Override 
	public String toString() {
		return name;
	}
}
