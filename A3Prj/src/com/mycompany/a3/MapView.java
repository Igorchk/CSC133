package com.mycompany.a3;

import java.util.Observable;
import java.util.Observer;

import com.codename1.ui.Container;
import com.codename1.ui.Graphics;
import com.codename1.charts.models.Point;
import com.codename1.ui.plaf.Border;

public class MapView extends Container implements Observer {

    private GameWorld gw;
    private boolean isPaused = false;
    private Point lastPointerClick = null;
    private boolean waitingForClick = false;

    public MapView(GameWorld gw) {
        super();
        this.gw = gw;
        gw.addObserver(this);
        this.getAllStyles().setBorder(Border.createLineBorder(5, 0xFF0000));
    }

    public void togglePause() {
        isPaused = !isPaused;
    }

    public Point getLastPointerClick() {
        return lastPointerClick;
    }
    
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        gw.setAbsolCords(getX(), getY());
        gw.setMapDimen(getWidth(), getHeight());

        Point pCmpRelPrnt = new Point((int) getX(), (int) getY());

        IIterator iterator = gw.getGameObjects().getIterator();

        while (iterator.hasNext()) {
            GameObject obj = (GameObject) iterator.getNext();
            obj.draw(g, pCmpRelPrnt);
        }
    }
    
    @Override
    public void pointerPressed(int x, int y) {

        int relX = x - getParent().getAbsoluteX();
        int relY = y - getParent().getAbsoluteY();
                
        lastPointerClick = new Point((relX - this.getX()), (relY - this.getY()));
        
		if(isPaused && gw.getClick() == true) {
			System.out.println("Game Paused");

			IIterator iterator = gw.getGameObjects().getIterator();
			
	        while (iterator.hasNext()) {
	            GameObject obj = (GameObject) iterator.getNext();
	            if (obj instanceof ISelectable && ((ISelectable) obj).isSelected()) {
	            	obj.setLocation((relX - getX()), (relY - getY()));
	            }
	        }
	        gw.setClick(false);
		}
        
    	if (!isPaused) return;

        Point pCmpRelPrnt = new Point(getX(), getY());
        Point pPtrRelPrnt = new Point(relX, relY);

        IIterator iterator = gw.getGameObjects().getIterator();

        while (iterator.hasNext()) {
            GameObject obj = (GameObject) iterator.getNext();
            if (obj instanceof ISelectable) {
                ISelectable selectable = (ISelectable) obj;
                selectable.setSelected(false);
                if (selectable.contains(pPtrRelPrnt, pCmpRelPrnt)) {
                    selectable.setSelected(true);
                }
            }
        }

        repaint();
    }

    @Override
    public void update(Observable o, Object arg) {
        this.repaint();
    }
}