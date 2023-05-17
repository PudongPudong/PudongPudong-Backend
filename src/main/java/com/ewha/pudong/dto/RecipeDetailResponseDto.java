package com.ewha.pudong.dto;

import com.ewha.pudong.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@NoArgsConstructor
public class RecipeDetailResponseDto {
    private String name;
    private String efficacy;
    private String type;
    private String cookingTime;
    private String tool;
    private List<String> ingredient;
    private String cookingOrder;
    private String plusPoint;
    private String youtubeLink;

    public RecipeDetailResponseDto(Recipe recipe){
        this.name = recipe.getName();
        this.efficacy = recipe.getEfficacy();
        this.type = recipe.getType();
        this.cookingTime = recipe.getCookingTime();
        this.tool = recipe.getTool();
        this.ingredient = stringToList(recipe.getIngredient());
        this.cookingOrder = recipe.getCookingOrder();
        this.plusPoint = recipe.getPlusPoint();
        this.youtubeLink = recipe.getYoutubeLink();
    }

    private List<String> stringToList(String str){
        String[] arr = str.split("\n");
        return new ArrayList<>(Arrays.asList(arr));
    }

}
