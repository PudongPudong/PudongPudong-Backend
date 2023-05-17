package com.ewha.pudong.dto;

import com.ewha.pudong.domain.Recipe;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RecipeResponseDto {
    private Long id;
    private String name;
    private String efficacy;
    private String image;

    public RecipeResponseDto(Recipe recipe){
        this.id = recipe.getId();
        this.name = recipe.getName();
        this.efficacy = recipe.getEfficacy();
        this.image = recipe.getImage();
    }
}
