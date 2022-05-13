package com.whattoshake.model.cocktail;

import com.whattoshake.model.AbstractEntity;
import com.whattoshake.model.enums.CocktailSize;
import com.whattoshake.model.enums.CocktailType;
import com.whattoshake.model.enums.LevelOfCooking;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Cocktail")
@Table(name = "cocktail")
public class Cocktail extends AbstractEntity {
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;
    @Column(name = "time_for_cooking", nullable = false, columnDefinition = "TEXT")
    private String timeForCooking;
    @Column(name = "direction", nullable = false, columnDefinition = "TEXT")
    private String direction;
    @Column(name = "image_url", nullable = false, columnDefinition = "TEXT")
    private String imageUrl;
    @ElementCollection
    @CollectionTable(name="images", joinColumns=@JoinColumn(name="image_id"), foreignKey = @ForeignKey(name = "image_id_fk"))
    @Column(name="image_gallery")
    private List<String> imageGallery;
    @ElementCollection
    @CollectionTable(name="videos", joinColumns=@JoinColumn(name="video_id"), foreignKey = @ForeignKey(name = "video_id_fk"))
    @Column(name="video_gallery")
    private List<String> videoGallery;
    @Enumerated(EnumType.STRING)
    @Column(name = "level_of_cooking", columnDefinition = "TEXT")
    private LevelOfCooking levelOfCooking;
    @Enumerated(EnumType.STRING)
    @Column(name = "cocktail_size", columnDefinition = "TEXT")
    private CocktailSize cocktailSize;
    @Enumerated(EnumType.STRING)
    @Column(name = "cocktail_type", columnDefinition = "TEXT")
    private CocktailType cocktailType;
    @ElementCollection
    @CollectionTable(name="cocktail_ingredient_items", joinColumns=@JoinColumn(name="cocktail_ingredient_item_id"), foreignKey = @ForeignKey(name = "cocktail_ingredient_item_id_fk"))
    @Column(name="cocktail_ingredient_item")
    private List<CocktailIngredientItem> cocktailIngredientItems;

}
