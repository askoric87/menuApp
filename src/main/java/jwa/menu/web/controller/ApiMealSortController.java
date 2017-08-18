package jwa.menu.web.controller;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import jwa.menu.model.MealSort;
import jwa.menu.service.MealSortService;
import jwa.menu.web.dto.MealSortDTO;


@RestController
@RequestMapping(value = "/api/mealsorts")
public class ApiMealSortController {

	@Autowired
	private MealSortService mealSortService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<MealSortDTO>> getAll(){
		
		List<MealSort> sorts = mealSortService.findAll();
		
		if(sorts == null || sorts.isEmpty()){
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<MealSortDTO> result = new ArrayList<>();
		for (MealSort mealSort : sorts) {
			result.add(MealSortDTO.fromMealSort(mealSort));
		}
		
		return new ResponseEntity<>(result, HttpStatus.OK);
	}
		
}
