package com.fdmgroup.medievalmayor.demos;

import com.fdmgroup.medievalmayor.game.resourceproducers.resources.Resource;
import com.fdmgroup.medievalmayor.game.resourceproducers.resources.ResourceFactory;
import com.fdmgroup.medievalmayor.game.resourceproducers.resources.ResourceStorageFactory;
import com.fdmgroup.medievalmayor.game.resourceproducers.resources.ResourceStorageHandler;

public class ResourceDemo {

	public static void main(String[] args) {
		
		ResourceFactory resourceFactory = new ResourceFactory();
		ResourceStorageFactory storageFactory = new ResourceStorageFactory();
		
		ResourceStorageHandler foodStorageRoot =  storageFactory.getFoodStorage(10);
		ResourceStorageHandler goldStorage = storageFactory.getGoldStorage(10);
		
		foodStorageRoot.addResourceStore(goldStorage);
		
		Resource food = resourceFactory.getFood(20);
		Resource gold = resourceFactory.getGold(5);
		
		System.out.println(foodStorageRoot.getResources());
		
		foodStorageRoot.addResource(food);
		
		System.out.println(foodStorageRoot.getResources());
		
		foodStorageRoot.addResource(gold);
		
		System.out.println(foodStorageRoot.getResources());
		
	}

}
