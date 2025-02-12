package com.mycompany.a1;


import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;



import java.util.ArrayList;


public class GameWorld {
	
	//Creates a list that will store all Game Objects
	private ArrayList<GameObject> gameObject;
	
	//Creates 'rand' for random number generation 
	Random rand = new Random();
	
	public GameWorld() {
	}
	
	public void init() {		
		
		gameObject = new ArrayList<>();
		
		//Creates 4 flags and assigns size of 25, predefined locations, color blue, and sequence numbers accordingly
		gameObject.add(new Flag(25, new Point(100,100), ColorUtil.rgb(0, 0, 255), 1));
		gameObject.add(new Flag(25, new Point(900,100), ColorUtil.rgb(0, 0, 255), 2));
		gameObject.add(new Flag(25, new Point(100,900), ColorUtil.rgb(0, 0, 255), 3));
		gameObject.add(new Flag(25, new Point(900,900), ColorUtil.rgb(0, 0, 255), 4));

		//Create object Ant and assigns size of 25, predefined start location, color grey, heading direction of 0, speed of 1, and initial foodLevel 25
		gameObject.add(new Ant(25, new Point(50, 50), ColorUtil.rgb(128,128,128), 0, 1, 25));
		
		//Creates 2 Spiders with size 25, random location, color black, random heading, speed of 5, and foodLevel 100 (we won't be using it with spiders)
		gameObject.add(new Spider(25, new Point(), ColorUtil.rgb(255, 255, 255), rand.nextInt(360), 5, 100));
		gameObject.add(new Spider(25, new Point(), ColorUtil.rgb(255, 255, 255), rand.nextInt(360), 5, 100));
		
		//Creates 2 FoodStations with random size, random location, color green, capacity is proportional to size so it will be set in the class
		gameObject.add(new FoodStation(rand.nextInt(41) + 10, new Point(rand.nextInt(1000), rand.nextInt(1000)), ColorUtil.rgb(0, 255, 0)));
		gameObject.add(new FoodStation(rand.nextInt(41) + 10, new Point(rand.nextInt(1000), rand.nextInt(1000)), ColorUtil.rgb(0, 255, 0)));
	}
	
	
	public void accelerate() {
		for(int i = 0; i <= gameObject.size(); i++) {
			if(gameObject.get(i) instanceof Ant) {
				((Ant) gameObject.get(i)).accelerate();
			}
		}
	}
	
	public void brake() {
		for(int i = 0; i <= gameObject.size(); i++) {
			if(gameObject.get(i) instanceof Ant) {
				((Ant) gameObject.get(i)).brake();
			}
		}
	}
	
	public void turnLeft() {
		for(int i = 0; i <= gameObject.size(); i++) {
			if(gameObject.get(i) instanceof Ant) {
				((Ant) gameObject.get(i)).turnLeft();
			}
		}
	}
	
	public void turnRight() {
		for(int i = 0; i <= gameObject.size(); i++) {
			if(gameObject.get(i) instanceof Ant) {
				((Ant) gameObject.get(i)).turnRight();
			}
		}
	}
	
	public void consumptionRate() {
		
	}
	
	public void flagCollision(int sequenceNumber) {
		for(int i = 0; i <= gameObject.size(); i++) {
			if(gameObject.get(i) instanceof Ant) {
				((Ant) gameObject.get(i)).flagCollision(sequenceNumber);
			}
		}
	}
	
	public void foodStationCollision() {
		int foodStationFood = 0;
		for(int i = 0; i <= gameObject.size(); i++) {
			if(gameObject.get(i) instanceof FoodStation) {
				FoodStation station = (FoodStation) gameObject.get(i);
				if(station.getCapacity() > 0) {
					foodStationFood = station.getCapacity();
					break;
				}
			}
		}
		
		for(int i = 0; i <= gameObject.size() - 1; i++) {
			if(gameObject.get(i) instanceof Ant) {
				((Ant) gameObject.get(i)).foodStationCollision(foodStationFood);
			}
		}
	}
	
	public void spiderCollision() {
		for(int i = 0; i <= gameObject.size(); i++) {
			if(gameObject.get(i) instanceof Ant) {
				((Ant) gameObject.get(i)).spiderCollision();
			}
		}
	}
	
	public void tickedClock() {
		
	}
	
	public void showDisplay() {
		
	}
	
	public void showMap() {
		
	}
	
	public void exit() {
		
	}
	
	public void yes() {
		
	}
	
	public void no() {
		
	}
}
