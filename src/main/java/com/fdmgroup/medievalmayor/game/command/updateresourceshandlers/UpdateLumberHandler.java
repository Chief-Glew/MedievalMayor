package com.fdmgroup.medievalmayor.game.command.updateresourceshandlers;


import com.fdmgroup.medievalmayor.game.city.City;
import com.fdmgroup.medievalmayor.game.resourceproducers.LumberMill;
import com.fdmgroup.medievalmayor.game.resourceproducers.ResourceProducer;
import com.fdmgroup.medievalmayor.game.resourceproducers.resources.Resource;

public class UpdateLumberHandler extends UpdateResourcesHandler {

	private static int lumberPerWood = 4;
	
	

	@Override
	public void handle(City city) {
		ResourceProducer lumberMill = city.getResourceBuildingOfType(LumberMill.class);
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
	}

}
