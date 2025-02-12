package com.mycompany.a1;


import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import java.util.Random;



import java.util.ArrayList;


public class GameWorld {
	
	private ArrayList<GameObject> gameObject;
	Random rand = new Random();
	
	public GameWorld() {
	}
	
	
	
	public void init() {		
		
		gameObject = new ArrayList<>();
		
		gameObject.add(new Flag(25, new Point(100,100), ColorUtil.rgb(0, 0, 255), 1));
		gameObject.add(new Flag(25, new Point(100,100), ColorUtil.rgb(0, 0, 255), 2));
		gameObject.add(new Flag(25, new Point(100,100), ColorUtil.rgb(0, 0, 255), 3));
		gameObject.add(new Flag(25, new Point(100,100), ColorUtil.rgb(0, 0, 255), 4));

		gameObject.add(new Ant(25, new Point(50, 50), ColorUtil.rgb(128,128,128), 0, 1, 25));
		
		gameObject.add(new Spider(25, new Point(), ColorUtil.rgb(255, 255, 255), rand.nextInt(360), 1, 25));
		gameObject.add(new Spider(25, new Point(), ColorUtil.rgb(255, 255, 255), rand.nextInt(360), 1, 25));
		
		gameObject.add(new FoodStation());
		gameObject.add(new FoodStation());
		
	}
	
	
	private void addGameObject(GameObject object) {
		gameObject.add(object);
	}
	
	
	public void accelerate() {
		
	}
	
	public void brake() {
		
	}
	
	public void turnLeft() {
		
	}
	
	public void turnRight() {
		
	}
	
	public void consumptionRate() {
		
	}
	
	public void flagCollision(int sequenceNumber) {
		
	}
	
	public void foodStationCollision() {
		
	}
	
	public void spiderCollision() {
		
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
