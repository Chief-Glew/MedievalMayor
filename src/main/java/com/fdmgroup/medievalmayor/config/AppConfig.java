package com.fdmgroup.medievalmayor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.FarmStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ForestStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.LumberMillStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.MineStringHandler;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ProducerClassFromStringHandler;

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
