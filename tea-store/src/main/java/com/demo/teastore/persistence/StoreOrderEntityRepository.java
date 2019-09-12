package com.demo.teastore.persistence;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.teastore.entity.StoreOrderEntity;

@Repository
public interface StoreOrderEntityRepository extends JpaRepository<StoreOrderEntity, Long> {
	
	@Query("select s from StoreOrderEntity s where s.user.id = :userId")
	List<StoreOrderEntity> findOrderByUserId(@PathParam ("userId") Long userId);
}
