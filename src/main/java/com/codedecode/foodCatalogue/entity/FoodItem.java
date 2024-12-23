package com.codedecode.foodCatalogue.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String itemName;

    private String itemDescription;

    private boolean isVeg; // Specify whether this food is for vegan?

    private BigDecimal price;

    private Integer restaurantId;

    // Initial value is 0, since you get into the restaurant, # of item selected is 0, then you scroll down and add food to your cart
    @Column(nullable = false, columnDefinition = "INT DEFAULT 0")
    private Integer quantity = 0; // How much quantity of that food item you are selecting to add it in your cart?

}
