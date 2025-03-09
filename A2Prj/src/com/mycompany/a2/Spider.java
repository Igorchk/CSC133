package com.mycompany.a2;

import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;


public class Spider extends Movable{

	private Random rand = new Random();
	
	public Spider(int size, Point location, int color, int heading, int speed, int foodLevel) {
		super(size, location, color, heading, speed, foodLevel);
	}
	
	/*
	 * Overrides parent method to not have Spider's color changed once set
	 */
	@Override
	public void setColor(int color) {
		
	}
	
	/*
	 * Overrides parent method to not have Spider's food level changed once set
	 */
	@Override
	public void setFoodLevel(int food) {
		
	}
	
	/*
	 * Adjusts objects location based on heading, location, and speed.
	 * Checks for borders, if border crossed adjusts heading and sets
	 * object to stay in bounds
	 */
	@Override
	public void move() {
		Point oldLocation = getLocation();
		int newHeading = getHeading() - (rand.nextInt(21) - 10);
		setHeading(newHeading);
		
		double theta = Math.toRadians(90 - newHeading);
		double deltaX = Math.cos(theta) * getSpeed();
		double deltaY = Math.sin(theta) * getSpeed();
		
		float newX = oldLocation.getX() + (float) deltaX;
		float newY = oldLocation.getY() + (float) deltaY;
		
		if(newX < 0 || newX > 1000 || newY < 0 || newY > 1000) {
			setHeading(newHeading + 180);
			
			newX = Math.max(0, Math.min(1000, newX));
			newY = Math.max(0, Math.min(1000, newY));
		}
		
		setLocation(newX, newY);
	}
	
	/*
	 * Returns Spider's current stats
	 */
	@Override
	public String toString() {
		return "Spider: loc=" + Math.round(getLocation().getX() * 100)/100.0f + ", " + Math.round(getLocation().getY()* 100)/100.0f + 
				" color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + 
				"] heading=" + getHeading() + " speed=" + getSpeed() + " size=" + getSize();
	}
}