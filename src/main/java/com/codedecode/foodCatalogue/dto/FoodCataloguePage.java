package com.codedecode.foodCatalogue.dto;

import com.codedecode.foodCatalogue.entity.FoodItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FoodCataloguePage {

    // This page is the one who will be responsible for showing all the food items, list and restaurant details
    // We need 2 things for this page:
    //  - List of food items (Menu)
    private List<FoodItem> foodItemsList;
    //  - Restaurant details
    private Restaurant restaurant;
    // => When you click on a particular restaurant, you will see all these 2 details on your page

}
