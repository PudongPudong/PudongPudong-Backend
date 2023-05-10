package com.ewha.pudong.dto;

import com.ewha.pudong.domain.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthRequestDto {
    private String treat;
    private String check_part;
    private int score;


    public Health toEntity(User user, Pet pet, PoopColor poop_color, PoopFirmness poop_firmness, PoopNum poop_num){
        return Health.builder()
                .treat(treat)
                .check_part(check_part)
                .score(score)
                .poop_color(poop_color)
                .poop_firmness(poop_firmness)
                .poop_num(poop_num)
                .user(user)
                .pet(pet).build();
    }
}
