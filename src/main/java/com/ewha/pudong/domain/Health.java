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

    @OneToOne
    @Column
    private PoopColor poop_color;

    @OneToOne
    @Column
    private PoopFirmness poop_firmness;

    @OneToOne
    @Column
    private PoopNum poop_num;

    @Column
    private int score;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @Builder
    public Health(String treat, String check_part, PoopColor poop_color, PoopFirmness poop_firmness, PoopNum poop_num, int score, Pet pet, User user) {
        this.treat = treat;
        this.check_part = check_part;
        this.poop_color = poop_color;
        this.poop_firmness = poop_firmness;
        this.poop_num = poop_num;
        this.score = score;
        this.pet = pet;
        this.user = user;
    }
}
