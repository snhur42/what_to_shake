package com.whattoshake.rest;

import com.whattoshake.model.cocktail.Cocktail;
import com.whattoshake.service.cocktail.CocktailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/cocktails")
public class CocktailRestController {
    private CocktailService cocktailService;

    @Autowired
    public CocktailRestController(CocktailService cocktailService) {
        this.cocktailService = cocktailService;
    }

    @GetMapping("findAll")
    public ResponseEntity<List<Cocktail>> getAllCocktails() {
        return new ResponseEntity<>(cocktailService.findAll(), HttpStatus.OK);
    }

    @GetMapping("findById/{cocktailId}")
    public ResponseEntity<Cocktail> getAllCocktails(@PathVariable String cocktailId) {
        return new ResponseEntity<>(cocktailService.findById(cocktailId), HttpStatus.OK);
    }


}
