package com.kitchen_story.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kitchen_story.entity.FoodItem;

@Repository
public interface FoodItemRepository extends JpaRepository<FoodItem, Long>{

	List<FoodItem> findByFoodName(String inputText);

}
