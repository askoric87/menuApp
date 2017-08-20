package jwa.menu.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import jwa.menu.model.Meal;
import jwa.menu.service.MealService;
import jwa.menu.web.dto.MealDTO;




@RestController
@RequestMapping(value = "/api/meals")
public class ApiMealController {

	@Autowired
	private MealService mealService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MealDTO>> getAll(
			@RequestParam(value = "name", required = false) String name, 
			@RequestParam(value = "page", required = false, defaultValue = "0") int page){
		
	
		List<Meal> meals;
		
		if(name != null){
			meals = mealService.findByName(name, page).getContent();
		}else {
			meals = mealService.findAll(page).getContent();
		}
				
		return new ResponseEntity<>(MealDTO.fromMeals(meals), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<MealDTO> getOne(@PathVariable Long id){
		
		Meal meal = mealService.findOne(id);
		if(meal == null){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
				
		return new ResponseEntity<>(MealDTO.fromMeal(meal), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST, consumes="application/json")
	public ResponseEntity<MealDTO> addMeal(@RequestBody MealDTO newMealDTO){
		
		Meal meal = mealService.save(MealDTO.toMeal(newMealDTO));
		
		return new ResponseEntity<>(MealDTO.fromMeal(meal), HttpStatus.CREATED);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}", consumes="application/json")
	public ResponseEntity<MealDTO> editMeal(@PathVariable Long id, @RequestBody MealDTO meal){
		
		if(meal.getId() != id){
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		if(mealService.findOne(id) == null){
			throw new EntityNotFoundException("Meal cannot be found.");
		}
		Meal edited = mealService.save(MealDTO.toMeal(meal));
		
		return new ResponseEntity<>(MealDTO.fromMeal(edited), HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	@ResponseStatus(value = HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id){
		if(mealService.findOne(id) == null){
			throw new EntityNotFoundException("Meal cannot be found.");
		}
		mealService.delete(id);
	}
	
	
		
	
}
