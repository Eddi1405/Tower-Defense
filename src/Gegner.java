import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Gegner {
    private int x;
    private int y;
    private Image image;
    private boolean zielErreicht;
    private  int hp=100;
    public String EnemyY = "pictures_map/yellow2.png";
    public boolean valid;
    public  int walkFrame = 0;
    public  int walkSpeed = 5;

    UI_gamepanel gp;



    public Gegner(UI_gamepanel gp) {    //Aussehen und Position gesetzt
        this.gp = gp;
        ImageIcon ii = new ImageIcon(this.getClass().getResource(EnemyY));
        image = ii.getImage();
        startPoint();
        valid = false;
        zielErreicht = false;
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

    public void move() {//Movement with delay


        if(walkFrame >= walkSpeed){
            x = x + 1;
            walkFrame = 0;
        }else {
            walkFrame++;
        }
    }




    public void draw(Graphics2D g2) {
        g2.drawImage(image , getX(),getY(),gp.w_tileSize-10,gp.h_tileSize-10, null);
    }


//TODO Damage Funktion

}