import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Gegner {
    //static Random randomGenerator = new Random();
    private static int x;
    private int y;
    private Image image;
    private int dx;
    private int dy;
    private int i;
    private final int strength=10;
    private static int hp=100;
    public String EnemyY = "pictures_map/yellow2.png";
    UI_gamepanel gp;
    static loop lp = new loop();
    //Timer timer = new Timer(5,this);
    public Gegner(UI_gamepanel gp) {    //Set skin and enemy position
        this.gp = gp;
        ImageIcon ii = new ImageIcon(this.getClass().getResource(EnemyY));
        image = ii.getImage();
        startPoint();
        dx= 1;
        dy = 1;
        lp.start(Gegner::move,0,100);
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

    public static void move() {    //Movement with delay
        //TODO komplett rewroken, ohne sleep und ohne Kopplung an Andere Entities.
        /**
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
        y = y + dy;*/


            x = x+5;
        if (x == 50){

            lp.stop();
        }
        System.out.println(x);
    }



    public void draw(Graphics2D g2) {
        g2.drawImage(image , getX(),getY(),gp.w_tileSize-10,gp.h_tileSize-10, null);
    }
//TODO Damage Funktion
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

