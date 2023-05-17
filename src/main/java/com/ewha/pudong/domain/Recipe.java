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
@Table(name="recipe")
public class Recipe {
    @Id
    @Column(name = "recipe_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull
    private String name;

    @Column(columnDefinition = "TEXT")
    @NotNull
    private String ingredient;

    @Column
    private String efficacy;

    @Column
    private String type;

    @Column
    private String cookingTime;

    @Column
    private String tool;

    @Column(columnDefinition = "TEXT")
    private String cookingOrder;

    @Column(columnDefinition = "TEXT")
    private String tip;

    @Column(columnDefinition = "TEXT")
    private String plusPoint;

    @Column(columnDefinition = "TEXT")
    private String youtubeLink;

    @Column(columnDefinition = "TEXT")
    private String image;

    @Builder
    public Recipe(String name, String ingredient, String efficacy, String cookingTime, String tool, String cookingOrder,
                  String tip, String plusPoint, String youtubeLink, String image){
        this.name = name;
        this.ingredient = ingredient;
        this.efficacy = efficacy;
        this.cookingTime = cookingTime;
        this.tool = tool;
        this.cookingOrder = cookingOrder;
        this.tip = tip;
        this.plusPoint = plusPoint;
        this.youtubeLink = youtubeLink;
        this.image = image;
    }
}
