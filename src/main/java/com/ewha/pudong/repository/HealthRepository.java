package com.ewha.pudong.repository;

import com.ewha.pudong.domain.Health;
import org.springframework.data.jpa.repository.JpaRepository;


public interface HealthRepository extends JpaRepository<Health,Long> {

}
