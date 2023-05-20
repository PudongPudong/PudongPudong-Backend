package com.ewha.pudong.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="pet")
public class Pet {
    @Id
    @Column(name = "pet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @NotNull
    @JoinColumn(name = "user_id")
    private User owner;

    @NotNull
    private String name;

    @Column(columnDefinition = "TEXT")
    private String image;

    @Column
    private String sex;

    @Column
    private String birthday;

    @Column
    private String variety;

    @Column
    private String weight;

    @Column
    private String allergy;

    @Builder
    public Pet(User owner, String name, String image, String sex, String birthday, String variety, String weight, String allergy){
        this.owner = owner;
        this.name = name;
        this.image = image;
        this.sex = sex;
        this.birthday = birthday;
        this.variety = variety;
        this.weight = weight;
        this.allergy = allergy;
    }

}