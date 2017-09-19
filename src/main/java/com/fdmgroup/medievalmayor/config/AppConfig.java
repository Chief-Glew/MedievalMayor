package com.fdmgroup.medievalmayor.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.FarmStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.ForestStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.LumberMillStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.MineStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.ResourceProducerClassFromStringHandler;
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
}

