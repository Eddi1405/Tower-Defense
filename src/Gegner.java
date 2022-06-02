import javax.swing.*;
import java.awt.*;

public class Gegner extends Entity implements Runnable {
    ///Variablen

    private int x;
    private int y;
    private Image image;
    private boolean zielErreicht;
    private int hp = 100;
    public String EnemyY = "pictures_map/yellow2.png";
    public boolean valid;
    public int walkFrame = 0;
    public int walkSpeed = 40;
    public boolean waagerechterWeg,xLinks,yHoch,tile,weiter;
    public int i,j,oldTileY,oldTileX,ieck;
    //Instanzen
    UI_gamepanel gp;
    path ph;
    Thread gegnerThread;


    public Gegner(UI_gamepanel gp) {    //Aussehen und Position gesetzt
        this.gp = gp;
        ph = new path(gp);
        ImageIcon ii = new ImageIcon(this.getClass().getResource(EnemyY));
        image = ii.getImage();
        startPoint();
        valid = false;
        zielErreicht = false;
        solidArea = new Rectangle(15,5,gp.w_TileSize - 50, gp.h_TileSize - 20);
        i = 0;
        j = 0;
        ieck = 0;
        tile = false;
        //Dynamische größe wird in eine variable gepackt

    }

    public void startGegnerThread() {
        if(gegnerThread == null) {
            gegnerThread = new Thread(this);
            gegnerThread.start();
        }
    }
    @Override
    public void run() {
        while (gegnerThread != null) {
            try {
                gegnerThread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            move();
        }
    }

    public void startPoint() {
        int stepH = gp.h_TileSize;
        x = -35;
        y = stepH * 2;
        oldTileX = 0;
        oldTileY = 2;

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
        System.out.println(ph.mitte);
        //System.out.println(oldTileX+"ö"+ph.mitteX+"+"+ph.mitteY+"ö"+oldTileY);

        if(ph.mitte == 1 || ph.mitte == 2 || ph.mitte == 7){
            ieck = 0;
        }

        switch (ph.mitte) {
            case 1:
                moveY();
                waagerechterWeg = true;
                //System.out.println("1");
                break;
            case 2:
                moveX();
                waagerechterWeg = false;
                //System.out.println("2");
                break;
            case 3:
                yHoch = true;
                xLinks = true;
                yThenx();
                //System.out.println("3");
                break;
            case 4:
                yHoch = true;
                xLinks = false;
                xTheny();
                //System.out.println("4");
                break;
            case 5:
                yHoch = true;
                xLinks = true;
                xTheny();
                //System.out.println("5");
                break;
            case 6:
                yHoch = true;
                xLinks = false;
                yThenx();
                //System.out.println("6");
                break;
            case 7:
                if(waagerechterWeg){
                    moveY();
                }else{
                    moveX();
                }
                //System.out.println("7");
                break;
            case 9:
                yHoch = false;
                xLinks = false;
                xTheny();
                break;
            case 10:
                yHoch = false;
                xLinks = true;
                yThenx();
                break;
            case 11:
                yHoch = false;
                xLinks = false;
                yThenx();
                break;
            case 12:
                yHoch = false;
                xLinks = true;
                xTheny();
                break;
        }

    }
    public void moveX(){
        if(i<gp.w_TileSize){
            if(xLinks){
                x--;
            }else {
                x++;
            }
            i++;
        }else{
            if(i == gp.w_TileSize){
                i = 0;
            }
        }
    }
        //System.out.println(x);
    public void moveY(){
        if(j<gp.h_TileSize){
            if(yHoch){
                y--;
            }else {
                y++;
            }
            j++;
        }else {
            if (j == gp.h_TileSize) {
                j = 0;
            }
        }
        //System.out.println(x);
    }

    public void xTheny(){
        if(ieck <= gp.h_TileSize/2){
            if(ieck == gp.h_TileSize/2){
                if(yHoch){
                    y--;
                }else {
                    y++;
                }
            }else{
                if(xLinks){
                    x--;
                }else{
                    x++;
                }
                ieck++;
            }
        }else{
            ieck = 0;
        }
    }
    public void yThenx(){
        if(ieck <= gp.h_TileSize/2){
            if(ieck == gp.h_TileSize/2){
                if(xLinks){
                    x--;
                }else{
                    x++;
                }
            }else{
                if(yHoch){
                    y--;
                }else {
                    y++;
                }
                ieck++;
            }
        }else{
            ieck = 0;
        }
    }
    //Zeichnet den Gegner auf dem GamePanel
    public void draw(Graphics2D g2) {
        g2.drawImage(image, getX(), getY(), gp.w_TileSize - 10, gp.h_TileSize - 10, null);
        //collsionbox
        Color c1 = new Color(155,2,2,150);
        g2.setColor(c1);
        //g2.drawRect(getX()+15,getY()+5,gp.w_TileSize - 50, gp.h_TileSize - 20);
    }


//TODO Damage Funktion

}