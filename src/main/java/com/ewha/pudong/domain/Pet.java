package com.ewha.pudong.domain;

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
public class Pet extends BaseEntity{

    @Id
    @Column(name = "pet_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    @NotNull
    private String petname;

    @Column(columnDefinition = "TEXT")
    private String image;

    @Column
    private String birth;

    @Column
    private String sex;

    @Column
    private String allergy;

    @Builder
    public Pet(User user, String petname, String image, String birth, String sex, String allergy) {
        this.user = user;
        this.petname = petname;
        this.image = image;
        this.birth = birth;
        this.sex = sex;
        this.allergy = allergy;
    }
}
