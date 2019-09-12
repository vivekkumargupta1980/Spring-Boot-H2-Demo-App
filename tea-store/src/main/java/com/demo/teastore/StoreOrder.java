package com.demo.teastore;

import java.sql.Timestamp;
import java.util.Set;

public class StoreOrder {
	private Long id;
	private Timestamp createdAt;
	private Set<StoreOrderItem> items;
	private User user;

	public StoreOrder() {
	}

	public StoreOrder(Long id, Timestamp createdAt, Set<StoreOrderItem> items, User user) {
		this.id = id;
		this.createdAt = createdAt;
		this.items = items;
		this.user = user;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Set<StoreOrderItem> getItems() {
		return items;
	}

	public void setItems(Set<StoreOrderItem> items) {
		this.items = items;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "StoreOrder [id=" + id + ", createdAt=" + createdAt + ", items=" + items + ", user=" + user + "]";
	}
}
