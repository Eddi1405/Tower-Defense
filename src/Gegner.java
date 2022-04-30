import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

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
    UI_gamepanel gp;

    //Timer timer = new Timer(5,this);
    public Gegner(UI_gamepanel gp) {    //Set skin and enemy position
        this.gp = gp;
        ImageIcon ii = new ImageIcon(this.getClass().getResource(EnemyY));
        image = ii.getImage();
        startPoint();
        dx= 1;
        dy = 1;
        //Dynamische größe wird in eine variable gepackt
    }

    public void startPoint(){
        int stepH = gp.h_tileSize;
        x = 10;
        y = stepH *2;

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
/*
        if(x < 0){
            dx = 0;
            x = 0;
        }
        if(x > gp.w_tileSize){
            dx = 0;
            x = 10;
        }

        if(y < 0){
            dy = 0;
            x = 0;
        }
        if(y > gp.h_tileSize){
            dy = 0;
            y = gp.h_tileSize / 2;
        }

        x = x + dy;
        System.out.println(x);
        y = y + dy;*/

        x = x + 1;
    }



    public void draw(Graphics2D g2) {
        g2.drawImage(image , getX(),getY(),gp.w_tileSize-10,gp.h_tileSize-10, null);
    }

}

