package com.ewha.pudong.service;

import com.ewha.pudong.domain.*;
import com.ewha.pudong.dto.HealthRequestDto;
import com.ewha.pudong.dto.HealthResponseDto;
import com.ewha.pudong.repository.HealthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Component
@Slf4j
public class HealthService {
    private final HealthRepository healthRepository;

    // 건강 전체 조회
    @Transactional(readOnly = true)
    public List<HealthResponseDto> findHealthList(Long user_id){
        return healthRepository.findAllByUserId(user_id).stream()
                .map(HealthResponseDto::new)
                .collect(Collectors.toList());
    }

    // 건강 개별 조회
    @Transactional(readOnly = true)
    public HealthResponseDto findHealthById(Long id) {
        Health health = healthRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Health Id를 찾을 수 없습니다.");
        });
        return new HealthResponseDto(health);
    }


    // 건강 작성
    @Transactional
    public Long createHealth(HealthRequestDto healthRequestDto, User user, Pet pet, PoopColor poop_color, PoopFirmness poop_firmness, PoopNum poop_num) {
        Health health = healthRequestDto.toEntity(user, pet, poop_color, poop_firmness, poop_num);
        health.setScore(poop_color,poop_firmness,poop_num);
        health.setResult();
        return healthRepository.save(health).getId();
    }
}
