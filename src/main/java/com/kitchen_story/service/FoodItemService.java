package com.kitchen_story.service;

import java.util.List;



import com.kitchen_story.entity.FoodItem;

public interface FoodItemService {

	FoodItem addFoodItem(FoodItem foodItem);

	List<FoodItem> getFoodItems();

	List<FoodItem> getFoodItemByName(String inputText);

	void removeProduct(Long id);

	String updateProduct(FoodItem foodItem, Long id);

}
