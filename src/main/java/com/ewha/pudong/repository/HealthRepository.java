package com.ewha.pudong.repository;

import com.ewha.pudong.domain.Health;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface HealthRepository extends JpaRepository<Health,Long> {
    List<Health> findAllByUserId(Long id);
}
