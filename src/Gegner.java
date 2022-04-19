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

    public Gegner() {    //Set skin and enemy position

        ImageIcon ii = new ImageIcon(this.getClass().getResource(EnemyY));
        image = ii.getImage();
        x = 10;
        y = 180;
        dx= 300;
        dy = 300;
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
            x += dx;
            y += dy;

    }
    
    public void draw(Graphics2D g2) {

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

