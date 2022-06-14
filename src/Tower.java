import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

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
    Tile[] towerPic;
    Tower1[] tower1 = new Tower1[2];
    Tower2[] tower2 = new Tower2[20];
    Tower3[] tower3 = new Tower3[20];

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






    public Tower(UI_gamepanel gp) {
        this.gp = gp;
        breite = (gp.width - gp.screen) / 100;
        gp.ueberT1 = tower1.length;
        gp.ueberT2 = tower2.length;
        gp.ueberT3 = tower3.length;
        towerPic = new Tile[3];
        solidArea = new Rectangle(30,20,gp.w_TileSize -60,gp.h_TileSize -40);
        newTower();
    }
    public void getImageTower() {

        try {
            //Türme
            towerPic[0] = new Tile();
            towerPic[0].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Tower1.png"));
            towerPic[1] = new Tile();
            towerPic[1].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Tower2.png"));
            towerPic[2] = new Tile();
            towerPic[2].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Tower3.png"));

        } catch (IOException e) {
            System.out.println("bild fehler");
            e.printStackTrace();
        }
    }

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
            //imageCorner.setLocation(old.getX(),old.getY());;

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

    //nur das erzeugen was benötigt wird
    public void newTower(){
        for(int i = 0;i<tower1.length;i++){
            tower1[i] = new Tower1(gp,this);
            tower1[i].nr = i;
        }
        for(int i = 0;i<tower2.length;i++){
            tower2[i] = new Tower2(gp,this);
            tower2[i].nr = i;
        }
        for(int i = 0;i<tower2.length;i++){
            tower3[i] = new Tower3(gp,this);
            tower3[i].nr = i;
        }
    }
    public void draw(Graphics2D g2){


        for(int i = 0;i<gp.anzT1;i++){

            if(tower1[i] != null){
                tower1[i].drawTower(g2);
                if(tower1[i].valid){
                    tower1[i].radius(300,g2);
                    tower1[i].redradius(g2);
                }
            }
        }
        for(int i = 0;i<gp.anzT2;i++){
            if(tower2[i] != null){
                tower2[i].drawTower(g2);
                if(tower2[i].valid){
                    tower2[i].radius(500,g2);
                    tower2[i].redradius(g2);
                }
            }
        }
        for(int i = 0;i<gp.anzT3;i++){
            if(tower3[i] != null){
                tower3[i].drawTower(g2);
                if(tower3[i].valid){
                    tower3[i].radius(350,g2);
                    tower3[i].redradius(g2);
                }
            }

        }
    }
}
