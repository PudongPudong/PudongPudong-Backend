package com.ewha.pudong.controller;

import com.ewha.pudong.domain.*;
import com.ewha.pudong.dto.HealthRequestDto;
import com.ewha.pudong.dto.HealthResponseDto;
import com.ewha.pudong.repository.HealthRepository;
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

    // 유저별 건강 전체 조회
    @GetMapping()
    public List<HealthResponseDto> getHealthList(@AuthenticationPrincipal User user) {
        return healthService.findHealthList(user.getId());
    }

    // 건강 개별 조회
    @GetMapping("/{health_id}")
    public HealthResponseDto getHealthById(@PathVariable Long health_id){
        return healthService.findHealthById(health_id);
    }

    // 건강 작성
    @PostMapping()
    public HealthResponseDto createHealth(@RequestBody HealthRequestDto healthRequestDto, Pet pet, @AuthenticationPrincipal User user, PoopColor poop_color, PoopFirmness poop_firmness, PoopNum poop_num) {
        Long id = healthService.createHealth(healthRequestDto, user, pet, poop_color, poop_firmness, poop_num);
        Health health = healthRepository.getById(id);
        return new HealthResponseDto(health);
    }
}
