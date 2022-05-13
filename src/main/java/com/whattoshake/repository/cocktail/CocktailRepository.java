package com.whattoshake.repository.cocktail;

import com.whattoshake.model.cocktail.Cocktail;
import com.whattoshake.repository.EntityRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CocktailRepository extends EntityRepository<Cocktail> {

}
