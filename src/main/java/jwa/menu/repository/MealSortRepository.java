package jwa.menu.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import jwa.menu.model.MealSort;


@Repository
public interface MealSortRepository extends JpaRepository<MealSort, Long> {

}
