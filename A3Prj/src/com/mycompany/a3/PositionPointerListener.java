package com.mycompany.a3;

import com.codename1.ui.geom.Point;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;

/**
 * One‐time listener that waits for the next click on MapView,
 * moves the given Fixed object there, repaints, and unregisters itself.
 */
public class PositionPointerListener implements ActionListener {
    private MapView mv;
    private Fixed   target;

    public PositionPointerListener(MapView mv, Fixed target) {
        this.mv     = mv;
        this.target = target;
    }

    @Override
    public void actionPerformed(ActionEvent evt) {
        // evt.getX()/getY() are the raw container coords of the click
        int x = evt.getX();
        int y = evt.getY();

        // Convert to MapView/game‐world coords:
        // 1) undo parent offset
        // 2) flip Y (screen down → game up) 133Spring25Asst3.pdf](file-service://file-4gmoBjD1hFmsYUhtf1E4ry)
        int localX = x - mv.getParent().getAbsoluteX();
        int localY = mv.getHeight() - (y - mv.getParent().getAbsoluteY());
        Point newLoc = new Point(localX, localY);

        // Move it, repaint, and remove this listener so it only fires once
        target.setLocation(newLoc.getX(), newLoc.getY());
        mv.repaint();
        mv.removePointerPressedListener(this);
    }
}