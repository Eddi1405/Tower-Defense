import java.awt.*;

/*Haupttower Klasse die weitergegeben wird*/
/*https://www.youtube.com/watch?v=zoScABLWwUY&t=251s
* https://youtu.be/69R6IoT2ZoA?t=1754
* https://youtu.be/ZQg8Se0Ywiw?t=2362*/
public class Tower {
public int angreifenGegner = 0;
public boolean angriff = false;

//Angrifflogik hierhin
//Checkt ob Gegner noch in Angriffsrange ist oder nicht
public void rangeCheck(){
    angriff = false;
//Hier Range Circle abfragen ob Gegner in Range
angriff = true;
}

//Wenn Angriff = True, draw Projektil
public void kampf(Graphics g){
    if (angriff){
        //g.drawImage("Projektil")
    }


}

    //public String TowerImage = "";
    //Maximale Tower die gekauft erstellt werden können sind "20"
    //Tower ID zuweisen
    //TODO Static entfernen
    public final Tower[] UI_shop = new Tower[20];
    public static  final Tower Tower1 = new Tower1(0);
    public static  final Tower Tower2 = new Tower2(1);
    public static  final Tower Tower3 = new Tower3(2);

    //Nummerieren von 1-X von links nach rechts
    /*123
    * 456
    * 789
    * */

    /*Bilder einlesen der Tower hierrein auslagern*/
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



    public Tower(int id)
    {
       // towerlist[id] = this.id = id;
    }



}
