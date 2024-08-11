package com.codedecode.foodCatalogue.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Restaurant {

    // Import from Restaurant database (not save in the FoodCatalogue database)
    // Which means this is a DTO,not entity

    private int id;
    private String name;
    private String address;
    private String city;
    private String restaurantDescription;
}
