package jwa.menu.service;


import org.springframework.data.domain.Page;

import jwa.menu.model.Meal;



public interface MealService {

	Page<Meal> findAll(int page);
	Meal findOne(Long id);
	Meal save(Meal meal);
	void delete(Long id);
	
	Page<Meal> findByName(String name, int page);
	Long count();
	
}
