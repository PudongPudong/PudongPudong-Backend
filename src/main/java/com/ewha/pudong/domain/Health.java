package com.ewha.pudong.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="health")
public class Health extends BaseEntity{

    @Id @Column(name = "health_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String treat;

    @Column
    private String check_part;

    @Column
    private String poop_color;

    @Column
    private String color_info;

    @Column
    private String poop_firmness;

    @Column
    private String firmness_info;

    @Column
    private String poop_num;

    @Column
    private String num_info;

    @Column
    private int score;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public Health(String treat, String check_part, String poop_color, String color_info, String poop_firmness, String firmness_info, String poop_num, String num_info, int score, Pet pet, User user) {
        this.treat = treat;
        this.check_part = check_part;
        this.poop_color = poop_color;
        this.color_info = color_info;
        this.poop_firmness = poop_firmness;
        this.firmness_info = firmness_info;
        this.poop_num = poop_num;
        this.num_info = num_info;
        this.score = score;
        this.pet = pet;
        this.user = user;
    }

}
