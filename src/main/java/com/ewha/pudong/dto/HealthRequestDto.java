package com.ewha.pudong.dto;

import com.ewha.pudong.domain.*;
import com.ewha.pudong.repository.PoopColorRepository;
import com.ewha.pudong.repository.PoopFirmnessRepository;
import com.ewha.pudong.repository.PoopNumRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Optional;


@Data
@AllArgsConstructor
public class HealthRequestDto {

    private final PoopColorRepository poopColorRepository;
    private final PoopFirmnessRepository poopFirmnessRepository;
    private final PoopNumRepository poopNumRepository;
    private String treat;
    private String check_part;

    private Long poop_color;

    private Long poop_firmness;

    private Long poop_num;


    public Health toEntity(User user, Pet pet, PoopColor poopColor, PoopFirmness poopFirmness, PoopNum poopNum){
        return Health.builder()
                .treat(treat)
                .check_part(check_part)
                .poop_color(poopColor)
                .poop_firmness(poopFirmness)
                .poop_num(poopNum)
                .user(user)
                .pet(pet).build();
    }
}
