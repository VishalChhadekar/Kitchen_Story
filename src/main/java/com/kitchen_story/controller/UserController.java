package com.kitchen_story.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kitchen_story.entity.Customer;
import com.kitchen_story.entity.FoodItem;
import com.kitchen_story.service.CustomerService;
import com.kitchen_story.service.FoodItemService;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/user")
public class UserController {

	@Autowired
	private FoodItemService foodItemService;
	
	@Autowired
	private CustomerService customerService;

	// GET ALL PRODUCTS
	@GetMapping("/getProducts")
	public ResponseEntity<List<FoodItem>> getFoodItems() {
		List<FoodItem> foodItems = foodItemService.getFoodItems();
		return new ResponseEntity<List<FoodItem>>(foodItems, HttpStatus.OK);
	}

	// GET PRODUCT BY NAME
	@GetMapping("/getProduct/{inputText}")
	public ResponseEntity<List<FoodItem>> getFoodItemByName(@PathVariable String inputText) {
		List<FoodItem> foodItems = foodItemService.getFoodItemByName(inputText);
		if (foodItems.isEmpty()) {
			return new ResponseEntity<List<FoodItem>>(foodItems, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<List<FoodItem>>(foodItems, HttpStatus.OK);
	}
	
	//PURCHASE
	@PostMapping("/purchase")
	public ResponseEntity<Customer> purchase(@RequestBody Customer customer){
		Customer customer2 = customerService.purchase(customer);
		return new ResponseEntity<Customer>(customer2, HttpStatus.OK);
		
	}
	
	@GetMapping("/getCustomer/{invoiceNumber}")
	public ResponseEntity<Customer> getCustomer(@PathVariable String invoiceNumber){
		Customer customer = customerService.getCustomer(invoiceNumber);
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}
	

}
