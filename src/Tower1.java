/*Tower1, die Ramme, ID: 0*/

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static java.lang.Math.round;

public class Tower1{

    Point[] start;
    boolean dragValid;
    Point prevPt;
    UI_gamepanel gp;
    Tower tw;
    public Tower1(UI_gamepanel gp,Tower tw) {
        this.gp = gp;
        this.tw = tw;
        start = new Point[2];
        setpoint();
    }

    public void Tower1(int kosten, int schaden)
    {
        tw.kosten = 10;
        tw.schaden = 5;
    }

    public void radius(int radius,Graphics2D g2,Point imageCorner){
        Color c1 = new Color(93, 93, 93, 61);
        g2.setColor(c1);
        if (dragValid&& gp.grid && !tw.collisionOn){
            g2.fillOval((int) imageCorner.getX()+gp.w_TileSize /2-radius/2, (int) imageCorner.getY()+gp.h_TileSize /2-radius/2,radius,radius);
        }

    }

    public void redradius(Graphics2D g2,Point imageCorner){
        Color c2 = new Color(232, 25, 25, 61);
        g2.setColor(c2);
        if (dragValid && tw.collisionOn){
            g2.fillOval((int) imageCorner.getX()+gp.w_TileSize /2-50, (int) imageCorner.getY()+gp.h_TileSize /2-50,100,100);
        }

    }

    public void setpoint(){
        for(int i = 0;i < start.length;i++){
        start[i] = new Point(gp.screen + (int) round(tw.breite * 13), (int) round(gp.h_TileSize * 1.2) + 50);
        }
    }

    public void setDragValid(MouseEvent e, Point imageCorner){
        dragValid = (e.getPoint().getX() > imageCorner.getX()) &&
                (e.getPoint().getX() < (imageCorner.getX() + (int) round(gp.w_TileSize * 1.0))) &&
                (e.getPoint().getY() > imageCorner.getY()) &&
                (e.getPoint().getY() < (imageCorner.getY() + (int) round(gp.w_TileSize * 1.0)));
    }
    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            prevPt = e.getPoint();
            //Damit nur wenn auf das bild geklickt wird sich das bild bewegt
            setDragValid(e,start[0]);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            gp.grid = false;
            //checkt, ob das bild richtig sitz
            tw.check(start[0],start[1],dragValid);
        }
    }
    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            Point currentPt = e.getPoint();
            //Ermöglicht das Bewegen der Türme
            tw.drag(dragValid,currentPt,start[0],prevPt);

            prevPt = currentPt;
        }
    }
}
