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

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
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
        if(type != null)
            spec = spec.and(RecipeSpecification.typeLike(type));
        if(tool != null)
            spec = spec.and(RecipeSpecification.toolLike(tool));
        if(cookingTime != null) {
            Integer min = convertToMin(cookingTime);
            spec = spec.and(RecipeSpecification.cookingTimeLess(min));
        }

        return recipeRepository.findAll(spec).stream().map(RecipeResponseDto::new)
                .collect(Collectors.toList());
    }

    private Integer convertToMin(String cookingTime){
        String time = cookingTime.replaceAll("[^0-9]", "");
        Integer min = Integer.parseInt(time);

        return min;
    }

    private Recipe findRecipeEntity(Long recipeId) {
        return recipeRepository.findById(recipeId)
                .orElseThrow(() -> new CustomException(ErrorCode.RECIPE_NOT_FOUND));
    }

    public List<RecipeResponseDto> findRandomRecipe(){
        List<Recipe> recipes = recipeRepository.findAll();
        Random random = new Random();

        Long first = Long.valueOf(random.nextInt(recipes.size()));
        Long second = Long.valueOf(random.nextInt(recipes.size()));
        Long third = Long.valueOf(random.nextInt(recipes.size()));
        List<Recipe> randomRecipes = new ArrayList<Recipe>();
        randomRecipes.add(recipeRepository.getById(first));
        randomRecipes.add(recipeRepository.getById(second));
        randomRecipes.add(recipeRepository.getById(third));

        return randomRecipes.stream().map(RecipeResponseDto::new)
                .collect(Collectors.toList());

    }
}
