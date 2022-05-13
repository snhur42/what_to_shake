package com.whattoshake.model.cocktail;

import com.whattoshake.model.AbstractEntity;
import com.whattoshake.model.enums.WeightType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Ingredient")
@Table(name = "ingredient")
public class Ingredient extends AbstractEntity {
    @Column(name = "name", nullable = false, columnDefinition = "TEXT")
    private String name;
    @Enumerated(EnumType.STRING)
    @Column(name = "weight_type", columnDefinition = "TEXT")
    private WeightType weightType;
}
