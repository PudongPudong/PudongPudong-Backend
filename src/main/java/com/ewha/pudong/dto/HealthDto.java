package com.ewha.pudong.dto;

import com.ewha.pudong.domain.Health;
import com.ewha.pudong.domain.Pet;
import com.ewha.pudong.domain.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class HealthDto {
    private String treat;
    private String check_part;
    private String poop_color;
    private String poop_firmness;
    private String poop_num;

    public Health tohealth(User user, Pet pet){
        return Health.builder()
                .treat(treat)
                .check_part(check_part)
                .poop_color(poop_color)
                .poop_firmness(poop_firmness)
                .poop_num(poop_num)
                .user(user)
                .pet(pet).build();
    }
}
