package com.fdmgroup.medievalmayor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fdmgroup.medievalmayor.controllers.urlstringhandlers.FarmUpgradeHandler;
import com.fdmgroup.medievalmayor.controllers.urlstringhandlers.ForestUpgradeHandler;
import com.fdmgroup.medievalmayor.controllers.urlstringhandlers.LumberMillUpgradeHandler;
import com.fdmgroup.medievalmayor.controllers.urlstringhandlers.MineUpgradeHandler;
import com.fdmgroup.medievalmayor.controllers.urlstringhandlers.ResourceProducerUpgradeHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.FarmStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ForestStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.LumberMillStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.MineStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ResourceProducerClassFromStringHandler;



@Configuration
@ComponentScan("com.fdmgroup.medievalmayor.game")
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
	public ResourceProducerUpgradeHandler resourceProducerUpgradeHandler(FarmUpgradeHandler farmUpgradeHandler, MineUpgradeHandler mineUpgradeHandler, ForestUpgradeHandler forestUpgradeHandler, LumberMillUpgradeHandler lumberMillUpgradeHandler){
		ResourceProducerUpgradeHandler resourceProducerUpgradeHandler = farmUpgradeHandler;
		resourceProducerUpgradeHandler.addToChain(mineUpgradeHandler);
		resourceProducerUpgradeHandler.addToChain(forestUpgradeHandler);
		resourceProducerUpgradeHandler.addToChain(lumberMillUpgradeHandler);
		return resourceProducerUpgradeHandler;
	}
}

