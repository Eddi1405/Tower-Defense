/*Haupttower Klasse die weitergegeben wird*/
/*https://www.youtube.com/watch?v=zoScABLWwUY&t=251s*/
public class Tower {

    //public String TowerImage = "";
    //Maximale Tower die gekauft erstellt werden können sind "20"
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
    //public static final Tower1 = new Tower1(0).ImageIO.read(getClass().getResourceAsStream("/pictures_map/Tower1.png"));
    //public static final Tower1 = new Tower1(0).ImageIO.read(getClass().getResourceAsStream("/pictures_map/Tower2.png"));
    //public static final Tower1 = new Tower1(0).ImageIO.read(getClass().getResourceAsStream("/pictures_map/Tower3.png"));

    public int id;
    public Tower(int id)
    {
       // towerlist[id] = this.id = id;
    }



}
