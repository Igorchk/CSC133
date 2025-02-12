/*Finish adjusting speed according to healthLevel
 * Update base values: 4
 */

package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.util.ColorUtil;


public class Ant extends Movable implements IFoodie{
	
	private int maximumSpeed = 10;
	private int foodConsumptionRate = 1;
	private int healthLevel = 10;
	private int lastFlagReached = 1;
	
	public Ant(int size, Point location, int color, int heading, int speed, int foodLevel) {
		super(size, location, color, heading, speed, foodLevel);
	}

	
	public int getMaximumSpeed() {
		return maximumSpeed;
	}

	public void setMaximumSpeed(int maximumSpeed) {
		this.maximumSpeed = maximumSpeed;
	}

	public int getFoodConsumption() {
		return foodConsumptionRate;
	}

	@Override
	public void setFoodConsumption(int foodConsumption) {
		int newFoodLevel = getFoodLevel() - foodConsumption;
		setFoodLevel(Math.max(0, newFoodLevel));
		if(getFoodLevel() == 0) {
			setSpeed(0);
		}
	}

	public int getHealthLevel() {
		return healthLevel;
	}

	public void setHealthLevel(int healthLevel) {
		this.healthLevel = healthLevel;
		checkSpeed();
	}

	public int getLastFlagReached() {
		return lastFlagReached;
	}

	public void setLastFlagReached(int lastFlagReached) {
		this.lastFlagReached = lastFlagReached;
	}
	
	//Checks and alters max speed based on health
	public void checkSpeed() {
		if(healthLevel >= 0) {
			int newMaxSpeed = (int) (maximumSpeed * ((float)healthLevel / 10));
			setMaximumSpeed(newMaxSpeed);
			if(getSpeed() > getMaximumSpeed()) {
				setSpeed(maximumSpeed);
			}
		}
		else {
			setSpeed(0);
		}
	}
	
	
	public void accelerate() {
		int newSpeed = getSpeed() + 1;
		
		if(newSpeed < getMaximumSpeed()) {
			setSpeed(newSpeed);
		}
		else {
			setSpeed(getMaximumSpeed());
		}

	}
	
	public void brake() {
		setSpeed(getSpeed() - 1);
	}
	
	public void turnLeft() {
		setHeading(getHeading() - 5);
	}
	
	public void turnRight() {
		setHeading(getHeading() + 5);
	}
}
