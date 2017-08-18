package jwa.menu.service.impl;



import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import jwa.menu.model.Meal;
import jwa.menu.repository.MealRepository;
import jwa.menu.service.MealService;


@Service
@Transactional
public class JpaMealService implements MealService {

	@Autowired
	private MealRepository mealRepository;
	
	@Override
	public Page<Meal> findAll(int page) {
		return mealRepository.findAll(new PageRequest(page, 10));
	}

	@Override
	public Meal findOne(Long id) {
		return mealRepository.findOne(id);
	}

	@Override
	public Meal save(Meal meal) {
		return mealRepository.save(meal);
	}

	@Override
	public void delete(Long id) {
		
		mealRepository.delete(id);
		
	}

	@Override
	public Page<Meal> findByName(String name, int page) {
		return mealRepository.findByNameLike("%" + name + "%", new PageRequest(page, 10));
	}

	@Override
	public Long count() {
		return mealRepository.count();
	}	

}
