package com.refresh.pos;



public class InventoryController {
	private ItemFactory itemFactory;
	private Inventory inventory;
	
	public InventoryController(Database database) {
		inventory = new Inventory(database);
		itemFactory = ItemFactory.getInstance();
	}
	
	public boolean add(){
		Item item = itemFactory.createItem("Apple", "885100422323222", 10.50);
    	boolean success = inventory.addNewItem(item);
    	return success;
	}
	public long getSize(){
		return inventory.getSize();
	}
}
