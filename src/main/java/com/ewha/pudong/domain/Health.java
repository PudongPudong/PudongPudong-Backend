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
    @JoinColumn(name = "poopcolor_id")
    private PoopColor poop_color;

    @OneToOne
    @JoinColumn(name = "poopfimness_id")
    private PoopFirmness poop_firmness;

    @OneToOne
    @JoinColumn(name = "poopnum_id")
    private PoopNum poop_num;

    @Column
    private int score;

    @Column
    private String result;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setScore(PoopColor poop_color, PoopFirmness poop_firmness, PoopNum poop_num) {
        this.score = poop_color.getScore()+poop_firmness.getScore()+poop_num.getScore();
    }

    public void setResult(){
        if (score>0 && score<=50){
            this.result = treat+"은 "+pet.getName()+"이에게 잘 맞지 않아요.";
        } else if (score>50 && score<=80) {
            this.result = treat+"은 "+pet.getName()+"이에게 맞아요.";
        } else if (score>80 && score<=100) {
            this.result = treat+"은 "+pet.getName()+"이에게 잘 맞아요!";
        }
    }

    @Builder
    public Health(String treat, String check_part, PoopColor poop_color, PoopFirmness poop_firmness, PoopNum poop_num, int score, String result, Pet pet, User user) {
        this.treat = treat;
        this.check_part = check_part;
        this.poop_color = poop_color;
        this.poop_firmness = poop_firmness;
        this.poop_num = poop_num;
        this.score = score;
        this.result = result;
        this.pet = pet;
        this.user = user;
    }
}
