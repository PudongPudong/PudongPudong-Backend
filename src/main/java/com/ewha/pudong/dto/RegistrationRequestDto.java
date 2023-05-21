package com.ewha.pudong.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class RegistrationRequestDto {
    private String name;
    private List<String> allergyList;

    @Builder
    public RegistrationRequestDto(String name, List<String> allergyList){
        this.name = name;
        this.allergyList = allergyList;
    }

}
