package com.whattoshake.rest;

import com.whattoshake.model.cocktail.Ingredient;
import com.whattoshake.service.cocktail.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/ingredients")
public class IngredientRestController {
    private IngredientService ingredientService;

    @Autowired
    public IngredientRestController(IngredientService ingredientService) {
        this.ingredientService = ingredientService;
    }

    @GetMapping("findAll")
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return new ResponseEntity<>(ingredientService.findAll(), HttpStatus.OK);
    }

    @GetMapping("findById/{ingredientId}")
    public ResponseEntity<Ingredient> getAllCocktails(@PathVariable String ingredientId) {
        return new ResponseEntity<>(ingredientService.findById(ingredientId), HttpStatus.OK);
    }

}
