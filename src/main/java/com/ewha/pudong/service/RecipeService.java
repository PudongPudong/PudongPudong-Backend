package com.ewha.pudong.service;

import com.ewha.pudong.domain.Recipe;
import com.ewha.pudong.domain.User;
import com.ewha.pudong.dto.RecipeDetailResponseDto;
import com.ewha.pudong.dto.RecipeResponseDto;
import com.ewha.pudong.exception.CustomException;
import com.ewha.pudong.exception.ErrorCode;
import com.ewha.pudong.repository.RecipeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeService {
    private final RecipeRepository recipeRepository;

    public List<RecipeResponseDto> findRecipeByIngredient(List<String> ingredients){
        String keyword = String.join("%", ingredients);
        List<Recipe> recipes = recipeRepository.searchByIngredientLike(keyword);
        return recipes.stream().map(RecipeResponseDto::new)
                .collect(Collectors.toList());
    }

    public List<RecipeResponseDto> findAllRecipe(){
        List<Recipe> recipes = recipeRepository.findAll();
        return recipes.stream().map(RecipeResponseDto::new)
                .collect(Collectors.toList());
    }

    public RecipeDetailResponseDto findRecipeDetails(Long recipeId){
        return new RecipeDetailResponseDto(findRecipeEntity(recipeId));
    }

    public List<RecipeResponseDto> findRecipeByFilter(String efficacy, String type, String tool, String cookingTime){
        Specification<Recipe> spec = (root, query, criteriaBuilder) -> null;

        if(efficacy != null)
            spec = spec.and(RecipeSpecification.efficacyLike(efficacy));
        else if(type != null)
            spec = spec.and(RecipeSpecification.typeEquals(type));
        else if(tool != null)
            spec = spec.and(RecipeSpecification.toolLike(tool));
        else if(cookingTime != null)
            spec = spec.and(RecipeSpecification.cookingTimeEquals(cookingTime));

        return recipeRepository.findAll(spec).stream().map(RecipeResponseDto::new)
                .collect(Collectors.toList());
    }

    private Recipe findRecipeEntity(Long recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new CustomException(ErrorCode.RECIPE_NOT_FOUND));
    }
}
