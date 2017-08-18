package jwa.menu;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import jwa.menu.model.Meal;
import jwa.menu.model.MealSort;
import jwa.menu.service.MealService;
import jwa.menu.service.MealSortService;




@Component
public class TestData {

	@Autowired
	private MealService mealService;
	
	@Autowired
	private MealSortService mealSortService;
	
	@PostConstruct
	public void init(){
		
		MealSort rostilj = new MealSort("Rostilj");
		MealSort kuvanoJelo = new MealSort("Kuvano jelo");
		MealSort desert = new MealSort("Desert");
		MealSort specijalitet = new MealSort("Specijalitet kuce");
		mealSortService.save(rostilj);
		mealSortService.save(kuvanoJelo);
		mealSortService.save(desert);
		mealSortService.save(specijalitet);
		
		Meal cevapi = new Meal("Cevapi", 150, rostilj);
		Meal pljeskavica = new Meal("Pljeksavica", 200, rostilj);
		Meal pljeksavicaMala = new Meal("Pljeskavica mala",180 , rostilj);
		Meal beloMeso = new Meal("Belo meso", 280, rostilj);
		Meal rolovanoBeloMeso = new Meal("Rolovano belo meso", 380, rostilj);
		Meal pasulj = new Meal("Pasulj", 150, kuvanoJelo);
		Meal gulas = new Meal("Gulas", 200, kuvanoJelo);
		Meal corba = new Meal("Corba", 120, kuvanoJelo);
		Meal kolac = new Meal("Kolac", 100, desert);
		Meal baklava = new Meal("Baklava", 145, desert);
		Meal torta = new Meal("Torta", 300, desert);
		Meal biftek = new Meal("Biftek u sosu od vina", 550, specijalitet);
		Meal pile = new Meal("Punjeno peceno pile", 480, specijalitet);
		mealService.save(cevapi);
		mealService.save(pljeskavica);
		mealService.save(pljeksavicaMala);
		mealService.save(beloMeso);
		mealService.save(rolovanoBeloMeso);
		mealService.save(pasulj);
		mealService.save(gulas);
		mealService.save(corba);
		mealService.save(kolac);
		mealService.save(baklava);
		mealService.save(torta);
		mealService.save(biftek);
		mealService.save(pile);
		

		
		
		
	}
	
		
	
}
