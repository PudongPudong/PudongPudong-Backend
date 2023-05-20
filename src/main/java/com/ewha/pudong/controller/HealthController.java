package com.ewha.pudong.controller;

import com.ewha.pudong.domain.*;
import com.ewha.pudong.dto.HealthRequestDto;
import com.ewha.pudong.dto.HealthResponseDto;
import com.ewha.pudong.repository.HealthRepository;
import com.ewha.pudong.repository.PetRepository;
import com.ewha.pudong.repository.UserRepository;
import com.ewha.pudong.service.HealthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

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
    // @AuthenticationPrincipal User user
    public List<HealthResponseDto> getHealthList() {
        User user = userRepository.getById(1L);
        System.out.println("user = " + user);
        return healthService.findHealthList(user);
    }

    // 건강 개별 조회
    @GetMapping("/{health_id}")
    public HealthResponseDto getHealthById(@PathVariable Long health_id){
        return healthService.findHealthById(health_id);
    }

    // 건강 작성
    @PostMapping()
    public HealthResponseDto createHealth(@RequestBody HealthRequestDto healthRequestDto) {
        User user = userRepository.getById(1L);
        Pet pet = petRepository.getReferenceById(1L);
        Long id = healthService.createHealth(healthRequestDto, user, pet);
        Health health = healthRepository.getById(id);
        return new HealthResponseDto(health);
    }
}
