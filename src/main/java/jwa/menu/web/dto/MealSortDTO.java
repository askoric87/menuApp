package jwa.menu.web.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import jwa.menu.model.MealSort;

public class MealSortDTO {

	private final Long id;
	private final String name;
	private final List<MealDTO> meals;
	
	@JsonCreator
	public MealSortDTO(
			@JsonProperty("id")Long id,
			@JsonProperty("name") String name,
			@JsonProperty("meals") List<MealDTO> meals) {
		
		this.id = id;
		this.name = name;
		this.meals = meals;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public List<MealDTO> getMeals() {
		return meals;
	}

	public static MealSortDTO fromMealSort(final MealSort mealSort) {
		return new MealSortDTO(mealSort.getId(), mealSort.getName(), MealDTO.fromMeals(mealSort.getMeals()));
	}
	
	public static MealSort toMealSort(final MealSortDTO mealSortDTO){
		return new MealSort(mealSortDTO.getId(), mealSortDTO.getName());
	}
	
}
