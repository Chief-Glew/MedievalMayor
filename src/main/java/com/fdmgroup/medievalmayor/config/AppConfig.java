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
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ProducerClassFromStringHandler;
import com.fdmgroup.medievalmayor.game.resourceproducers.Forest;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

@Configuration
@ComponentScan("com.fdmgroup.medievalmayor.game")
public class AppConfig {
	
	@Bean
	public ProducerClassFromStringHandler producerClassFromStringHandler(){
		ProducerClassFromStringHandler producerClassFromStringHandler = new FarmStringHandler();
		producerClassFromStringHandler.addToChain(new MineStringHandler());
		producerClassFromStringHandler.addToChain(new ForestStringHandler());
		producerClassFromStringHandler.addToChain(new LumberMillStringHandler());
		return producerClassFromStringHandler;
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
