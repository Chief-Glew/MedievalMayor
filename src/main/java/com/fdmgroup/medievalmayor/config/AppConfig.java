package com.fdmgroup.medievalmayor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fdmgroup.medievalmayor.game.events.BanditsAttackHandler;
import com.fdmgroup.medievalmayor.game.events.MigrantsAppearHandler;
import com.fdmgroup.medievalmayor.game.events.RandomEventHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.FarmStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.ForestStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.GuardHouseStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.LumberMillStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.MineStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.ResourceProducerClassFromStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers.CarpenterKiller;
import com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers.FarmersKiller;
import com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers.GuardKiller;
import com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers.LumberjackKiller;
import com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers.MinersKiller;
import com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers.PeopleKillingHandler;
import com.fdmgroup.medievalmayor.game.handlers.peoplekillinghandlers.UnassignedPeopleKiller;
import com.fdmgroup.medievalmayor.game.handlers.resourcestealinghandlers.ResourceStealingHandler;
import com.fdmgroup.medievalmayor.game.handlers.resourcestealinghandlers.StealFoodHandler;
import com.fdmgroup.medievalmayor.game.handlers.resourcestealinghandlers.StealGoldHandler;
import com.fdmgroup.medievalmayor.game.handlers.resourcestealinghandlers.StealLumberHandler;
import com.fdmgroup.medievalmayor.game.handlers.resourcestealinghandlers.StealWoodHandler;
import com.fdmgroup.medievalmayor.game.handlers.upgradehandlers.FarmUpgradeHandler;
import com.fdmgroup.medievalmayor.game.handlers.upgradehandlers.ForestUpgradeHandler;
import com.fdmgroup.medievalmayor.game.handlers.upgradehandlers.LumberMillUpgradeHandler;
import com.fdmgroup.medievalmayor.game.handlers.upgradehandlers.MineUpgradeHandler;
import com.fdmgroup.medievalmayor.game.handlers.upgradehandlers.ResourceProducerUpgradeHandler;

@Configuration
@ComponentScan({"com.fdmgroup.medievalmayor.game", "com.fdmgroup.medievalmayor.controllers", "com.fdmgroup.medievalmayor.CRUD"})
public class AppConfig {
	
	@Bean
	public ResourceProducerClassFromStringHandler resourceProducerClassFromStringHandler(){
		ResourceProducerClassFromStringHandler resourceProducerClassFromStringHandler = new FarmStringHandler();
		resourceProducerClassFromStringHandler.addToChain(new MineStringHandler());
		resourceProducerClassFromStringHandler.addToChain(new ForestStringHandler());
		resourceProducerClassFromStringHandler.addToChain(new LumberMillStringHandler());
		resourceProducerClassFromStringHandler.addToChain(new GuardHouseStringHandler());

		return resourceProducerClassFromStringHandler;
	}

	
	@Bean
	@Autowired
	public ResourceProducerUpgradeHandler resourceProducerUpgradeHandler(FarmUpgradeHandler farmUpgradeHandler, MineUpgradeHandler mineUpgradeHandler, ForestUpgradeHandler forestUpgradeHandler, LumberMillUpgradeHandler lumberMillUpgradeHandler){
		ResourceProducerUpgradeHandler resourceProducerUpgradeHandler = farmUpgradeHandler;
		resourceProducerUpgradeHandler.addToChain(mineUpgradeHandler);
		resourceProducerUpgradeHandler.addToChain(forestUpgradeHandler);
		resourceProducerUpgradeHandler.addToChain(lumberMillUpgradeHandler);
		return resourceProducerUpgradeHandler;
	}
	
	@Bean
	@Autowired
	public PeopleKillingHandler peopleKillingHandler(CarpenterKiller carpenterKiller, FarmersKiller farmersKiller, GuardKiller guardKiller, LumberjackKiller lumberjackKiller, MinersKiller minersKiller, UnassignedPeopleKiller unassignedPeopleKiller) {
		PeopleKillingHandler peopleKillingHandler = unassignedPeopleKiller;
		peopleKillingHandler.addToChain(minersKiller);
		peopleKillingHandler.addToChain(lumberjackKiller);
		peopleKillingHandler.addToChain(guardKiller);
		peopleKillingHandler.addToChain(farmersKiller);
		peopleKillingHandler.addToChain(carpenterKiller);
		peopleKillingHandler.addToChain(peopleKillingHandler);
		return peopleKillingHandler;
	}
	
	@Bean
	@Autowired
	public ResourceStealingHandler resourceStealingHandler(StealFoodHandler stealFoodHandler, StealWoodHandler stealWoodHandler, StealGoldHandler stealGoldHandler, StealLumberHandler stealLumberHandler) {
		ResourceStealingHandler resourceStealingHandler = stealFoodHandler;
		resourceStealingHandler.addToChain(stealWoodHandler);
		resourceStealingHandler.addToChain(stealGoldHandler);
		resourceStealingHandler.addToChain(stealLumberHandler);
		resourceStealingHandler.addToChain(resourceStealingHandler);
		return resourceStealingHandler;
	}
	
	@Bean
	@Autowired
	public RandomEventHandler randomEventHandler(MigrantsAppearHandler migrantsAppearHandler, BanditsAttackHandler banditsAttackHandler) {
		RandomEventHandler randomEventHandler = migrantsAppearHandler;
		randomEventHandler.addToChain(banditsAttackHandler);
		
		return randomEventHandler;
	}
}

