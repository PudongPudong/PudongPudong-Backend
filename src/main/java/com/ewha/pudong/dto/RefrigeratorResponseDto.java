package com.ewha.pudong.dto;


import com.ewha.pudong.domain.Refrigerator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RefrigeratorResponseDto {

    private String ingredient;

    private LocalDateTime createdAt;

    public RefrigeratorResponseDto(Refrigerator refrigerator) {
        this.ingredient = refrigerator.getIngredient();
        this.createdAt = refrigerator.getCreatedAt();
    }
}
