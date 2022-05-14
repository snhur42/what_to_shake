package com.whattoshake.dto;

import com.whattoshake.model.enums.WeightType;

public record IngredientDTO(
        String name,
       WeightType weightType
) {
}
