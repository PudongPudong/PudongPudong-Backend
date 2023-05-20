package com.ewha.pudong.service;

import com.ewha.pudong.domain.Pet;
import com.ewha.pudong.domain.User;
import com.ewha.pudong.dto.GeneralResponseDto;
import com.ewha.pudong.dto.RegistrationRequestDto;
import com.ewha.pudong.exception.CustomException;
import com.ewha.pudong.exception.ErrorCode;
import com.ewha.pudong.repository.PetRepository;
import com.ewha.pudong.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PetRepository petRepository;

    @Transactional
    public void registerMyPet(Long userId, RegistrationRequestDto registrationRequestDto){
        petRepository.save(toPetEntity(userId, registrationRequestDto));
    }

    private User findUserEntity(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new CustomException(ErrorCode.USER_NOT_FOUND));
    }
    private Pet toPetEntity(Long userId, RegistrationRequestDto registrationRequestDto){
        User user = findUserEntity(userId);
        return Pet.builder()
                .name(registrationRequestDto.getName())
                .allergy(registrationRequestDto.getAllergyList().toString())
                .user(user)
                .build();
    }

}
