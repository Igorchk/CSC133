package com.mycompany.a2;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class FoodStation extends Fixed{

	private int capacity;
	
	public FoodStation(int size, Point location, int color) {
		super(size, location, color);
		this.capacity = size;
	}

	/*
	 * Getter for capacity
	 */
	public int getCapacity() {
		return capacity;
	}
	
	/*
	 * Setter for capacity
	 */
	public void setCapacity( ) {
		this.capacity = 0;
	}
	
	/*
	 * Overrides parents method to change color
	 */
	@Override
	public void setColor(int color) {	
	}

	/*
	 * Returns current stats for FoodStation
	 */
	@Override
	public String toString() {
		return "FoodStation: loc=" + Math.round(getLocation().getX() * 100)/100.0f + ", " + Math.round(getLocation().getY()* 100)/100.0f + 
				" color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + 
				"] size=" + getSize() + " capacity=" + getCapacity();
	}
}