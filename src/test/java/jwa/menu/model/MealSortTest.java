package jwa.menu.model;

import static org.junit.Assert.*;

import org.junit.Test;

public class MealSortTest {

	private final String name = "my sort name";
		
	@Test
	public void testConstruction_success() {
		
		final MealSort mealSort = new MealSort(name);
		
		assertEquals(name, mealSort.getName());
		
	}
	
	public void testConstrucitionWithEmptyName(){
		
		final String  emptyName = null;
		
		try {
		final MealSort mealSort = new MealSort(emptyName);
		fail("Expected excetion was not thrown.");
		} catch (IllegalArgumentException e){
			assertEquals("Name must be set.", e.getMessage());
		}
		
	}

}
