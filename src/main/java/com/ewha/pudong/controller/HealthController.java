package com.ewha.pudong.controller;

import com.ewha.pudong.domain.*;
import com.ewha.pudong.dto.HealthRequestDto;
import com.ewha.pudong.dto.HealthDetailResponseDto;
import com.ewha.pudong.dto.HealthResponseDto;
import com.ewha.pudong.repository.HealthRepository;
import com.ewha.pudong.repository.PetRepository;
import com.ewha.pudong.repository.UserRepository;
import com.ewha.pudong.service.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/health")
public class HealthController {
    private final HealthService healthService;

    private final UserRepository userRepository;
    private final HealthRepository healthRepository;

    private final PetRepository petRepository;

    // 유저별 건강 전체 조회
    @GetMapping()
    public List<HealthResponseDto> getHealthList(@AuthenticationPrincipal UserPrincipal userPrincipal) {
        return healthService.findHealthList(userPrincipal.getId());
    }

    // 건강 개별 조회
    @GetMapping("/{health_id}")
    public HealthDetailResponseDto getHealthById(@PathVariable Long health_id){
        return healthService.findHealthById(health_id);
    }

    // 점수가 높은 세개 건강 조회
    @GetMapping("/top3")
    public List<HealthResponseDto> getTop3Health(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return healthService.findTop3Health(userPrincipal.getId());
    }

    // 건강 작성
    @PostMapping()
    public HealthDetailResponseDto createHealth(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody HealthRequestDto healthRequestDto) {
        Long id = healthService.createHealth(healthRequestDto, userPrincipal.getId());
        Health health = healthRepository.getReferenceById(id);
        return new HealthDetailResponseDto(health);
    }
}
