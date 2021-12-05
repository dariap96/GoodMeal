package com.goodmeal.controllers;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RecipesRatingDTO {
    private String userLogin;
    private Long recipeId;
    private int rating;
    private String review;
}
