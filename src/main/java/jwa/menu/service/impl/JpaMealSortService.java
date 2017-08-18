package jwa.menu.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jwa.menu.model.MealSort;
import jwa.menu.repository.MealSortRepository;
import jwa.menu.service.MealSortService;


@Service
@Transactional
public class JpaMealSortService implements MealSortService {

	@Autowired
	private MealSortRepository mealSortRepository;
	
	@Override
	public List<MealSort> findAll() {
		return mealSortRepository.findAll();
	}

	@Override
	public MealSort save(MealSort mealSort) {
		return mealSortRepository.save(mealSort);
	}

}
