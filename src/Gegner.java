import javax.swing.*;
import java.awt.*;

public class Gegner extends Entity {
    ///Variablen

    private int x;
    private int y;
    private Image image;
    private boolean zielErreicht;
    private int hp = 100;
    public String EnemyY = "pictures_map/yellow2.png";
    public boolean valid;
    public int walkFrame = 0;
    public int walkSpeed = 1;
    public boolean waagerechterWeg, senkrechterWeg,xLinks,yHoch,xRechts,yRunter;
    public int i,j;
    //Instanzen
    UI_gamepanel gp;
    path ph;


    public Gegner(UI_gamepanel gp) {    //Aussehen und Position gesetzt
        this.gp = gp;
        ph = new path(gp);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(EnemyY));
        image = ii.getImage();
        startPoint();
        valid = false;
        zielErreicht = false;
        solidArea = new Rectangle(30, 20, gp.w_TileSize - 60, gp.h_TileSize - 40);
        i = 0;
        j = 0;
        //Dynamische größe wird in eine variable gepackt
    }

    public void startPoint() {
        int stepH = gp.h_TileSize;
        x = 10;
        y = stepH * 2;

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
        ph.check(this, x, y);


        //2,7,4 geht danach geht nichts mehr
        switch (ph.tileNum1) {
            case 1:
                movePath(0, gp.h_TileSize);
                senkrechterWeg = true;
                waagerechterWeg = false;
                yHoch = true;
                break;
            case 2:
                movePath(gp.w_TileSize, 0);
                waagerechterWeg = true;
                senkrechterWeg = false;
                xRechts = true;
                break;
            case 3:
                //movePath(gp.w_TileSize, 0);
                waagerechterWeg = false;
                senkrechterWeg = false;
                break;
            case 4:
                movePath(gp.w_TileSize, gp.h_TileSize/2);
                waagerechterWeg = false;
                senkrechterWeg = false;
                xRechts = true;
                yHoch = true;
                break;
            case 5:
                movePath(0, gp.h_TileSize);
                waagerechterWeg = false;
                senkrechterWeg = false;

                break;
            case 6:
                //movePath(gp.w_TileSize, 0);
                waagerechterWeg = false;
                senkrechterWeg = false;
                break;
            case 7:
                if (waagerechterWeg) {
                    movePath(gp.w_TileSize, 0);
                    xRechts = true;

                } else if (senkrechterWeg) {
                    movePath(0, gp.h_TileSize);
                    yRunter = true;

                }
                break;
        }

    }


    public void movePath(int tileW, int tileH) {
        //System.out.println("Ich Fahre vorwärts");
        if (i <= tileW && xRechts) {

            if (walkFrame >= walkSpeed) {
                x = x + 1;
                System.out.println(x+"x");
                i++;
                walkFrame = 0;
            } else {
                walkFrame++;
                //System.out.println(walkFrame);
            }
        }else{
            i = 0;
            xRechts = false;
            System.out.println("xreset");
        }
        if (j <= tileH && yHoch) {

            if (walkFrame >= walkSpeed) {
                y = y - 1;
                System.out.println(y+"y");
                j++;
                walkFrame = 0;
            } else {
                walkFrame++;
                //System.out.println(walkFrame);
            }
        }else{
            j = 0;
            yHoch = false;
            System.out.println("yreset");
        }
    }


    //Zeichnet den Gegner auf dem GamePanel
    public void draw(Graphics2D g2) {
        g2.drawImage(image, getX(), getY(), gp.w_TileSize - 10, gp.h_TileSize - 10, null);
    }


//TODO Damage Funktion

}