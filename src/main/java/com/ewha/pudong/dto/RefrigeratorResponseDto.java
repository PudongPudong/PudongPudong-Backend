package com.ewha.pudong.dto;


import com.ewha.pudong.domain.Refrigerator;
import com.ewha.pudong.domain.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RefrigeratorResponseDto {

    private String ingredient;

    private Long user_id;

    private LocalDateTime createdAt;

    public RefrigeratorResponseDto(Refrigerator refrigerator) {
        this.ingredient = refrigerator.getIngredient();
        this.user_id = refrigerator.getUser().getId();
        this.createdAt = refrigerator.getCreatedAt();
    }
}
