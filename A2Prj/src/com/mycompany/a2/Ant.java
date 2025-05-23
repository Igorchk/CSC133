
package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.ui.Dialog;
import com.codename1.charts.util.ColorUtil;


public class Ant extends Movable implements IFoodie{
	
	private int maximumSpeed = 10;
	private int foodConsumptionRate = 1;
	private int healthLevel = 10;
	private int lastFlagReached = 1;
	private static Ant theAnt;
	
	private Ant(int size, Point location, int color, int heading, int speed, int foodLevel) {
		super(size, location, color, heading, speed, foodLevel);
	}

	public static Ant getAnt() {
		if (theAnt == null) {
			theAnt = new Ant(25, new Point(50, 50), ColorUtil.rgb(128,128,128), 0, 1, 25);
		}
		return theAnt;
	}
	
	public void resetAnt() {
	    setLocation(50,50);
		setHealthLevel(10);
	    setFoodLevel(25);
	    setHeading(0);
	    setSpeed(1);
	    setLastFlagReached(1);
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
		System.out.println("Ant changed food level.\n");
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
		setHeading(getHeading() - 5);
	}
	
	/*
	 * Turns Ant right by adjusting heading by +5
	 */
	public void turnRight() {
		setHeading(getHeading() + 5);
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
}
