import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.time.temporal.ValueRange;

import static java.lang.Math.round;

/*Haupttower Klasse die weitergegeben wird*/
/*https://www.youtube.com/watch?v=zoScABLWwUY&t=251s
 * https://youtu.be/69R6IoT2ZoA?t=1754
 * https://youtu.be/ZQg8Se0Ywiw?t=2362*/
public class Tower extends Entity {
    UI_gamepanel gp;
    public int kosten;
    public int schaden;
    public int breite;
    public int angreifenGegner = 0;
    //Angriff cycle
    public boolean angriff = false;
    //Projektil spawn cycle (Line)
    public boolean feuern = false;


    //Angriff logik hierhin
//prüft ob Gegner noch in Angriffs range ist oder nicht
    public void rangeCheck() {

        angriff = false;
//Hier Range Circle abfragen ob Gegner in Range

        /* if(hier abgleich von X Y){



        }*/
        System.out.println("IN RANGE");
        angriff = true;
    }




    //Wenn Angriff = True, draw Projektil
    public void kampf(Graphics  g) {
        if (angriff) {

        }

        if(feuern) {
            //g.setColor(new Color(255,255,0));
            //g.drawLine();
        }

    }

    //public String TowerImage = "";
    //Maximale Tower die gekauft erstellt werden können sind "20"
    //Tower ID zuweisen
    //TODO Static entfernen
    //public final Tower[] UI_shop = new Tower[20];

    //public final Tower tower2 = new Tower2(gp);
    //public final Tower tower3 = new Tower3(gp);

    //Nummerieren von 1-X von links nach rechts
    /*123
     * 456
     * 789
     * */

    /*Bilder einlesen der Tower hier rein auslagern*/
    public void getImageTower() {
/*
        try {
            //Türme
            tower[0] = new Tile();
            tower[0].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Tower1.png"));
            tower[1] = new Tile();
            tower[1].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Tower2.png"));
            tower[2] = new Tile();
            tower[2].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Tower3.png"));

        } catch (IOException e) {
            System.out.println("bild fehler");
            e.printStackTrace();
        } */
    }





    public Tower(UI_gamepanel gp) {
        this.gp = gp;
        breite = (gp.width - gp.screen) / 100;
        Tower1 tower1 = new Tower1(gp,this);
    }

/*

    public void radius(int radius,Graphics2D g2,Point imageCorner){
        Color c1 = new Color(93, 93, 93, 61);
        g2.setColor(c1);
        if (dragValid[0] && gp.grid && !collisionOn){
            g2.fillOval((int) imageCorner.getX()+gp.w_TileSize /2-radius/2, (int) imageCorner.getY()+gp.h_TileSize /2-radius/2,radius,radius);
        }

    }

    public void redradius(Graphics2D g2,Point imageCorner){
        Color c2 = new Color(232, 25, 25, 61);
        g2.setColor(c2);
        if (dragValid[0] && collisionOn){
            g2.fillOval((int) imageCorner.getX()+gp.w_TileSize /2-50, (int) imageCorner.getY()+gp.h_TileSize /2-50,100,100);
        }

    }
*/
    public void check(Point imageCorner,Point old,boolean dragValid){
        collisionOn = false;
        gp.cc.check(this,imageCorner);
        //Aktuelle x Position
        int imx =(int)imageCorner.getX();
        //Aktuelle y Position
        int imy =(int)imageCorner.getY();
        double mx = imx % gp.w_TileSize;
        int my = imy % gp.h_TileSize;

        if(imx < gp.screen-50 && imy < gp.height && !collisionOn) {
            if(mx < gp.w_TileSize /2 && my < gp.h_TileSize /2){
                //Koordinaten
                imageCorner.setLocation(imx - mx, imy - my);
                //Position im Grid
                gp.cc.addPosition((int)(imx-mx)/gp.w_TileSize,(int)(imy-my)/gp.h_TileSize);
                //System.out.println((int)(imx-mx)/gp.w_tileSize+"-|-"+(int)(imy-my)/gp.h_tileSize);
            } else if (mx > gp.w_TileSize /2 && my < gp.h_TileSize /2) {
                imageCorner.setLocation(imx + (gp.w_TileSize -mx), imy - my);
                gp.cc.addPosition((int)(imx + (gp.w_TileSize -mx))/gp.w_TileSize,(int)(imy-my)/gp.h_TileSize);
                //System.out.println((int)(imx + (gp.w_tileSize-mx))/gp.w_tileSize+"+|-"+(int)(imy-my)/gp.h_tileSize);
            } else if (mx < gp.w_TileSize /2 && my > gp.h_TileSize /2) {
                imageCorner.setLocation(imx - mx, imy + (gp.h_TileSize -my));
                gp.cc.addPosition((int)(imx-mx)/gp.w_TileSize,(int)(imy + (gp.h_TileSize -my))/gp.h_TileSize);
                //System.out.println((int)(imx-mx)/gp.w_tileSize+"-|+"+(int)(imy + (gp.h_tileSize-my))/gp.h_tileSize);
            }else {
                imageCorner.setLocation(imx + (gp.w_TileSize -mx), imy + (gp.h_TileSize -my));
                gp.cc.addPosition((int)((imx + (gp.w_TileSize -mx))/gp.w_TileSize),(int)(imy + (gp.h_TileSize -my))/gp.h_TileSize);
                //System.out.println((int)((imx + (gp.w_tileSize-mx))/gp.w_tileSize)+"+|+"+(int)(imy + (gp.h_tileSize-my))/gp.h_tileSize);
            }
        }else if(collisionOn && dragValid){
            imageCorner.setLocation(old.getX(),old.getY());;
        }
    }
    public void drag(boolean dragValid,Point currentPt,Point imageCorner,Point prevPt){

        //Checkt für jedes Bild, ob es bewegt werden darf
        if (dragValid) {
            imageCorner.translate(
                    (int) (currentPt.getX() - prevPt.getX()),
                    (int) (currentPt.getY() - prevPt.getY())
            );
            collisionOn = false;
            gp.cc.check(this,imageCorner);

            gp.grid = true;
        }

    }
/*
    public void setDragValid(MouseEvent e, int index ,Point imageCorner){
        dragValid[index] = (e.getPoint().getX() > imageCorner.getX()) &&
                (e.getPoint().getX() < (imageCorner.getX() + (int) round(gp.w_TileSize * 1.0))) &&
                (e.getPoint().getY() > imageCorner.getY()) &&
                (e.getPoint().getY() < (imageCorner.getY() + (int) round(gp.w_TileSize * 1.0)));
    }
    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            prevPt = e.getPoint();
            //Damit nur wenn auf das bild geklickt wird sich das bild bewegt
            setDragValid(e,0);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            gp.grid = false;
            //checkt, ob das bild richtig sitz
            check(0);
            pressed = true;
        }
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            Point currentPt = e.getPoint();
            //Ermöglicht das Bewegen der Türme
            drag(0,currentPt);

            prevPt = currentPt;
        }
    }*/
}
