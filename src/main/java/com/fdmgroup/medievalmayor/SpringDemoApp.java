package com.fdmgroup.medievalmayor;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fdmgroup.medievalmayor.config.AppConfig;
import com.fdmgroup.medievalmayor.game.City;
import com.fdmgroup.medievalmayor.game.CityService;
import com.fdmgroup.medievalmayor.game.building.BuildingManager;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Farm;
import com.fdmgroup.medievalmayor.game.building.resourcebuilding.Mine;
import com.fdmgroup.medievalmayor.game.exceptions.AssignedNegativeNumberException;
import com.fdmgroup.medievalmayor.game.exceptions.InsufficentPopulationException;

public class SpringDemoApp {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		for(String beanName : applicationContext.getBeanDefinitionNames()){
			System.out.println(beanName);
		}
		
		CityService cityService = applicationContext.getBean(CityService.class);
		Mine mine = new Mine(3);
		City city = new City(10, 20, 5, new Farm(3), mine);
		try {
			BuildingManager.getInstance().assignPeopleToBuilding(2,10,mine);
		} catch (InsufficentPopulationException | AssignedNegativeNumberException e) {
			e.printStackTrace();
		}
		
		System.out.println(city.getGold());
		cityService.updateTurn(city);
		System.out.println(city.getGold());
		
		((ConfigurableApplicationContext)applicationContext).close();
	}
}
