package com.mycompany.a2;


import com.codename1.charts.models.Point;
import com.codename1.charts.util.ColorUtil;


abstract class GameObject {

	private int size;
	private Point location;
	private int color;
	
	public GameObject(int size, Point location, int color) {
		this.size = size;
		this.location = location;
		this.color = color;
	}
	
	/*
	 * Getter for size
	 */
	public int getSize() {
		return size;
	}
	
	/*
	 * Getter for location
	 */
	public Point getLocation() {
		return location;
	}
	
	/*
	 * Setter for location
	 */
	public void setLocation(float x, float y) {
		location = new Point(x, y);
	}
	
	/*
	 * Getter for color
	 */
	public int getColor() {
		return color;
	}
	
	/*
	 * Setter for color
	 */
	public void setColor(int color) {
		this.color = color;
	}
	
}
