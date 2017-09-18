package com.fdmgroup.medievalmayor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ProducerClassFromStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.FarmStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.ForestStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.LumberMillStringHandler;
import com.fdmgroup.medievalmayor.game.handlers.getproducertypehandlers.MineStringHandler;

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
}
