package jwa.menu.web.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import jwa.menu.model.MealSort;
import jwa.menu.service.MealSortService;
import jwa.menu.web.dto.MealSortDTO;

public class ApiMealSortControllerTest {

	@Mock
	private MealSortService mealSortService;
	
	@InjectMocks
	private ApiMealSortController mealSortController;
	
	private final MealSort pasta = new MealSort(1L, "Pasta");
	private final MealSort riba = new MealSort(2L, "Riba");
	
	@Before
	public void setUp(){
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void getAllEmpty() {
		when(mealSortService.findAll()).thenReturn(null);
		
		ResponseEntity<List<MealSortDTO>> result = mealSortController.getAll();
		
		verify(mealSortService, times(1)).findAll();
		assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
	}
	
	@Test
	public void getAll() {
		when(mealSortService.findAll()).thenReturn(Arrays.asList(pasta, riba));
		
		ResponseEntity<List<MealSortDTO>> result = mealSortController.getAll();
		
		verify(mealSortService, times(1)).findAll();
		assertEquals(result.getBody().size(), 2);
		assertEquals(result.getBody().get(0).getId(), pasta.getId());
		assertEquals(result.getBody().get(0).getName(), pasta.getName());
		assertEquals(result.getBody().get(1).getId(), riba.getId());
		assertEquals(result.getBody().get(1).getName(), riba.getName());
		assertEquals(result.getStatusCode(), HttpStatus.OK);
	}

}
