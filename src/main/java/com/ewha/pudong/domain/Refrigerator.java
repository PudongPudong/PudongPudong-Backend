package com.ewha.pudong.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name="refrigerator")
public class Refrigerator extends BaseEntity{
    @Id
    @Column(name = "refrigerator_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String ingredient;

    @OneToOne
    private User user;

    @Builder
    public Refrigerator(String ingredient, User user) {
        this.ingredient = ingredient;
        this.user = user;
    }
}
