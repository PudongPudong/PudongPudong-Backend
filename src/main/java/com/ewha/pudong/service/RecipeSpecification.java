package com.ewha.pudong.service;

import com.ewha.pudong.domain.Recipe;
import org.springframework.data.jpa.domain.Specification;

public class RecipeSpecification {
    public static Specification<Recipe> efficacyLike(String efficacy) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("efficacy"),"%" + efficacy + "%");
    }

    public static Specification<Recipe> typeEquals(String type) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("type"), type);
    }

    public static Specification<Recipe> toolLike(String tool) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("tool"),"%" + tool + "%");
    }

    public static Specification<Recipe> cookingTimeEquals(String cookingTime) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.equal(root.get("cookingTime"), cookingTime);
    }

}
