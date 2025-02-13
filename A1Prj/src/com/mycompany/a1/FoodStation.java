package com.mycompany.a1;

import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;

public class FoodStation extends Fixed{

	private int capacity;
	
	public FoodStation(int size, Point location, int color) {
		super(size, location, color);
		this.capacity = size;
	}

	public int getCapacity() {
		return capacity;
	}
	
	public void setCapacity( ) {
		this.capacity = 0;
	}

	@Override
	public String toString() {
		return "FoodStation: loc=" + Math.round(getLocation().getX() * 100)/100.0f + ", " + Math.round(getLocation().getY()* 100)/100.0f + 
				" color=[" + ColorUtil.red(getColor()) + "," + ColorUtil.green(getColor()) + "," + ColorUtil.blue(getColor()) + 
				"] size=" + getSize() + " capacity=" + getCapacity();
	}
}