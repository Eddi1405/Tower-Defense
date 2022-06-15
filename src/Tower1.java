/*Tower1, die Ramme, ID: 0*/

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static java.lang.Math.round;

public class Tower1{
    int nr = 100;
    Point[] pointTower;
    boolean dragValid,set,spawn;
    Point prevPt;
    UI_gamepanel gp;
    Tower tw;
    public boolean valid;
    public Tower1(UI_gamepanel gp,Tower tw) {
        this.gp = gp;
        this.tw = tw;
        pointTower = new Point[2];
        setpoint();
        tw.getImageTower();
        Tower1.ClickListener clickListener = new Tower1.ClickListener();
        Tower1.DragListener dragListener = new Tower1.DragListener();
        gp.addMouseListener(clickListener);
        gp.addMouseMotionListener(dragListener);
    }

    public void Tower1(int kosten, int schaden)
    {
        tw.kosten = 10;
        tw.schaden = 5;
    }

    // Zeichnet das Turmmenü.
    public void drawMenu(Graphics2D g2){
        if (gp.ui.system.contains("nix") || gp.ui.system.contains("nux")) {
            if (dragValid) {
                g2.drawImage(gp.ui.sidebar[2].image, (int) pointTower[0].getX() - (int) tw.breite * 4, (int) pointTower[0].getY() - (int) tw.breite * 4, (int) round(gp.w_TileSize * 0.5), (int) round(gp.h_TileSize * 0.5), null);
                g2.drawImage(gp.ui.sidebar[3].image, (int) pointTower[0].getX() + (int) tw.breite * 19, (int) pointTower[0].getY() - (int) tw.breite * 4, (int) round(gp.w_TileSize * 0.5), (int) round(gp.h_TileSize * 0.5), null);
            }
        }
        else{
            if (dragValid) {
                g2.drawImage(gp.ui.sidebar[2].image, (int) pointTower[0].getX() - (int) tw.breite * 4, (int) pointTower[0].getY() - (int) tw.breite * 4, (int) round(gp.w_TileSize * 0.5), (int) round(gp.h_TileSize * 0.5), null);
                g2.drawImage(gp.ui.sidebar[3].image, (int) pointTower[0].getX() + (int) tw.breite * 15, (int) pointTower[0].getY() - (int) tw.breite * 4, (int) round(gp.w_TileSize * 0.5), (int) round(gp.h_TileSize * 0.5), null);
            }
        }
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
        g2.drawImage(tw.towerPic[0].image,(int) pointTower[0].getX() , (int) pointTower[0].getY(), (int) round(gp.w_TileSize * 1.0), (int) round(gp.h_TileSize * 1.0), null);
    }

    public void setpoint(){
        for(int i = 0;i < pointTower.length;i++){
        pointTower[i] = new Point(gp.screen + round(tw.breite * 13), (int) round(gp.h_TileSize * 1.2) + 50);
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
            if(dragValid){
                gp.grid = false;
                //checkt, ob das bild richtig sitz
                tw.check(pointTower[0],pointTower[1],dragValid);
                valid = false;
                set = true;
            }
        }
    }
    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            if(dragValid) {
                Point currentPt = e.getPoint();
                //Ermöglicht das Bewegen der Türme
                //System.out.println(nr+"<"+set);
                tw.drag(dragValid, currentPt, pointTower[0], prevPt);
                valid = true;
                gp.moved = true;
                prevPt = currentPt;
            }
        }
    }
}
