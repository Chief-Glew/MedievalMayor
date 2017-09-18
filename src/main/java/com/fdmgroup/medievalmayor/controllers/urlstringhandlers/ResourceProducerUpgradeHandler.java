package com.fdmgroup.medievalmayor.controllers.urlstringhandlers;

import java.util.Map;

import org.springframework.ui.Model;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.command.ClientCommand;
import com.fdmgroup.medievalmayor.game.command.handlers.getproducertypehandlers.ProducerClassFromStringHandler;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;

public abstract class ResourceProducerUpgradeHandler extends URLStringHandler {

	private Map<String, Integer> cost;
	private ClientCommand clientCommand;
	private ProducerClassFromStringHandler producerClassFromStringHandler;

	public ResourceProducerUpgradeHandler(ClientCommand clientCommand, ProducerClassFromStringHandler producerClassFromStringHandler) {
		this.clientCommand = clientCommand;
		this.producerClassFromStringHandler = producerClassFromStringHandler;
	}

	protected void setCost(Map<String, Integer> cost) {
		this.cost = cost;
		this.clientCommand = clientCommand;
		this.producerClassFromStringHandler = producerClassFromStringHandler;
	}

	@Override
	public String handle(City city, String urlString, Model model) {
		Class<? extends ResourceProducer> resourceProducerClass = producerClassFromStringHandler.handle(urlString);
		ResourceProducer resourceProducer = city.getResourceProducerOfType(resourceProducerClass);
		int currentLevel = resourceProducer.getProducerLevel();
		clientCommand.upgradeResourceProducerInCity(city, resourceProducer, cost);
		int newLevel = resourceProducer.getProducerLevel();
		if (currentLevel==newLevel){
			return "notEnoughResources";
		}
		return "newUserHome";
	}

}
