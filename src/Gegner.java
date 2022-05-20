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
    public boolean waagerechterWeg, senkrechterWeg,xLinks,yHoch,xRechts,tile;
    public int i,j,oldTile;
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
        tile = false;
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


        //1,2,3,4,6,7 geht danach geht nichts mehr. funktioniert auch nur halb. An den Ecken Beschleunigt der Baloon die ganze Zeit und einmal bounct der him und her.
        //problem: der Baloon fährt nicht richtig um die ecken es wird immer i auf null gesetzt. dadurch fährt der baloon zu weit
        switch (ph.tileNum1) {
            case 1:
                moveY(1);
                senkrechterWeg = true;
                break;
            case 2:
                moveX(1,2);
                waagerechterWeg = true;
                break;
            case 3:
                yHoch = true;
                xLinks = true;
                moveY(2);
                moveX(2,3);
                break;
            case 4:
                xLinks = false;
                yHoch = true;
                moveX(2,4);
                moveY(2);
                break;
            case 5:
                moveX(2,5);
                moveY(2);
                break;
            case 6:
                yHoch = false;
                xLinks = true;
                moveX(2,6);
                moveY(2);
                break;
            case 7:
                if(senkrechterWeg){
                    moveY(1);
                } else if (waagerechterWeg) {
                    moveX(1,7);
                }
                break;
        }

    }

    //OLD, für jedes tile wurde eine eigene Klasser erstellt. Damit der switch sauberer aus sieht und das programm übersichtlicher ist.
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
    public void moveX(int teil,int newTile){
        if (i <= gp.w_TileSize/teil) {
            if (walkFrame >= walkSpeed) {
                if(xLinks){
                    x = x - 1;
                }
                else{
                    x = x + 1;
                }
                i++;
                walkFrame = 0;
            } else {
                walkFrame++;
            }
        }else{
                i = 0;
                System.out.println("new");
        }
    }
    public void moveY(int teil){
        if (i <= gp.h_TileSize/teil) {
            if (walkFrame >= walkSpeed) {
                if(yHoch){
                    y = y - 1;
                }else{
                    y = y + 1;
                }
                i++;
                walkFrame = 0;
            } else {
                walkFrame++;
            }
        }else{
            i = 0;
        }
    }

    //Zeichnet den Gegner auf dem GamePanel
    public void draw(Graphics2D g2) {
        g2.drawImage(image, getX(), getY(), gp.w_TileSize - 10, gp.h_TileSize - 10, null);
    }


//TODO Damage Funktion

}