package com.ewha.pudong.repository;

import com.ewha.pudong.domain.Health;
import com.ewha.pudong.domain.User;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface HealthRepository extends JpaRepository<Health,Long> {

    List<Health> findAllByUserId(Long id);

    List<Health> findByUserIdOrderByScoreDesc(Long id);
}
