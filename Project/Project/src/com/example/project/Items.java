package com.example.project;


public class Items{
	private String itemId = null;
	private String orderId = null;
	private String qty = null;
	private String amount = null;
	private String tableId = null;
	private String itemName = null;
	private String status = null;
	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getItemName() {
		return itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public Items(){
		
	}
	
	/*
	 * String itemId, String orderId, String qty, String amount,
			String tableId,String itemName,String status
	 */
	public Items(String itemId, String orderId, String qty, String amount,
			String tableId,String itemName,String status) {
		super();
		this.itemId = itemId;
		this.orderId = orderId;
		this.qty = qty;
		this.amount = amount;
		this.tableId = tableId;
		this.itemName = itemName;
		this.status = status;
	}

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public String getQty() {
		return qty;
	}

	public void setQty(String qty) {
		this.qty = qty;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTableId() {
		return tableId;
	}

	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
}
