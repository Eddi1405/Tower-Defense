/*Tower1, die Ramme, ID: 0*/

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static java.lang.Math.round;


public class Tower3 {
    int nr;
    Point[] pointTower;
    boolean dragValid,valid;
    Point prevPt;
    UI_gamepanel gp;
    Tower tw;
    public Tower3(UI_gamepanel gp, Tower tw) {
        this.gp = gp;
        this.tw = tw;
        pointTower = new Point[2];
        setpoint();
        Tower3.ClickListener clickListener = new Tower3.ClickListener();
        Tower3.DragListener dragListener = new Tower3.DragListener();
        gp.addMouseListener(clickListener);
        gp.addMouseMotionListener(dragListener);
    }

    public void Tower3(int kosten, int schaden)
    {
        tw.kosten = 10;
        tw.schaden = 5;
    }

    public void radius(int radius,Graphics2D g2){
        Color c1 = new Color(93, 93, 93, 61);
        g2.setColor(c1);
        if (dragValid&& gp.grid && !tw.collisionOn){
            g2.fillOval((int) pointTower[0].getX()+gp.w_TileSize /2-radius/2, (int) pointTower[0].getY()+gp.h_TileSize /2-radius/2,radius,radius);
        }

    }

    public void redradius(Graphics2D g2){
        Color c2 = new Color(232, 25, 25, 61);
        g2.setColor(c2);
        if (dragValid && tw.collisionOn){
            g2.fillOval((int) pointTower[0].getX()+gp.w_TileSize /2-50, (int) pointTower[0].getY()+gp.h_TileSize /2-50,100,100);
        }

    }

    public void drawTower(Graphics2D g2){
        g2.drawImage(tw.towerPic[2].image,(int) pointTower[0].getX() , (int) pointTower[0].getY(), (int) round(gp.w_TileSize * 1.0), (int) round(gp.h_TileSize * 1.0), null);
    }



    public void setpoint(){
        for(int i = 0;i < pointTower.length;i++){
            pointTower[i] = new Point(gp.screen + round(tw.breite * 13)+(int) round(gp.w_TileSize * 1.2) * 2, (int) round(gp.h_TileSize * 1.2) + 50);
        }
    }

    public void setDragValid(MouseEvent e){
        dragValid = (e.getPoint().getX() > pointTower[0].getX()) &&
                (e.getPoint().getX() < (pointTower[0].getX() + (int) round(gp.w_TileSize * 1.0))) &&
                (e.getPoint().getY() > pointTower[0].getY()) &&
                (e.getPoint().getY() < (pointTower[0].getY() + (int) round(gp.w_TileSize * 1.0)));
    }
    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            prevPt = e.getPoint();
            //Damit nur, wenn auf das bild geklickt wird sich das bild bewegt
            setDragValid(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            gp.grid = false;
            //checkt, ob das bild richtig sitz
            tw.check(pointTower[0],pointTower[1],dragValid);
            valid = false;
        }
    }
    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            Point currentPt = e.getPoint();
            //Erm??glicht das Bewegen der T??rme
            tw.drag(dragValid,currentPt,pointTower[0],prevPt);
            valid = true;
            gp.moved = true;
            prevPt = currentPt;
        }
    }



}
