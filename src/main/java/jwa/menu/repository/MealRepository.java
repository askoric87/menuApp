package jwa.menu.repository;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import jwa.menu.model.Meal;


@Repository
public interface MealRepository extends PagingAndSortingRepository<Meal, Long > {

	Page<Meal> findByNameLike(String name, Pageable page);
}
