package com.ewha.pudong.controller;

import com.ewha.pudong.dto.RecipeResponseDto;
import com.ewha.pudong.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/home")
@RequiredArgsConstructor
public class HomeController {

    private final RecipeService recipeService;

    @GetMapping("/recipes")
    public List<RecipeResponseDto> getRandomRecipes(){
        return recipeService.findRandomRecipe();
    }

    @GetMapping("/refrigerator")
    public getMyRefirgerator(){

    }
}
