package com.kitchen_story.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kitchen_story.entity.FoodItem;
import com.kitchen_story.repository.FoodItemRepository;

@Service
public class FoodItemServiceImp implements FoodItemService {

	@Autowired
	private FoodItemRepository foodItemRepository;

	@Override
	public FoodItem addFoodItem(FoodItem foodItem) {
		return foodItemRepository.save(foodItem);
	}

	@Override
	public List<FoodItem> getFoodItems() {
		return foodItemRepository.findAll();
	}

	@Override
	public List<FoodItem> getFoodItemByName(String inputText) {
		return foodItemRepository.findByFoodName(inputText);
	}

	@Override
	public void removeProduct(Long id) {
		 foodItemRepository.deleteById(id);;
	}

	@Override
	public String updateProduct(FoodItem foodItem, Long id) {
		FoodItem getFoodItem = foodItemRepository.findById(id).get();
		
		//if request for update is not null; then only update else, skip it.
		if(foodItem.getFoodName()!= null) {
			getFoodItem.setFoodName(foodItem.getFoodName());
		}
		if(foodItem.getPrice()!=null) {
			getFoodItem.setPrice(foodItem.getPrice());
		}
		if(foodItem.getImgSource()!=null) {
			getFoodItem.setImgSource(foodItem.getImgSource());
		}
		//after updating value; save the product
		foodItemRepository.save(getFoodItem);
		return "Product Updated";
	}

}
