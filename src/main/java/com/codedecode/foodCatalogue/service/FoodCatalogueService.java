package com.codedecode.foodCatalogue.service;

import com.codedecode.foodCatalogue.dto.FoodCataloguePage;
import com.codedecode.foodCatalogue.dto.FoodItemDTO;
import com.codedecode.foodCatalogue.dto.Restaurant;
import com.codedecode.foodCatalogue.entity.FoodItem;
import com.codedecode.foodCatalogue.mapper.FoodItemMapper;
import com.codedecode.foodCatalogue.repo.FoodItemRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class FoodCatalogueService {

    @Autowired
    FoodItemRepo foodItemRepo;

    @Autowired
    RestTemplate restTemplate;

    public FoodItemDTO addFoodItem(FoodItemDTO foodItemDTO) {
        FoodItem foodItemSavedInDB = foodItemRepo.save(FoodItemMapper.INSTANCE.mapFootItemDTOTOFoodItem(foodItemDTO));
        return FoodItemMapper.INSTANCE.mapFootItemToFoodItemDTO(foodItemSavedInDB);
    }

    public FoodCataloguePage fetchFoodCataloguePageDetails(Integer restaurantId) {
        // Food item list
        List<FoodItem> foodItemList = fetchFoodItemList(restaurantId);
        // Restaurant details
        Restaurant restaurant = fetchRestaurantDetail(restaurantId);
        //After fetching these information, combine them and send to FE
        return createFoodCataloguePage(foodItemList, restaurant);
    }

    private List<FoodItem> fetchFoodItemList(Integer restaurantId) {
        // Go to DB and get list of food items
        if (foodItemRepo.findByRestaurantId(restaurantId) == null) System.out.println("NUll");
        List<FoodItem> foodItemList = foodItemRepo.findByRestaurantId(restaurantId);
        if (foodItemList.isEmpty()) {
            System.out.println("No food items found for restaurantId: " + restaurantId);
        }
        return foodItemList;
    }

    private Restaurant fetchRestaurantDetail(Integer restaurantId) {
        // Fetch detail from Restaurant Microservice => Need a rest template
        // We should not use localhost with port directly, since it might cause confuse with other microservice with same port in Eureka server
        // Therefore, we should use the name of the MS directly as the link to the MS, hit an endpoint
        // The second argument is the Response, and we have to Map it to our DTO, which is our Restaurant DTO define in /dto folder
        //  => Whatever response you get from the restaurant listing, MS will be mapped to restaurant DTO type in our application
        // Eureka will go by Microservice registered name with Eureka (RESTAURANT-SERVICE) and treat it as localhost:9091, but not using port number
        return restTemplate.getForObject("http://RESTAURANT-SERVICE/restaurant/fetchById/" + restaurantId, Restaurant.class);
    }

    private FoodCataloguePage createFoodCataloguePage(List<FoodItem> foodItemList, Restaurant restaurant) {
        // Merge 2 responses as 1 food catalog page
        // Create a combine object contain Food Item list and Restaurant details
        FoodCataloguePage foodCataloguePage = new FoodCataloguePage();
        foodCataloguePage.setFoodItemsList(foodItemList);
        foodCataloguePage.setRestaurant(restaurant);
        return foodCataloguePage;
    }
}
