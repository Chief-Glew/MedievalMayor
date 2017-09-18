package com.fdmgroup.medievalmayor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.FarmStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.ForestStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.LumberMillStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.MineStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.ResourceProducerClassFromStringHandler;




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
}