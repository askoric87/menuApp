package jwa.menu.web.dto;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jwa.menu.model.Meal;

public class MealDTO {

	private final Long id;
	private final String name;
	private final double price;
	private final MealSortDTO mealSort;
	
	@JsonCreator
	public MealDTO(
			@JsonProperty("id")Long id,
			@JsonProperty("name")String name,
			@JsonProperty("price") double price,
			@JsonProperty("mealSort")MealSortDTO mealSort) {
		
		this.id = id;
		this.name = name;
		this.price = price;
		this.mealSort = mealSort;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public MealSortDTO getMealSort() {
		return mealSort;
	}
	
	public double getPrice(){
		return price;
	}

	public static MealDTO fromMeal(final Meal meal){
		return new MealDTO(meal.getId(), meal.getName(), meal.getPrice(), MealSortDTO.fromMealSort(meal.getMealSort()));
	}
	
	public static List<MealDTO> fromMeals(final List<Meal> meals) {
		final List<MealDTO> result = new ArrayList<>();
		for (Meal meal : meals) {
			result.add(MealDTO.fromMeal(meal));
		}
		
		return result;
	}
	
	public static Meal toMeal(final MealDTO mealDTO){
		return new Meal(mealDTO.getId(), mealDTO.getName(), mealDTO.getPrice(),MealSortDTO.toMealSort(mealDTO.getMealSort()));
	}
	
	
}
