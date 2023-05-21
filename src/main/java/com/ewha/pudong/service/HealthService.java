package com.ewha.pudong.service;

import com.ewha.pudong.domain.*;
import com.ewha.pudong.dto.HealthRequestDto;
import com.ewha.pudong.dto.HealthDetailResponseDto;
import com.ewha.pudong.dto.HealthResponseDto;
import com.ewha.pudong.repository.HealthRepository;
import com.ewha.pudong.repository.PoopColorRepository;
import com.ewha.pudong.repository.PoopFirmnessRepository;
import com.ewha.pudong.repository.PoopNumRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Component
@Slf4j
public class HealthService {
    private final HealthRepository healthRepository;
    private final PoopColorRepository poopColorRepository;
    private final PoopFirmnessRepository poopFirmnessRepository;
    private final PoopNumRepository poopNumRepository;

    // 건강 전체 조회
    @Transactional(readOnly = true)
    public List<HealthResponseDto> findHealthList(User user){
        return healthRepository.findAllByUserId(user.getId()).stream()
                .map(HealthResponseDto::new)
                .collect(Collectors.toList());
    }

    // 흡수 점수가 가장 높은 건강 3개 조회

    @Transactional(readOnly = true)
    public List<HealthResponseDto> findTop3Health(User user){
        List<Health> healthList= healthRepository.findByUserIdOrderByScoreDesc(user.getId());
        List<Health> top3Health = new ArrayList<Health>(healthList.subList(0,3));
        return top3Health.stream()
                .map(HealthResponseDto::new)
                .collect(Collectors.toList());
    }

    // 건강 개별 조회
    @Transactional(readOnly = true)
    public HealthDetailResponseDto findHealthById(Long id) {
        Health health = healthRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Health Id를 찾을 수 없습니다.");
        });
        return new HealthDetailResponseDto(health);
    }


    // 건강 작성
    @Transactional
    public Long createHealth(HealthRequestDto healthRequestDto, User user, Pet pet) {
        PoopColor poopColor = poopColorRepository.findById(healthRequestDto.getPoop_color()).orElseThrow(() -> {
            return new IllegalArgumentException("poopColor Id를 찾을 수 없습니다.");
        });
        PoopFirmness poopFirmness = poopFirmnessRepository.findById(healthRequestDto.getPoop_firmness()).orElseThrow(() -> {
            return new IllegalArgumentException("poopFirmness Id를 찾을 수 없습니다.");
        });
        PoopNum poopNum = poopNumRepository.findById(healthRequestDto.getPoop_num()).orElseThrow(() -> {
            return new IllegalArgumentException("poopFirmness Id를 찾을 수 없습니다.");
        });
        Health health = healthRequestDto.toEntity(user, pet, poopColor, poopFirmness, poopNum);
        health.setScore();
        health.setResult();
        return healthRepository.save(health).getId();
    }
}
