package jwa.menu.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class MealTest {

	private final MealSort mealSort = new MealSort("my meal sort");
	private final String mealName = "my meal";
	private final double price = 10.00;
	
	@Test
	public void testConstruction_success() {
		
		final Meal meal = new Meal(mealName, price, mealSort);
		
		assertEquals(mealName, meal.getName());
		assertEquals(price, meal.getPrice(), 0.001);
		assertEquals(mealSort, meal.getMealSort());
	}
	
	@Test
	public void testConstructionWithNegativePrice_fail() {
		
		final double negativePrice = -1.00;
		
		try {
			final Meal meal = new Meal(mealName, negativePrice, mealSort);
			fail("Expected exception was not thrown.");
		} catch (IllegalArgumentException e) {
			assertEquals("Price can not be negative.", e.getMessage());
		}
	}
	
	@Test
	public void testConstructionWithEmptyName_fail() {
	
		final String name = "";
		try {
			final Meal meal = new Meal(name, price, mealSort);
			fail("Expected exception was not thrown.");
		} catch (IllegalArgumentException e) {
			assertEquals("Name must be set.", e.getMessage());
		}
	}
	
	@Test
	public void testConstructionWithNullMealSort_fail() {
	
		final MealSort sort = null;
		try {
			final Meal meal = new Meal(mealName, price, sort);
			fail("Expected exception was not thrown.");
		} catch (IllegalArgumentException e){
			assertEquals("Meal sort must be set.", e.getMessage());
		}
	}

	

}
