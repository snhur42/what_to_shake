package com.whattoshake.service.cocktail;

import com.whattoshake.model.cocktail.Cocktail;
import com.whattoshake.repository.cocktail.CocktailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
@Qualifier("cocktailService")
public class CocktailService {
    private CocktailRepository cocktailRepository;

    @Autowired
    public CocktailService(CocktailRepository cocktailRepository) {
        this.cocktailRepository = cocktailRepository;
    }

    public List<Cocktail> findAll() {
        return this.cocktailRepository.findAll();
    }

    public Cocktail findById(String cocktailId) {
        return this.cocktailRepository.findById(UUID.fromString(cocktailId)).get();
    }
}
