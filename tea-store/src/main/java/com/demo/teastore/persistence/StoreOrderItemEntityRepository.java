package com.demo.teastore.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.teastore.entity.StoreOrderItemEntity;

@Repository
public interface StoreOrderItemEntityRepository extends JpaRepository<StoreOrderItemEntity, Long> {

}
