package com.demo.teastore.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.teastore.entity.UserEntity;

@Repository
public interface UserEntityRepository  extends JpaRepository<UserEntity, Long> {

}
