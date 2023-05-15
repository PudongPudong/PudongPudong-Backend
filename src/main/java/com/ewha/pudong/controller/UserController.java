package com.ewha.pudong.controller;

import com.ewha.pudong.domain.UserPrincipal;
import com.ewha.pudong.dto.GeneralResponseDto;
import com.ewha.pudong.dto.RegistrationRequestDto;
import com.ewha.pudong.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/registration")
    public GeneralResponseDto register(@AuthenticationPrincipal UserPrincipal userPrincipal, RegistrationRequestDto registrationRequestDto){
        userService.registerMyPet(userPrincipal.getId(), registrationRequestDto);
        return new GeneralResponseDto("등록되었습니다.");
    }

}
