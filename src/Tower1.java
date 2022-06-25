/*Tower1, die Ramme, ID: 0*/

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import static java.lang.Math.round;

public class Tower1{
    int nr = 100;
    int weg = 50;
    Point[] pointTower;
    boolean dragValid,set,dragValidsell,dragValidupgrade;
    Point prevPt;
    UI_gamepanel gp;
    Tower tw;
    Tower1.ClickListener clickListener;
    Tower1.DragListener dragListener;
    public boolean valid,check,move,upgrade;
    public Tower1(UI_gamepanel gp,Tower tw) {
        this.gp = gp;
        this.tw = tw;
        pointTower = new Point[2];
        setpoint();
        tw.getImageTower();
        move = true;
        clickListener = new Tower1.ClickListener();
        dragListener = new Tower1.DragListener();
        gp.addMouseListener(clickListener);
        gp.addMouseMotionListener(dragListener);
    }

    public void Tower11(int kosten, int schaden)
    {
        tw.kosten = 10;
        tw.schaden = 5;
    }

    public void del(){
        clickListener = null;
        dragListener = null;
    }
    // Zeichnet das Turmmenü.
    public void drawMenu(Graphics2D g2){
        if (gp.ui.system.contains("nix") || gp.ui.system.contains("nux")) {
            if (dragValid) {
                //Verkaufen
                g2.drawImage(gp.ui.sidebar[2].image, (int) pointTower[0].getX() - (int) tw.breite * 4, (int) pointTower[0].getY() - (int) tw.breite * 4, (int) round(gp.w_TileSize * 0.5), (int) round(gp.h_TileSize * 0.5), null);
                //upgrade
                g2.drawImage(gp.ui.sidebar[3].image, (int) pointTower[0].getX() + (int) tw.breite * 19 +1, (int) pointTower[0].getY() - (int) tw.breite * 4, (int) round(gp.w_TileSize * 0.5), (int) round(gp.h_TileSize * 0.5), null);
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
        if (dragValid&& gp.grid && !tw.collisionOn || set && dragValid){
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
    public void upgradeTower(Graphics2D g2){
        if(upgrade){
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, (int) tw.breite * 8));
           if(weg == 0){
              weg = 50;
              upgrade = false;
           }else{
               Color t = new Color(0,0,0,weg*5);
               g2.setColor(t);
               g2.drawString("Upgrade",(int)pointTower[0].getX()+5,(int)pointTower[0].getY()-5);
               weg = weg - 2;
           }
        }
        g2.setColor(Color.BLACK);
    }
    public void drawTower(Graphics2D g2){
        g2.drawImage(tw.towerPic[0].image,(int) pointTower[0].getX() , (int) pointTower[0].getY(), (int) round(gp.w_TileSize * 1.0), (int) round(gp.h_TileSize * 1.0), null);
        //g2.drawRect((int)pointTower[0].getX(),(int)pointTower[0].getY(),(int) tw.breite * 6,(int) tw.breite * 6);
        //g2.drawRect((int)pointTower[0].getX()+ (int) tw.breite * 23+2,(int)pointTower[0].getY(),(int) tw.breite * 6,(int) tw.breite * 6);
        upgradeTower(g2);

    }

    public void setpoint(){
        for(int i = 0;i < pointTower.length;i++){
        pointTower[i] = new Point(gp.screen + round(tw.breite * 13), (int) round(gp.h_TileSize * 1.2) + 50);
        }
    }
    public void setDragValidsell(MouseEvent e){
        dragValidsell = (e.getPoint().getX() > pointTower[0].getX()) &&
                (e.getPoint().getX() < (pointTower[0].getX() + (int) tw.breite * 6)) &&
                (e.getPoint().getY() > pointTower[0].getY()) &&
                (e.getPoint().getY() < (pointTower[0].getY() + (int) tw.breite * 6));
    }
    public void setDragValidupgrade(MouseEvent e){
        dragValidupgrade = (e.getPoint().getX() > pointTower[0].getX()+ (int) tw.breite * 23+2) &&
                (e.getPoint().getX() < (pointTower[0].getX() + (int) tw.breite * 6+ (int) tw.breite * 23+2)) &&
                (e.getPoint().getY() > pointTower[0].getY()) &&
                (e.getPoint().getY() < (pointTower[0].getY() + (int) tw.breite * 6));
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
                setDragValidsell(e);
                setDragValidupgrade(e);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            if(dragValid && nr < gp.anzT1 && move){
                gp.grid = false;
                //checkt, ob das bild richtig sitz
                if(tw.check(pointTower[0],pointTower[1],dragValid)){
                    check = true;
                    tw.check = false;
                }else{
                    check = false;
                    move = false;
                }
                valid = false;
                set = true;
                gp.ueberT1--;
            }
            if(dragValidsell){
                check = true;
                tw.gp.cc.delPosition();
                gp.ueberT1++;
                set = false;
            }
            if(dragValidupgrade){
                upgrade = true;
            }
            if(dragValid && !dragValidsell && !dragValidupgrade){

                System.out.println(nr);
            }
        }
    }
    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            if(dragValid && nr < gp.anzT1 && move) {
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
