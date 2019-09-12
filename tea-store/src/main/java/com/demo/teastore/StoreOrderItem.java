package com.demo.teastore;

public class StoreOrderItem {
	private Long id;
	private Tea tea;
	private int quantity;

	public StoreOrderItem() {
	}

	public StoreOrderItem(Long id, Tea tea, int quantity) {
		this.id = id;
		this.tea = tea;
		this.quantity = quantity;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Tea getTea() {
		return tea;
	}

	public void setTea(Tea tea) {
		this.tea = tea;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "StoreOrderItem [id=" + id + ", tea=" + tea + ", quantity=" + quantity + "]";
	}
}
