package com.ewha.pudong.dto;

import com.ewha.pudong.domain.Refrigerator;
import com.ewha.pudong.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class RefrigeratorRequestDto {

    private String ingredient;

    public Refrigerator toEntity(User user){
        return Refrigerator.builder()
                .ingredient(ingredient)
                .user(user).build();
    }


}
