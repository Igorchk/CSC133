
package com.mycompany.a3;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.ui.Graphics;
import com.codename1.charts.util.ColorUtil;


public class Ant extends Movable implements IFoodie, IDrawable{
	
	private GameWorld gw;
	private int maximumSpeed = 10;
	private int foodConsumptionRate = 1;
	private int healthLevel = 10;
	private int lastFlagReached = 0;
	private static Ant theAnt;
	
	private Ant(int size, Point location, int color, int heading, int speed, int foodLevel) {
		super(size, location, color, heading, speed, foodLevel);
	}

	public static Ant getAnt() {
		if (theAnt == null) {
			theAnt = new Ant(75, new Point(50, 50), ColorUtil.rgb(128,128,128), 0, 10, 1500);
		}
		return theAnt;
	}
	
	public void resetAnt() {
	    setLocation(50,50);
		setHealthLevel(10);
	    setFoodLevel(1500);
	    setHeading(0);
	    setSpeed(10);
	    setLastFlagReached(0);
	}
	
	public void setGameWorld(GameWorld gw) {
		this.gw = gw;
	}
	
	/*
	 * Overrides parents method to change color
	 */
	@Override
	public void setColor(int color) {	
	}
	
	/*
	 * Returns Ant's top achievable speed
	 */
	public int getMaximumSpeed() {
		return maximumSpeed;
	}

	/*
	 * Sets Ant's top speed
	 */
	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	/*
	 * Returns Ant's foodConsumptionRate
	 */
	public int getFoodConsumption() {
		return foodConsumptionRate;
	}

	/*
	 * Setter for foodConsumption using IFoodie interface
	 */
	@Override
	public void setFoodConsumption(int foodConsumption) {
		int newFoodLevel = getFoodLevel() - foodConsumption;
		setFoodLevel(Math.max(0, newFoodLevel));
		if(getFoodLevel() == 0) {
			setSpeed(0);
		}
	}

	/*
	 * Returns Ant's top healthLevel
	 */
	public int getHealthLevel() {
		return healthLevel;
	}

	/*
	 * Called to set Ant's healthLevel, if lower than 0 set to 0
	 */
	public void setHealthLevel(int healthLevel) {
		this.healthLevel = Math.max(0, healthLevel);
		checkSpeed();
	}

	/*
	 * Returns Ant's lastFlagReached
	 */
	public int getLastFlagReached() {
		return lastFlagReached;
	}

	/*
	 * Called to set Ant's lastFlagReached
	 */
	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}
	
	/*
	 * Checks and alters max speed based on health
	 */
	public void checkSpeed() {
		if(healthLevel >= 0) {
			int newMaxSpeed = (int) (maximumSpeed * ((float)healthLevel / 10));
			setMaximumSpeed(newMaxSpeed);
			if(getSpeed() > getMaximumSpeed()) {
				setSpeed(maximumSpeed);
			}
		}else {
			setSpeed(0);
		}
	}

	/*
	 * Checks foodLevel and healthLevel -> if not 0 then accelerates the Ant
	 */
	public void accelerate() {		
		
		if(getFoodLevel() > 0 && getHealthLevel() > 0) {
			
			int newSpeed = getSpeed() + 1;
			if(newSpeed <= getMaximumSpeed()) {
				setSpeed(newSpeed);
			}
			else {
				setSpeed(getMaximumSpeed());
			}
		}
	}
	
	/*
	 * Called to slow down the Ant by adjusting its speed
	 */
	public void brake() {
		if(getSpeed() > 0) {
			setSpeed(getSpeed() - 1);
		}
	}
	
	/*
	 * Turns Ant left by adjusting heading by -5
	 */
	public void turnLeft() {
		setHeading(getHeading() - 15);
	}
	
	/*
	 * Turns Ant right by adjusting heading by +5
	 */
	public void turnRight() {
		setHeading(getHeading() + 15);
	}
	
	/*
	 * Checks if the current flag reached is the next one if so then sets new last flag, if not tells which flag to go to
	 */
	public void flagCollision(int sequenceNumber) {
		if((getLastFlagReached() + 1) == sequenceNumber) {
			setLastFlagReached(sequenceNumber);
			System.out.println("Your Ant has reached flag " + sequenceNumber + "\n");
		}else {
            Dialog.show("Error", "Wrong Flag", "OK", null);
			}
	}
	
	/*
	 * Adjusts Ant's foodLevel based on the amount foodStation had
	 */
	public void foodStationCollision(int amount) {
		setFoodLevel(getFoodLevel() + amount);
	}
	
	/*
	 * Reduces Ant's health by 1
	 */
	public void spiderCollision() {
		if(getHealthLevel() > 0 ){
			setHealthLevel(getHealthLevel() - 1);
		}
	}

	@Override
	public void draw(Graphics g, Point pCmpRelPrnt) {
        float x = getLocation().getX() + pCmpRelPrnt.getX() - getSize()/2;
        float y = getLocation().getY() + pCmpRelPrnt.getY() - getSize()/2;
        g.setColor(ColorUtil.BLACK);
        g.fillArc((int)x, (int)y, getSize(), getSize(), 0, 360);
	}
	
	/*
	 * Returns Ant's current stats
	 */
	@Override
	public String toString() {
		return "Ant: loc=" + Math.round(getLocation().getX() * 100)/100.0f + ", " + Math.round(getLocation().getY()* 100)/100.0f + 
				" color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + 
				"] heading=" + getHeading() + " speed=" + getSpeed() + " size=" + getSize() +
				" maxSpeed=" + getMaximumSpeed() + " foodConsumptionRate=" + getFoodConsumption();
	}
	
	@Override
	public void handleCollision(GameObject otherObject) {
		if(otherObject instanceof Spider) {
			Spider spider = (Spider) otherObject;
			spiderCollision();
			gw.getSpiderSound();
		}else if(otherObject instanceof FoodStation) {
			FoodStation foodStation = (FoodStation) otherObject;
			foodStationCollision(foodStation.getCapacity());
			gw.getSpiderSound();
		}else if(otherObject instanceof Flag) {
			Flag flag = (Flag) otherObject;
			flagCollision(flag.getSequenceNumber());
			gw.getSpiderSound();
		}
	}

	@Override
	public void move(int absolX, int absolY, int mapWidth, int mapHeight) {
		Point oldLocation = getLocation();
		
		double theta = Math.toRadians(90 - getHeading());
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
}
