package com.ewha.pudong.service;

import com.ewha.pudong.domain.*;
import com.ewha.pudong.dto.HealthRequestDto;
import com.ewha.pudong.repository.HealthRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Component
@Slf4j
public class HealthService {
    private final HealthRepository healthRepository;

    // 건강 전체 조회
    @Transactional(readOnly = true)
    public List<HealthRequestDto> getHealth(){
        List<Health> healths = healthRepository.findAll();
        List<HealthRequestDto> healthRequestDtos = new ArrayList<>();
        healths.forEach(s -> healthRequestDtos.add(HealthRequestDto.toEntity(s.getUser(),s.getPet(),s.getPoop_color(),s.getPoop_firmness(), s.getPoop_num())));
        return healthRequestDtos;
    }

    @Transactional(readOnly = true)
    public HealthRequestDto getBoard(Long id) {
        Health health = healthRepository.findById(id).orElseThrow(() -> {
            return new IllegalArgumentException("Board Id를 찾을 수 없습니다.");
        });
        HealthRequestDto healthRequestDto = healthRequestDto.toEntity(health.getUser(), health.getPet(), health.getPoop_color(), health.getPoop_firmness(), health.getPoop_num());
        return new HealthRequestDto(health);
    }


    // 건강 작성
    @Transactional
    public Long save(HealthRequestDto healthRequestDto, User user, Pet pet, PoopColor poop_color, PoopFirmness poop_firmness, PoopNum poop_num) {
        Health health = healthRequestDto.toEntity(user, pet, poop_color, poop_firmness, poop_num);
        health.setScore(poop_color,poop_firmness,poop_num);
        health.setResult();
        return healthRepository.save(health).getId();
    }
}
