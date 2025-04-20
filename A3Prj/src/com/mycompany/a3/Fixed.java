package com.mycompany.a3;


import com.codename1.charts.models.Point;
import com.codename1.ui.Graphics;


abstract class Fixed extends GameObject implements ISelectable{

	private boolean selected;

	public Fixed(int size, Point location, int color) {
		super(size, location, color);
	}
	
	/*
	 * Setter for Size
	 */
	public void setSize() {

	}

	//Setter for if object is selected
	public void setSelected(boolean b) {
		selected = b;
	}

	//Returns state of selected variable
	public boolean isSelected() {
		return selected;
	}

	//Checks if click is within the object
	public boolean contains(Point pPtrRelPrnt, Point pCmpRelPrnt) {
	    float ptrX = pPtrRelPrnt.getX();
	    float ptrY = pPtrRelPrnt.getY();

	    float x = getLocation().getX() + pCmpRelPrnt.getX();
	    float y = getLocation().getY() + pCmpRelPrnt.getY();

	    int size = (getSize() / 2) * 3;

	    return ptrX >= x - size && ptrX <= x + size &&
	           ptrY >= y - size && ptrY <= y + size;
	}


	//draws object
	public void draw(Graphics g, Point pCmpRelPrnt) {}

}
