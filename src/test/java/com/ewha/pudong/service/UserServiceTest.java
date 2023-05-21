package com.ewha.pudong.service;

import com.ewha.pudong.config.Role;
import com.ewha.pudong.domain.Pet;
import com.ewha.pudong.domain.User;
import com.ewha.pudong.dto.RegistrationRequestDto;
import com.ewha.pudong.repository.PetRepository;
import com.ewha.pudong.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService userService;

    @Mock
    UserRepository userRepository;
    @Mock
    PetRepository petRepository;

    User user;
    Pet pet;
    List<String> allergyList;

    @BeforeEach
    void setup () {
        user = User.builder().username("testuser")
                .email("testuser@gmail.com")
                .role(Role.USER)
                .build();

        allergyList = new ArrayList<>();
        allergyList.add("allergy1");
        allergyList.add("allergy2");
        allergyList.add("allergy3");

        pet = Pet.builder()
                .name("testpet")
                .owner(user)
                .allergy(allergyList.toString())
                .build();

    }

    @Test
    void 마이펫_등록하기(){
        //given
        RegistrationRequestDto request = RegistrationRequestDto.builder()
                .name("testpet")
                .allergyList(allergyList).build();

        //moking
        when(userRepository.findById(any())).thenReturn(Optional.ofNullable(user));
        when(petRepository.save(any(Pet.class))).thenReturn(pet);

        //when
        userService.registerMyPet(1L, request);

        //then
        assertThat(pet.getName()).isEqualTo("testpet");
    }

}