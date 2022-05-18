import java.awt.*;
import java.time.temporal.ValueRange;

/*Haupttower Klasse die weitergegeben wird*/
/*https://www.youtube.com/watch?v=zoScABLWwUY&t=251s
 * https://youtu.be/69R6IoT2ZoA?t=1754
 * https://youtu.be/ZQg8Se0Ywiw?t=2362*/
public class Tower /*extends UI_gamepanel*/ {
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
    public final Tower[] UI_shop = new Tower[20];
    public final Tower tower1 = new Tower1(gp);
    public  final Tower tower2 = new Tower2(gp);
    public  final Tower tower3 = new Tower3(gp);

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
    }


}
