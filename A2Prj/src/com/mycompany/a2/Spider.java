package com.mycompany.a2;

import java.util.Random;
import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;


public class Spider extends Movable{

	private static Random rand = new Random();
	private int absolX;
	private int absolY;
	
	public Spider(Point location, int absolX, int absolY) {
		super(25, location, ColorUtil.rgb(255, 255, 255), rand.nextInt(360), 5, 100);
		this.absolX = absolX;
		this.absolY = absolY;
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
	public void move(int absolX, int absolY) {
	    Point oldLocation = getLocation();
	    int newHeading = getHeading() - (rand.nextInt(21) - 10);
	    setHeading(newHeading);

	    double theta = Math.toRadians(90 - newHeading);
	    double deltaX = Math.cos(theta) * getSpeed();
	    double deltaY = Math.sin(theta) * getSpeed();

	    float newX = oldLocation.getX() + (float) deltaX;
	    float newY = oldLocation.getY() + (float) deltaY;

	    int absolSpiderX = (int) newX + absolX;
	    int absolSpiderY = (int) newY + absolY;

	    if (absolSpiderX < absolX || absolSpiderX > (absolX + 1000) || absolSpiderY < absolY || absolSpiderY > (absolY + 1000)) {
	        	        
	        setHeading(newHeading + 180);

	        newX = Math.max(absolX, Math.min(absolX + 1000, absolSpiderX)) - absolX;
	        newY = Math.max(absolY, Math.min(absolY + 1000, absolSpiderY)) - absolY;
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