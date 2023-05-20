package com.ewha.pudong.repository;

import com.ewha.pudong.domain.Refrigerator;
import com.ewha.pudong.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefrigeratorRepository extends JpaRepository<Refrigerator, Long> {

    Refrigerator findByUser(User user);

}
