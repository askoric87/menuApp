package jwa.menu.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name="tblMeal")
public class Meal {

	@Id
	@GeneratedValue
	@Column
	private Long id;
	@Column
	private String name;
	@Column
	private double price;
		
	@ManyToOne
	private MealSort mealSort;
	
	public Meal(){
		
	}
	
	
	public Meal(Long id, String name, double price, MealSort mealSort) {
		this.id = id;
		this.name = name;
		this.price = price;
		this.mealSort = mealSort;
	}



	public Meal(String name, double price, MealSort mealSort) {
		this.setName(name);
		this.setPrice(price);
		this.setMealSort(mealSort);
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
		if(name == null || name.isEmpty()){
			throw new IllegalArgumentException("Name must be set.");
		}
		this.name = name;
	}

	public double getPrice() {
			return price;
	}

	public void setPrice(double price) {
		if(price < 0){
			throw new IllegalArgumentException("Price can not be negative.");
		}
		this.price = price;
	}

	public MealSort getMealSort() {
		return mealSort;
	}

	public void setMealSort(MealSort mealSort) {
		if(mealSort == null){
			throw new IllegalArgumentException("Meal sort must be set.");
		}
		this.mealSort = mealSort;
	}
	
	
	
	
}
