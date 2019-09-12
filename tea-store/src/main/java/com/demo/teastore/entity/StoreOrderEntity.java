package com.demo.teastore.entity;

import java.sql.Timestamp;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "store_order")
public class StoreOrderEntity {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "user_id")
	private UserEntity user;

	@Column(name = "created_at")
	private Timestamp createdAt;

	@OneToMany(mappedBy = "storeOrder", cascade = {CascadeType.ALL})
	private Set<StoreOrderItemEntity> items;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public UserEntity getUser() {
		return user;
	}

	public void setUser(UserEntity user) {
		this.user = user;
	}

	public Timestamp getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}

	public Set<StoreOrderItemEntity> getItems() {
		return items;
	}

	public void setItems(Set<StoreOrderItemEntity> items) {
		this.items = items;
	}

}
