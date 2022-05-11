import javax.swing.*;
import java.awt.*;

public class Gegner {
    ///Variablen

    private int x;
    private int y;
    private Image image;
    private boolean zielErreicht;
    private  int hp=100;
    public String EnemyY = "pictures_map/yellow2.png";
    public boolean valid;
    public  int walkFrame = 0;
    public  int walkSpeed = 5;

    //Instanzen
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
        int stepH = gp.h_TileSize;
        x = 10;
        y = stepH *2;

    }
    //Getter
    public int getX() { //Get function
        return x;
    }

    public int getY() {
        return y;
    }

    public Image getImage() {
        return image;
    }

    public void move() {//Fortbewegung mit Verzögerung

        if(walkFrame >= walkSpeed){
            x = x + 1;
            walkFrame = 0;
        }else {
            walkFrame++;
        }
    }



    //Zeichnet den Gegner auf dem GamePanel
    public void draw(Graphics2D g2) {
        g2.drawImage(image , getX(),getY(),gp.w_TileSize -10,gp.h_TileSize -10, null);
    }


//TODO Damage Funktion

}