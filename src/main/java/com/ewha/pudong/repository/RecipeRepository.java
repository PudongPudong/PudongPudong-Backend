package com.ewha.pudong.repository;

import com.ewha.pudong.domain.Recipe;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RecipeRepository extends JpaRepository<Recipe, Long>, JpaSpecificationExecutor<Recipe> {
    @Query("SELECT r FROM Recipe r WHERE r.ingredient LIKE %:ingredient%")
    List<Recipe> searchByIngredientLike(@Param("ingredient") String ingredient);

    List<Recipe> findAll(Specification<Recipe> spec);
}
