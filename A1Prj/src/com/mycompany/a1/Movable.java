package com.mycompany.a1;


import com.codename1.charts.models.Point;
import com.codename1.util.MathUtil;
import com.codename1.charts.util.ColorUtil;



abstract class Movable extends GameObject{
	
	private int heading, speed, foodLevel;
	
	public Movable(int size, Point location, int color, int heading, int speed, int foodLevel) {
		super(size,location, color);
		this.heading = heading;
		this.speed = speed;
		this.foodLevel = foodLevel;
	}

	/*
	 * Returns objects current heading
	 */
	public int getHeading() {
		return heading;
	}
	
	/*
	 * Setter objects heading
	 */
	public void setHeading(int heading) {
		this.heading = heading;
	}
	
	/*
	 * Returns objects current speed
	 */
	public int getSpeed() {
		return speed;
	}
	
	/*
	 * Setter objects speed
	 */
	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	/*
	 * Returns objects current foodLevel
	 */
	public int getFoodLevel() {
		return foodLevel;
	}
	
	/*
	 * Setter objects foodLevel
	 */
	public void setFoodLevel(int foodLevel) {
		this.foodLevel = foodLevel;
	}
	
	/*
	 * Adjusts objects location based on heading, location, and speed
	 */
	public void move() {
		Point oldLocation = getLocation();
		
		double theta = Math.toRadians(90 - heading);
		double deltaX = Math.cos(theta) * speed;
		double deltaY = Math.sin(theta) * speed;
		
		float newX = oldLocation.getX() + (float) deltaX;
		float newY = oldLocation.getY() + (float) deltaY;
		
		setLocation(newX, newY);		
	}
}