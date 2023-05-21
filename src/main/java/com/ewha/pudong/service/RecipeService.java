package com.ewha.pudong.service;

import com.ewha.pudong.domain.Recipe;
import com.ewha.pudong.domain.Refrigerator;
import com.ewha.pudong.domain.User;
import com.ewha.pudong.dto.RecipeDetailResponseDto;
import com.ewha.pudong.dto.RecipeResponseDto;
import com.ewha.pudong.dto.RefrigeratorResponseDto;
import com.ewha.pudong.exception.CustomException;
import com.ewha.pudong.exception.ErrorCode;
import com.ewha.pudong.repository.RecipeRepository;
import com.ewha.pudong.repository.RefrigeratorRepository;
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
    private final RefrigeratorRepository refrigeratorRepository;

    public List<RecipeResponseDto> findRecipeByIngredient(List<String> ingredients, User user){

        // 유저마다 냉장고 저장, 유저에 해당하는 냉장고가 이미 db에 있다면 업데이트
        Refrigerator myRefrigerator = refrigeratorRepository.findByUser(user);
        if (myRefrigerator==null){
             String ingredient = ingredients.toString();
            Refrigerator refrigerator = new Refrigerator(ingredient, user);
            refrigeratorRepository.save(refrigerator);
        }
        else {
            myRefrigerator.update(ingredients.toString());
            refrigeratorRepository.save(myRefrigerator);
        }

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
