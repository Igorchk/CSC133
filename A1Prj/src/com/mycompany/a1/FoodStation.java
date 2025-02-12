package com.mycompany.a1;

import com.codename1.charts.models.Point;

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

}