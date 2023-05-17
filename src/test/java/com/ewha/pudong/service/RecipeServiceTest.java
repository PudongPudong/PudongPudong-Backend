package com.ewha.pudong.service;

import com.ewha.pudong.repository.RecipeRepository;
import com.ewha.pudong.repository.UserRepository;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RecipeServiceTest {
    @InjectMocks
    RecipeService recipeService;

    @Mock
    RecipeRepository recipeRepository;

}