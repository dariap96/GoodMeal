package com.goodmeal.DTOs;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UpdateRecipesDTO {
    private String query;
    private String meal;
    private String dish;
    private String cuisine;
}
