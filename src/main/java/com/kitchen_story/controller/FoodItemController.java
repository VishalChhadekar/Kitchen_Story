package com.kitchen_story.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.kitchen_story.entity.FoodItem;
import com.kitchen_story.service.FoodItemService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class FoodItemController {
	
	@Autowired
	private FoodItemService foodItemService;
	

	//ADD PRODUCT
	@PostMapping("/addProduct")
	public ResponseEntity<FoodItem> addFoodItem(@RequestBody FoodItem foodItem){
		FoodItem food = foodItemService.addFoodItem(foodItem);
		return new ResponseEntity<FoodItem>(food, HttpStatus.OK);
	}
	
	//GET ALL PRODUCTS
	@GetMapping("/getProducts")
	public ResponseEntity<List<FoodItem>> getFoodItems() {
		List<FoodItem> foodItems =  foodItemService.getFoodItems();
		return new ResponseEntity<List<FoodItem>>(foodItems, HttpStatus.OK);
	}
	
	//GET PRODUCT BY NAME
	@GetMapping("/getProduct/{inputText}")
	public ResponseEntity<List<FoodItem>> getFoodItemByName(@PathVariable String inputText){
		List<FoodItem> foodItems = foodItemService.getFoodItemByName(inputText);
		if(foodItems.isEmpty()) {
			return new ResponseEntity<List<FoodItem>>(foodItems, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<FoodItem>>(foodItems, HttpStatus.OK);
	}
	
	//REMOVE PRODUCT
	@DeleteMapping("/deleteProduct/{id}")
	public ResponseEntity<String> removeProduct(@PathVariable Long id){
		 foodItemService.removeProduct(id);	
		 return new ResponseEntity<String>("Product Deleted", HttpStatus.OK);
	}
	
	//UPDATE PRODUCT
	@PutMapping("/updateProduct/{id}")
	public ResponseEntity<String> updateProduct(@RequestBody FoodItem foodItem, @PathVariable Long id){
		foodItemService.updateProduct(foodItem, id);
		return new ResponseEntity<String> ("Product Updated", HttpStatus.OK);	
	}
}
