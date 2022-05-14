package com.whattoshake.service.user.impl;

import com.whattoshake.dto.IngredientDTO;
import com.whattoshake.model.cocktail.Ingredient;
import com.whattoshake.repository.cocktail.CocktailRepository;
import com.whattoshake.repository.cocktail.IngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;


@Service
@Transactional
@Qualifier("contentManagerService")
public class ContentManagerService {
    private IngredientRepository  ingredientRepository;
    private CocktailRepository cocktailRepository;

    @Autowired
    public ContentManagerService(IngredientRepository ingredientRepository, CocktailRepository cocktailRepository) {
        this.ingredientRepository = ingredientRepository;
        this.cocktailRepository = cocktailRepository;
    }

    public boolean saveIngredients(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient(ingredientDTO.name(), ingredientDTO.weightType());
        this.ingredientRepository.save(ingredient);
        return true;
    }

    public List<Ingredient> findAllIngredients() {
        return this.ingredientRepository.findAll();
    }

    public Ingredient findIngredientById(String ingredientId) {
        return this.ingredientRepository.findById(UUID.fromString(ingredientId)).get();
    }

    public Ingredient updateIngredient(String ingredientId, IngredientDTO ingredientDTO) {
        Ingredient ingredient = this.ingredientRepository.findById(UUID.fromString(ingredientId)).get();
        ingredient.setName(ingredientDTO.name());
        ingredient.setWeightType(ingredientDTO.weightType());
        return this.ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(String ingredientId) {
        this.ingredientRepository.deleteById(UUID.fromString(ingredientId));
    }
}

