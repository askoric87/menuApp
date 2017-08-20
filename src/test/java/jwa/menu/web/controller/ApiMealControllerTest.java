package jwa.menu.web.controller;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityNotFoundException;

import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import jwa.menu.model.Meal;
import jwa.menu.model.MealSort;
import jwa.menu.service.MealService;
import jwa.menu.web.dto.MealDTO;
import jwa.menu.web.dto.MealSortDTO;

public class ApiMealControllerTest {

	@Mock
	private MealService mealService;

	@InjectMocks
	private ApiMealController mealController;

	private final Meal bolonjeze = new Meal(1L, "Bolonjeze", 100, new MealSort(1L, "Pasta"));
	private final Meal losos = new Meal(2L, "Losos", 200, new MealSort(2L, "Riba"));
	private final MealDTO bolonjezeDTO = new MealDTO(1L, "Bolonjeze", 100, new MealSortDTO(1L, "Pasta", new ArrayList<MealDTO>()));

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAll() {
		when(mealService.findAll(0)).thenReturn(new PageImpl(Arrays.asList(bolonjeze, losos)));

		List<MealDTO> result = mealController.getAll(null, 0).getBody();

		verify(mealService, times(1)).findAll(0);
		assertEquals(result.size(), 2);
		MealDTO firstResult = result.get(0);
		assertEquals(firstResult.getId(), bolonjeze.getId());
		assertEquals(firstResult.getName(), bolonjeze.getName());
		assertEquals(firstResult.getPrice(), bolonjeze.getPrice(), 0.001);
		assertEquals(firstResult.getMealSort().getId(), bolonjeze.getMealSort().getId());

		MealDTO secondResult = result.get(1);
		assertEquals(secondResult.getId(), losos.getId());
		assertEquals(secondResult.getName(), losos.getName());
		assertEquals(secondResult.getPrice(), losos.getPrice(), 0.001);
		assertEquals(secondResult.getMealSort().getId(), losos.getMealSort().getId());
	}

	@Test
	public void testGetAllByName() {
		when(mealService.findByName(bolonjeze.getName(), 0)).thenReturn(new PageImpl(Arrays.asList(bolonjeze)));

		List<MealDTO> result = mealController.getAll(bolonjeze.getName(), 0).getBody();

		verify(mealService, times(1)).findByName(bolonjeze.getName(), 0);
		assertEquals(result.size(), 1);
		MealDTO firstResult = result.get(0);
		assertEquals(firstResult.getId(), bolonjeze.getId());
		assertEquals(firstResult.getName(), bolonjeze.getName());
		assertEquals(firstResult.getPrice(), bolonjeze.getPrice(), 0.001);
		assertEquals(firstResult.getMealSort().getId(), bolonjeze.getMealSort().getId());

	}

	@Test
	public void testGetByNameEmpty() {
		when(mealService.findByName(bolonjeze.getName(), 0)).thenReturn(new PageImpl(new ArrayList<>()));

		List<MealDTO> result = mealController.getAll(bolonjeze.getName(), 0).getBody();

		verify(mealService, times(1)).findByName(bolonjeze.getName(), 0);
		assertEquals(result.size(), 0);

	}
	
	@Test
	public void testFindOneEmpty() {
		when(mealService.findOne(bolonjeze.getId())).thenReturn(null);

		ResponseEntity<MealDTO> result = mealController.getOne(bolonjeze.getId());

		verify(mealService, times(1)).findOne(bolonjeze.getId());
		assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);

	}
	
	@Test
	public void testFindOne() {
		when(mealService.findOne(bolonjeze.getId())).thenReturn(bolonjeze);

		ResponseEntity<MealDTO> result = mealController.getOne(bolonjeze.getId());

		verify(mealService, times(1)).findOne(bolonjeze.getId());
		assertEquals(bolonjeze.getId(), bolonjeze.getId());
		assertEquals(result.getStatusCode(), HttpStatus.OK);

	}
	
	@Test
	public void testAddMeal() {
		when(mealService.save(any(Meal.class))).thenReturn(bolonjeze);

		ResponseEntity<MealDTO> result = mealController.addMeal(bolonjezeDTO);

		verify(mealService, times(1)).save(any(Meal.class));
		assertEquals(bolonjeze.getId(), bolonjeze.getId());
		assertEquals(bolonjeze.getName(), bolonjeze.getName());
		assertEquals(bolonjeze.getPrice(), bolonjeze.getPrice(), 0.001);
		assertEquals(bolonjeze.getMealSort().getId(), bolonjeze.getMealSort().getId());
		assertEquals(result.getStatusCode(), HttpStatus.CREATED);

	}
	
	@Test
	public void testEditMealWithWrongId() {
		
		ResponseEntity<MealDTO> result = mealController.editMeal(losos.getId(), bolonjezeDTO); 

		verify(mealService, never()).findOne(losos.getId());
		assertEquals(result.getStatusCode(), HttpStatus.BAD_REQUEST);
	}
	
	@Test
	public void testEditMealWithIdNull() {
		when(mealService.findOne(any(Long.class))).thenReturn(null);
		
		try{
		mealController.editMeal(bolonjezeDTO.getId(), bolonjezeDTO);
		fail("Expected exception was not thrown");
		}catch (EntityNotFoundException e) {
			assertEquals("Meal cannot be found.", e.getMessage());
			verify(mealService, times(1)).findOne(bolonjezeDTO.getId());
		}
				
	}
	
	@Test
	public void testEditMeal() {
		when(mealService.save(any(Meal.class))).thenReturn(bolonjeze);
		when(mealService.findOne(bolonjeze.getId())).thenReturn(bolonjeze);
		
		ResponseEntity<MealDTO> result = mealController.editMeal(bolonjezeDTO.getId(), bolonjezeDTO);

		verify(mealService, times(1)).save(any(Meal.class));
		assertEquals(bolonjezeDTO.getId(), result.getBody().getId());
		assertEquals(bolonjeze.getName(), result.getBody().getName());
		assertEquals(bolonjeze.getPrice(), result.getBody().getPrice(), 0.001);
		assertEquals(bolonjeze.getMealSort().getId(), result.getBody().getMealSort().getId());
		assertEquals(result.getStatusCode(), HttpStatus.OK);

	}
	
	@Test
	public void testDeleteWithIdNull() {
		when(mealService.findOne(bolonjeze.getId())).thenReturn(null);
		
		try{
		mealController.delete(bolonjeze.getId());
		fail("Expected exception was not thrown");
		}catch (EntityNotFoundException e) {
			assertEquals("Meal cannot be found.", e.getMessage());
			verify(mealService, times(1)).findOne(bolonjeze.getId());
		}
				
	}
	@Test
	public void testDelete() {
		when(mealService.findOne(bolonjeze.getId())).thenReturn(bolonjeze);
		
		mealController.delete(bolonjeze.getId());
		
		verify(mealService, times(1)).delete(bolonjeze.getId());
	}
	
}
