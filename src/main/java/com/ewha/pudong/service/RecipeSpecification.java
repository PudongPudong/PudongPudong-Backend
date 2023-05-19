package com.ewha.pudong.service;

import com.ewha.pudong.domain.Recipe;
import org.springframework.data.jpa.domain.Specification;

public class RecipeSpecification {
    public static Specification<Recipe> efficacyLike(String efficacy) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("efficacy"),"%" + efficacy + "%");
    }

    public static Specification<Recipe> typeLike(String type) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("type"),"%" + type + "%");
    }

    public static Specification<Recipe> toolLike(String tool) {
        return (root, query, CriteriaBuilder) -> CriteriaBuilder.like(root.get("tool"),"%" + tool + "%");
    }

    public static Specification<Recipe> cookingTimeLess(Integer cookingTime) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThanOrEqualTo(root.get("cookingTime"), cookingTime);
    }

}
