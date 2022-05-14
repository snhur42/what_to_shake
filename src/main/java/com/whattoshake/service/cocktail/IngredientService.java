package com.whattoshake.service.cocktail;

import com.whattoshake.model.cocktail.Cocktail;
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
@Qualifier("ingredientService")
public class IngredientService {
    private IngredientRepository ingredientRepository;

    @Autowired
    public IngredientService(IngredientRepository ingredientRepository) {
        this.ingredientRepository = ingredientRepository;
    }

    public List<Ingredient> findAll() {
        return this.ingredientRepository.findAll();
    }

    public Ingredient findById(String ingredientId) {
        return this.ingredientRepository.findById(UUID.fromString(ingredientId)).get();
    }
}
