package com.whattoshake.repository.cocktail;

import com.whattoshake.model.cocktail.Ingredient;
import com.whattoshake.repository.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IngredientRepository extends EntityRepository<Ingredient> {

}
