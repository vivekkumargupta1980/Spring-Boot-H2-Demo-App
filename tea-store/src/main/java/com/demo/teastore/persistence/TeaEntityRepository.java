package com.demo.teastore.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.teastore.entity.TeaEntity;

@Repository
public interface TeaEntityRepository  extends JpaRepository<TeaEntity, Long> {

}
