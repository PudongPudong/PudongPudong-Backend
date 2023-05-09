package com.ewha.pudong.service;

import com.ewha.pudong.domain.*;
import com.ewha.pudong.dto.HealthDto;
import com.ewha.pudong.repository.HealthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Component
@Slf4j
public class HealthService {
    private final HealthRepository healthRepository;

    // 건강 작성
    @Transactional
    public Long save(HealthDto healthDto, User user, Pet pet, PoopColor poop_color, PoopFirmness poop_firmness, PoopNum poop_num) {
        Health health = healthDto.toEntity(user, pet, poop_color, poop_firmness, poop_num);
        health.setScore(poop_color,poop_firmness,poop_num);
        health.setResult();
        return healthRepository.save(health).getId();
    }
}
