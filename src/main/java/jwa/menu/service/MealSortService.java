package jwa.menu.service;

import java.util.List;

import jwa.menu.model.MealSort;


public interface MealSortService {

	List<MealSort> findAll();
	MealSort save(MealSort mealSort);
	
}
