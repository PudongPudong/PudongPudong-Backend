package com.ewha.pudong.controller;

import com.ewha.pudong.domain.User;
import com.ewha.pudong.dto.RecipeDetailResponseDto;
import com.ewha.pudong.dto.RecipeResponseDto;
import com.ewha.pudong.repository.UserRepository;
import com.ewha.pudong.service.RecipeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    private final UserRepository userRepository;

    @GetMapping("refrigerator")
    // @AuthenticationPrincipal User user
    public List<RecipeResponseDto> makeRecipeByIngredient(@RequestParam List<String> ingredients){
        User user = userRepository.getReferenceById(1L);
        return recipeService.findRecipeByIngredient(ingredients, user);
    }

    @GetMapping("/{recipeId}")
    public RecipeDetailResponseDto recipeDetails(@PathVariable Long recipeId){
        return recipeService.findRecipeDetails(recipeId);
    }

    @GetMapping("")
    public List<RecipeResponseDto> recipeList(){
        return recipeService.findAllRecipe();
    }

    @GetMapping("filter")
    public List<RecipeResponseDto> makeRecipeByFilter(@RequestParam(value="efficacy", required=false) String efficacy,
                                                      @RequestParam(value="type", required = false) String type,
                                                      @RequestParam(value="tool", required = false) String tool,
                                                      @RequestParam(value="cookingTime", required = false) String cookingTime){
        return recipeService.findRecipeByFilter(efficacy, type, tool, cookingTime);
    }

}
