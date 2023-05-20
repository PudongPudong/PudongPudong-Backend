package com.ewha.pudong.controller;

import com.ewha.pudong.domain.User;
import com.ewha.pudong.dto.RecipeResponseDto;
import com.ewha.pudong.dto.RefrigeratorRequestDto;
import com.ewha.pudong.dto.RefrigeratorResponseDto;
import com.ewha.pudong.repository.UserRepository;
import com.ewha.pudong.service.RecipeService;
import com.ewha.pudong.service.RefrigeratorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final RecipeService recipeService;

    private final RefrigeratorService refrigeratorService;

    private final UserRepository userRepository;

    @GetMapping("/recipes")
    public List<RecipeResponseDto> getRandomRecipes(){
        return recipeService.findRandomRecipe();
    }

    @GetMapping("/refrigerator")
    // @AuthenticationPrincipal User user
    public RefrigeratorResponseDto getMyRefirgerator(){
        User user = userRepository.getReferenceById(1L);
        return refrigeratorService.findMyRefrigerator(user);
    }

    @PostMapping("/refrigerator")
    public RefrigeratorResponseDto saveMyRefrigerator(@RequestBody RefrigeratorRequestDto refrigeratorRequestDto){
        User user = userRepository.getReferenceById(1L);
        return refrigeratorService.saveMyRefrigerator(refrigeratorRequestDto, user);
    }
}
