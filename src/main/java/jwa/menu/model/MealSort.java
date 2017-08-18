package jwa.menu.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tblMealSort")
public class MealSort {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String name;
	
	
	@OneToMany
	private List<Meal> meals = new ArrayList<>();
	
	public MealSort(){
		
	}
		
	public MealSort(Long id, String name) {
		this.id = id;
		this.name = name;
	}


	public MealSort(String name) {
		this.setName(name);
				
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if(name == null){
			throw new IllegalArgumentException("Name must be set.");
		}
		this.name = name;
	}

	public List<Meal> getMeals() {
		return meals;
	}

	public void setMeals(List<Meal> meals) {
		this.meals = meals;
	}
	
	
}
