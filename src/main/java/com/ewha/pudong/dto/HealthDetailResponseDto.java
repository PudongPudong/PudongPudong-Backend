package com.ewha.pudong.dto;

import com.ewha.pudong.domain.Health;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HealthDetailResponseDto {

    private Long id;
    private String treat;
    private String check_part;
    private String result;
    private int score;
    private String poop_color;
    private String color_info;
    private String poop_firmness;
    private String firmness_info;
    private String poop_num;
    private String num_info;

    private LocalDateTime createdAt;

    public HealthDetailResponseDto(Health health){
        this.id = health.getId();
        this.result = health.getResult();
        this.score = health.getScore();
        this.poop_color = health.getPoop_color().getName();
        this.color_info = health.getPoop_color().getInfo();
        this.poop_firmness = health.getPoop_firmness().getName();
        this.firmness_info = health.getPoop_firmness().getInfo();
        this.poop_num = health.getPoop_num().getName();
        this.num_info = health.getPoop_num().getInfo();
        this.createdAt = health.getCreatedAt();
        this.treat = health.getTreat();
        this.check_part = health.getCheck_part();
    }
}
