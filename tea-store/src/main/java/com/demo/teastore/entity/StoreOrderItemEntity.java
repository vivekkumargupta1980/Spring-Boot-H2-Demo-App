package com.demo.teastore.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "store_order_item")
public class StoreOrderItemEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "store_order_id")
	private StoreOrderEntity storeOrder;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "tea_id")
	private TeaEntity tea;

	@Column(name = "quantity")
	private int quantity;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StoreOrderEntity getStoreOrder() {
		return storeOrder;
	}

	public void setStoreOrder(StoreOrderEntity storeOrder) {
		this.storeOrder = storeOrder;
	}

	public TeaEntity getTea() {
		return tea;
	}

	public void setTea(TeaEntity tea) {
		this.tea = tea;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
