package com.refresh.pos.domain.sale;

/**
 * Sale that loads only total and orders.
 * It's for overview report that doesn't need LineItem's information.
 * NOTE: This Sale instance throws NullPointerException
 * when calling method that involve with LineItem.
 * 
 * @author Refresh Team
 *
 */
public class QuickLoadSale extends Sale {
	
	private Double total;
	private Integer orders;
	
	/**
	 * 
	 * @param id ID of this sale.
	 * @param startTime
	 * @param endTime
	 * @param status
	 * @param total
	 * @param orders numbers of lineItem in this Sale.
	 */
	public QuickLoadSale(int id, String startTime, String endTime, String status, Double total, Integer orders) {
		super(id, startTime, endTime, status, null);
		this.total = total;
		this.orders = orders;
	}
	
	@Override
	public int getOrders() {
		return orders;
	}
	
	@Override
	public double getTotal() {
		return total;
	}

}
