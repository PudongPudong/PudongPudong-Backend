package com.ewha.pudong.service;

import com.ewha.pudong.domain.Refrigerator;
import com.ewha.pudong.domain.User;
import com.ewha.pudong.dto.RefrigeratorRequestDto;
import com.ewha.pudong.dto.RefrigeratorResponseDto;
import com.ewha.pudong.exception.CustomException;
import com.ewha.pudong.exception.ErrorCode;
import com.ewha.pudong.repository.RefrigeratorRepository;
import com.ewha.pudong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RefrigeratorService {

    private final RefrigeratorRepository refrigeratorRepository;
    private final UserRepository userRepository;

    public RefrigeratorResponseDto findMyRefrigerator(Long userId){
        Refrigerator refrigerator = refrigeratorRepository.findByUser(findUserEntity(userId));
        return new RefrigeratorResponseDto(refrigerator);
    }

    public RefrigeratorResponseDto saveMyRefrigerator(RefrigeratorRequestDto refrigeratorRequestDto, Long userId){
        User user = findUserEntity(userId);
        Refrigerator myRefrigerator = refrigeratorRepository.findByUser(user);
        if (myRefrigerator==null){
            Refrigerator refrigerator = refrigeratorRequestDto.toEntity(user);
            refrigeratorRepository.save(refrigerator);
            return new RefrigeratorResponseDto(refrigerator);
        }
        else {
            myRefrigerator.update(refrigeratorRequestDto.getIngredient());
            refrigeratorRepository.save(myRefrigerator);
            return new RefrigeratorResponseDto(myRefrigerator);
        }
    }

    private User findUserEntity(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
}
