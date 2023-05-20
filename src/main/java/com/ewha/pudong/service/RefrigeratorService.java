package com.ewha.pudong.service;

import com.ewha.pudong.domain.Refrigerator;
import com.ewha.pudong.domain.User;
import com.ewha.pudong.dto.RefrigeratorRequestDto;
import com.ewha.pudong.dto.RefrigeratorResponseDto;
import com.ewha.pudong.repository.RefrigeratorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RefrigeratorService {

    private final RefrigeratorRepository refrigeratorRepository;

    public RefrigeratorResponseDto findMyRefrigerator(User user){
        Refrigerator refrigerator = refrigeratorRepository.findByUser(user);
        return new RefrigeratorResponseDto(refrigerator);
    }

    public RefrigeratorResponseDto saveMyRefrigerator(RefrigeratorRequestDto refrigeratorRequestDto, User user){
        Refrigerator refrigerator = refrigeratorRequestDto.toEntity(user);
        refrigeratorRepository.save(refrigerator);
        return new RefrigeratorResponseDto(refrigerator);
    }
}
