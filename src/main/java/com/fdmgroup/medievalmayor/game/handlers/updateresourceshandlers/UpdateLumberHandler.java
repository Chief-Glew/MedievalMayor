package com.fdmgroup.medievalmayor.game.handlers.updateresourceshandlers;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;
import com.fdmgroup.medievalmayor.game.resources.Resource;

public class UpdateLumberHandler extends UpdateResourcesHandler {
	
	static final Logger logger = LogManager.getLogger("UpdateLumberHandler.class");

	@Override
	public void handle(City city) {
		LumberMill lumberMill = (LumberMill)city.getResourceProducerOfType(LumberMill.class);//TODO find a better way to do this
		int lumberPerWood = lumberMill.getAmountOfLumberPerWood();
		Resource maxLumberResource = lumberMill.produceResource();
		int maxLumberAmount = maxLumberResource.getAmount();
		int availabeWood = city.getResourceAmount("Wood");
		maxLumberAmount -=maxLumberAmount%lumberPerWood;
		
		int woodUsed;
		int lumberProduced;
		if(maxLumberAmount>=(availabeWood*lumberPerWood)) {
			lumberProduced = availabeWood*lumberPerWood;
			woodUsed = availabeWood;
		}
		else {
			woodUsed=maxLumberAmount/lumberPerWood;
			lumberProduced = maxLumberAmount;
		}
		
		city.addResource(resourceFactory.getWood(-woodUsed));
		city.addResource(resourceFactory.getLumber(lumberProduced));
		logger.debug("Handle method used");
	}

}
