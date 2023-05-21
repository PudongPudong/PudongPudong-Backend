package com.ewha.pudong.controller;

import com.ewha.pudong.domain.User;
import com.ewha.pudong.domain.UserPrincipal;
import com.ewha.pudong.dto.RecipeResponseDto;
import com.ewha.pudong.dto.RefrigeratorRequestDto;
import com.ewha.pudong.dto.RefrigeratorResponseDto;
import com.ewha.pudong.repository.UserRepository;
import com.ewha.pudong.service.RecipeService;
import com.ewha.pudong.service.RefrigeratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final RecipeService recipeService;

    private final RefrigeratorService refrigeratorService;

    @GetMapping("/recipes")
    public List<RecipeResponseDto> getRandomRecipes(){
        return recipeService.findRandomRecipe();
    }

    @GetMapping("/refrigerator")
    public RefrigeratorResponseDto getMyRefirgerator(@AuthenticationPrincipal UserPrincipal userPrincipal){
        return refrigeratorService.findMyRefrigerator(userPrincipal.getId());
    }

    @PostMapping("/refrigerator")
    public RefrigeratorResponseDto saveMyRefrigerator(@AuthenticationPrincipal UserPrincipal userPrincipal, @RequestBody RefrigeratorRequestDto refrigeratorRequestDto){
        return refrigeratorService.saveMyRefrigerator(refrigeratorRequestDto, userPrincipal.getId());
    }
}
