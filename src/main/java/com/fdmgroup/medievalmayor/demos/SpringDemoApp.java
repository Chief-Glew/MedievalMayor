package com.fdmgroup.medievalmayor.demos;


import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.fdmgroup.medievalmayor.config.AppConfig;
import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.city.CityFactory;
import com.fdmgroup.medievalmayor.game.command.CommandInvoker;
import com.fdmgroup.medievalmayor.game.command.NextTurnCommand;
import com.fdmgroup.medievalmayor.game.command.SetNumberOfWorkersInBuildingFromCityCommand;
import com.fdmgroup.medievalmayor.game.command.UserCommand;
import com.fdmgroup.medievalmayor.game.resourceproducers.Farm;
import com.fdmgroup.medievalmayor.game.resourceproducers.Mine;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public class SpringDemoApp {

	public static void main(String[] args) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
		for(String beanName : applicationContext.getBeanDefinitionNames()){
			System.out.println(beanName);
		}
		
		
		CityFactory cityFactory = new CityFactory();
		City city = cityFactory.getNewCityWithForest();
		ResourceProducer farm = city.getResourceProducerOfType(Farm.class);
		ResourceProducer mine = city.getResourceProducerOfType(Mine.class);

		CommandInvoker commandInvoker = new CommandInvoker();
		CommandInvoker nextTurnInvoker = new CommandInvoker();
		
		UserCommand nextTurn = new NextTurnCommand(city);
		nextTurnInvoker.setCommand(nextTurn);
		
		System.out.println("Initial City Values");
		System.out.println("Total population: "+city.getTotalPopulation());
		System.out.println("Initial number of unnasigned people: " + city.getUnassignedPopulation());
		System.out.println("Initial number of Farmers: " + farm.getNumberOfAssignedWorkers());
		System.out.println("Initial number of Miners: " + mine.getNumberOfAssignedWorkers());
		System.out.println("Initial Food: " + city.getFood());
		System.out.println("Initial Gold: "+city.getGold());
		System.out.println("");
		
		
		UserCommand asign4PeopleToFarm = new SetNumberOfWorkersInBuildingFromCityCommand(city, farm, 4);
		UserCommand asign5PeopleToMine = new SetNumberOfWorkersInBuildingFromCityCommand(city, mine, 5);
		commandInvoker.setCommand(asign5PeopleToMine);
		commandInvoker.invokeCommands();
		commandInvoker.setCommand(asign4PeopleToFarm);
		commandInvoker.invokeCommands();
		nextTurnInvoker.invokeCommands();
		
		
		System.out.println("Turn 1 City Values");
		System.out.println("Total population: "+city.getTotalPopulation());
		System.out.println("Current number of unnasigned people: " + city.getUnassignedPopulation());
		System.out.println("Current number of Farmers: " + farm.getNumberOfAssignedWorkers());
		System.out.println("Current number of Miners: " + mine.getNumberOfAssignedWorkers());
		System.out.println("Current Food: " + city.getFood());
		System.out.println("Current Gold: "+city.getGold());
		System.out.println("");
		
		

		((ConfigurableApplicationContext)applicationContext).close();
	}
}
