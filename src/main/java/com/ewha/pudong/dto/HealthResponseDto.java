package com.ewha.pudong.dto;

import com.ewha.pudong.domain.Health;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HealthResponseDto {
    private String treat;
    private int score;

    public HealthResponseDto(Health health) {
        this.treat = health.getTreat();
        this.score = health.getScore();
    }
}
