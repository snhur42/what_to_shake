package com.whattoshake.rest;

import com.whattoshake.dto.IngredientDTO;
import com.whattoshake.model.cocktail.Ingredient;
import com.whattoshake.model.user.AppUser;
import com.whattoshake.service.user.impl.AppUserServiceImpl;
import com.whattoshake.service.user.impl.ContentManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("api/content_manager")
public class ContentManagerRestController {
    private final ContentManagerService contentManagerService;

    @Autowired
    public ContentManagerRestController(
            @Qualifier("contentManagerService") ContentManagerService contentManagerService

    ) {
        this.contentManagerService = contentManagerService;
    }

//    CRUD of Ingredient

    @PostMapping("create_ingredient")
    public ResponseEntity<Boolean> createIngredient(@RequestBody IngredientDTO ingredientDTO) {
        return new ResponseEntity<>(contentManagerService.saveIngredients(ingredientDTO),
                HttpStatus.CREATED);
    }

    @GetMapping("ingredients")
    public ResponseEntity<List<Ingredient>> getAllIngredients() {
        return new ResponseEntity<>(contentManagerService.findAllIngredients(), HttpStatus.OK);
    }

    @GetMapping("ingredients/{ingredientId}")
    public ResponseEntity<Ingredient> getIngredientById(@PathVariable String ingredientId) {
        return new ResponseEntity<>(contentManagerService.findIngredientById(ingredientId), HttpStatus.OK);
    }

    @PutMapping("ingredients/{ingredientId}")
    public ResponseEntity<Ingredient> updateIngredient(@PathVariable String ingredientId, @RequestBody IngredientDTO ingredientDTO) {
        return new ResponseEntity<>(contentManagerService.updateIngredient(ingredientId, ingredientDTO), HttpStatus.OK);
    }

    @DeleteMapping("delete_ingredient/{ingredientId}")
    public void deleteIngredient(@PathVariable String ingredientId) {
        contentManagerService.deleteIngredient(ingredientId);
    }

}
