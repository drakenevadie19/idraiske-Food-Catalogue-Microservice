package com.codedecode.foodCatalogue.controller;

import com.codedecode.foodCatalogue.dto.FoodCataloguePage;
import com.codedecode.foodCatalogue.dto.FoodItemDTO;
import com.codedecode.foodCatalogue.service.FoodCatalogueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/foodCatalogue")
@CrossOrigin
public class FoodItemController {

    @Autowired
    FoodCatalogueService foodCatalogueService;

    // Endpoint to add dish to DB
    @PostMapping("/addFoodItem")
    public ResponseEntity<FoodItemDTO> addFoodItem(@RequestBody FoodItemDTO foodItemDTO) {
        FoodItemDTO foodItemSaved = foodCatalogueService.addFoodItem(foodItemDTO);
        return new ResponseEntity<>(foodItemSaved, HttpStatus.CREATED);
    }

    // Endpoint to return 2 things: food item list (menu) and restaurant details
    //  => Fetch all the item details and restaurant details when we are hitting with particular restaurantID
    @GetMapping("/fetchRestaurantAndFoodItemsById/{restaurantId}")
    public ResponseEntity<FoodCataloguePage> fetchRestaurantDetailsWithFoodMenu(@PathVariable Integer restaurantId) {
        // Return this object with List of Food item and restaurant details
        FoodCataloguePage foodCataloguePage = foodCatalogueService.fetchFoodCataloguePageDetails(restaurantId);
        return new ResponseEntity<>(foodCataloguePage, HttpStatus.OK);
    }

}
