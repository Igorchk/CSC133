package com.mycompany.a3;

import java.util.Random;
import java.util.Vector;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Graphics;


public class Spider extends Movable{

	private GameWorld gw;
	private static Random rand = new Random();
	private int absolX;
	private int absolY;
	
	public Spider(Point location, int absolX, int absolY) {
		super(75, location, ColorUtil.rgb(255, 255, 255), rand.nextInt(360), 5, 100);
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
	
	public void setGameWorld(GameWorld gw) {
		this.gw = gw;
	}
	
	/*
	 * Adjusts objects location based on heading, location, and speed.
	 * Checks for borders, if border crossed adjusts heading and sets
	 * object to stay in bounds
	 */
	@Override
	public void move(int absolX, int absolY, int mapWidth, int mapHeight) {
	    Point oldLocation = getLocation();
	    int newHeading = getHeading() - (rand.nextInt(41) - 10);
	    setHeading(newHeading);

	    double theta = Math.toRadians(90 - newHeading);
	    double deltaX = Math.cos(theta) * getSpeed();
	    double deltaY = Math.sin(theta) * getSpeed();

		float newX = oldLocation.getX() + (float) deltaX;
		float newY = oldLocation.getY() + (float) deltaY;
		
		float absX = newX + absolX;
		float absY = newY + absolY;
	    
	    int radius = getSize()/2;
	    
	    if ((absX - radius) < absolX || (absX + radius) > (absolX + mapWidth) ||
	    	(absY - radius) < absolY || (absY + radius) > (absolY + mapHeight)) {
	
	        absX = Math.max(absolX + radius, Math.min(absX, absolX + mapWidth - radius));
	        absY = Math.max(absolY + radius, Math.min(absY, absolY + mapHeight - radius));
	        
	        newX = absX - absolX;
	        newY = absY - absolY;
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

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
		float x = getLocation().getX() + pCmpRelPrnt.getX();
        float y = getLocation().getY() + pCmpRelPrnt.getY();
        int size = getSize();
        
        int[] xPoint = {(int)(x - size/2), (int)(x + size/2), (int)(x)};
        int[] yPoint = {(int)(y - size/2), (int)(y - size/2), (int)(y + size/2)};
        
        g.setColor(ColorUtil.BLUE);
        g.drawPolygon(xPoint, yPoint, 3);	
        }

	@Override
	public void handleCollision(GameObject otherObject) {
		if(otherObject instanceof Ant) {
			Ant ant = (Ant) otherObject;
			ant.spiderCollision();
		}
	}
}