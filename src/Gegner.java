import javax.swing.*;
import java.awt.*;

public class Gegner {
    //static Random randomGenerator = new Random();
    private int x;
    private int y;
    private Image image;
    private int dx;
    private int dy;
    private int i;
    private final int strength=10;
    private static int hp=100;
    public String EnemyY = "pictures_map/yellow2.png";
    //für die dynamische größe der Gegner.wird in gamepanel übergeben.
    private int wtilesize;
    private int htilesize;
    //Timer timer = new Timer(5,this);
    public Gegner(int w_tilesize,int h_tilesize) {    //Set skin and enemy position

        ImageIcon ii = new ImageIcon(this.getClass().getResource(EnemyY));
        image = ii.getImage();
        x = 10;
        y = 180;
        dx= 1;
        dy = 1;
        //Dynamische größe wird in eine variable gepackt
        wtilesize = w_tilesize;
        htilesize = h_tilesize;
    }

    public int getX() { //Get function
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void move() {    //Movement with delay

        if(x < 0){
            dx = 0;
            x = 0;
        }
        if(x > wtilesize){
            dx = 0;
            x = 500;
        }

        if(y < 0){
            dy = 0;
            x = 0;
        }
        if(y > htilesize){
            dy = 0;
            y = 500;
        }

        x = x + dy;
        System.out.println(x);
        y = y + dy;
    }
    
    public void draw(Graphics2D g2) {
        //TODO dynamische größe bei den Gegnern
        g2.drawImage(image , getX(),getY(),80,80, null);
    }

 /*
    public void attacked (){    //Function that is activated when enemy's attacked
        hp -=Cazzillo.getS();
        if (hp<=0)
            death();
    }


    public void death (){
        x=randomGenerator.nextInt(1024);
        y=randomGenerator.nextInt(700);
        hp=100;
    }
    */
}

