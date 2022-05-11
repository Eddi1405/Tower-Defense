import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.*;

import static java.lang.Math.round;

public class UI_shop extends Entity {
    //Variablen
    private int leben = 150;
    private String system = System.getProperty("os.name").toLowerCase();
    boolean[] dragValid;
    double breite;
    boolean ab = true;
    boolean pressed = true;

    //Instanzen
    UI_gamepanel gp;
    Font seven;
    Tile[] tower;
    Tile[] feahigkeiten;
    Tile[] sidebar;
    Point[] imageCorner;
    Point[] prevImageCorner;
    Point prevPt;

    //TODO mehre tower von einer sorte auf dem
    //TODO Collison mit weg

    public UI_shop(UI_gamepanel gp) {

        tower = new Tile[20];
        feahigkeiten = new Tile[3];
        sidebar = new Tile[4];
        imageCorner = new Point[10];
        prevImageCorner = new Point[3];
        dragValid = new boolean[10];
        this.gp = gp;

        setFont();
        getImageTower();

        //Drag and Drop
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        gp.addMouseListener(clickListener);
        gp.addMouseMotionListener(dragListener);
        setPoint();
        //Collisions Aufklärung
        solidArea = new Rectangle(30,20,gp.w_TileSize -60,gp.h_TileSize -40);

    }
    // Es werden einmalig Punkte gesetzt. Für die Türme im Einkaufsladen
    public void setPoint(){
        if(ab){
            breite = (gp.width - gp.screen) / 100;
            imageCorner[0] = new Point(gp.screen + (int) round(breite * 13), (int) round(gp.h_TileSize * 1.2) + 50);
            imageCorner[1] = new Point(gp.screen + (int) round(breite * 13) + (int) round(gp.w_TileSize * 1.2), (int) round(gp.h_TileSize * 1.2) + 50);
            imageCorner[2] = new Point(gp.screen + (int) round(breite * 13) + (int) round(gp.w_TileSize * 1.2) * 2, (int) round(gp.h_TileSize * 1.2) + 50);
            for(int i=0;i<prevImageCorner.length;i++){
                prevImageCorner[i] =new Point((int)imageCorner[i].getX(),(int)imageCorner[i].getY()) ;
            }
            ab = false;
        }

    }
    // Zeichnet das Turmmenü.
    public void drawMenu(Graphics2D g2){
        if(dragValid[0]) {
            g2.drawImage(sidebar[2].image,(int) imageCorner[0].getX()-(int)breite*4 , (int) imageCorner[0].getY()-(int)breite*4, (int) round(gp.w_TileSize * 0.5), (int) round(gp.h_TileSize * 0.5), null);
            g2.drawImage(sidebar[3].image,(int) imageCorner[0].getX()+(int)breite*15 , (int) imageCorner[0].getY()-(int)breite*4, (int) round(gp.w_TileSize * 0.5), (int) round(gp.h_TileSize * 0.5), null);
        }
    }

    public void draw(Graphics2D g2) {


        //Schriftart wird gesetzt
        g2.setFont(seven);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60));

        //zeichnet ein rechteck
        Color c = new Color(196, 157, 73);
        g2.setColor(c);
        g2.fillRect(gp.screen, 0, gp.width - gp.screen, gp.height);
        g2.setColor(Color.BLACK);
        //zeichnet einen schwarzen Strich um Map und Shop zu trennen
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(gp.screen, 0, gp.screen, gp.height);

        //Breite wird definiert damit alles dynamisch Angepasst werden kann.

        //zeichnet die Towers
        //1 reihe Fähigkeiten
        g2.drawImage(feahigkeiten[0].image, gp.screen + (int) round(breite * 10), 50, (int) round(gp.w_TileSize * 1.2), (int) round(gp.h_TileSize * 1.2), null);
        g2.drawImage(feahigkeiten[1].image, gp.screen + (int) round(breite * 10) + (int) round(gp.w_TileSize * 1.2), 50, (int) round(gp.w_TileSize * 1.2), (int) round(gp.h_TileSize * 1.2), null);
        g2.drawImage(feahigkeiten[2].image, gp.screen + (int) round(breite * 10) + (int) round(gp.w_TileSize * 1.2) * 2, 50, (int) round(gp.w_TileSize * 1.2), (int) round(gp.h_TileSize * 1.2), null);
        //2 reihe
        g2.drawImage(tower[0].image,(int) imageCorner[0].getX() , (int) imageCorner[0].getY(), (int) round(gp.w_TileSize * 1.0), (int) round(gp.h_TileSize * 1.0), null);
        g2.drawImage(tower[1].image,(int) imageCorner[1].getX(), (int) imageCorner[1].getY(), (int) round(gp.w_TileSize * 1.0), (int) round(gp.h_TileSize * 1.0), null);
        g2.drawImage(tower[2].image, (int) imageCorner[2].getX(), (int) imageCorner[2].getY(), (int) round(gp.w_TileSize * 1.0), (int) round(gp.h_TileSize * 1.0), null);

        //Die Angriffsreichweite der Türme, welche noch nicht dynamisch ist.
        Color c1 = new Color(93, 93, 93, 61);
        g2.setColor(c1);
        if (dragValid[0] && gp.grid && !collisionOn){
            g2.fillOval((int) imageCorner[0].getX()+gp.w_TileSize /2-150, (int) imageCorner[0].getY()+gp.h_TileSize /2-150,300,300);
        }
        if (dragValid[1] && gp.grid && !collisionOn){
            g2.fillOval((int) imageCorner[1].getX()+gp.w_TileSize /2-250, (int) imageCorner[1].getY()+gp.h_TileSize /2-250,500,500);
        }
        if (dragValid[2] && gp.grid && !collisionOn){
            g2.fillOval((int) imageCorner[2].getX()+gp.w_TileSize /2-175, (int) imageCorner[2].getY()+gp.h_TileSize /2-175,350,350);
        }
        //Etwas Befindet sich in dem Kollisionsradius des Turmes. (Nicht dynamisch)
        Color c2 = new Color(232, 25, 25, 61);
        g2.setColor(c2);
        if (dragValid[0] && collisionOn){
            g2.fillOval((int) imageCorner[0].getX()+gp.w_TileSize /2-50, (int) imageCorner[0].getY()+gp.h_TileSize /2-50,100,100);
        }
        if (dragValid[1] && collisionOn){
            g2.fillOval((int) imageCorner[1].getX()+gp.w_TileSize /2-50, (int) imageCorner[1].getY()+gp.h_TileSize /2-50,100,100);
        }
        if (dragValid[2] && collisionOn){
            g2.fillOval((int) imageCorner[2].getX()+gp.w_TileSize /2-50, (int) imageCorner[2].getY()+gp.h_TileSize /2-50,100,100);
        }
        // Am Ende wird die Farbe wieder auf den Standard gesetzt.
        g2.setColor(Color.BLACK);

        //Da die Darstellung auf Linux, Windows und Mac unterschiedlich ist musste hier gecheckt werden welches System verwendet wird damit die Anzeige richitg ist
        //TODO Coinsmenge muss spaeter angepasst werden und Incre/decrementiert werden
        int coins = 10;
        if (system.contains("nix") || system.contains("nux")) {
            //Leben
            g2.drawImage(sidebar[0].image, gp.screen + (int) round(breite * 81.7), 5, (int) round(gp.w_TileSize * 0.6), (int) round(gp.h_TileSize * 0.6), null);
            g2.drawString(String.valueOf(leben), gp.screen + (int) round(breite * 101.7), 50);
            //Goldstücke
            g2.drawImage(sidebar[1].image, gp.screen + (int) round(breite * 5), 5, (int) round(gp.w_TileSize * 0.6), (int) round(gp.h_TileSize * 0.6), null);
            g2.drawString(String.valueOf(coins), gp.screen + (int) round(breite * 23.3), 50);
        } else {
            //Leben
            g2.drawImage(sidebar[0].image, gp.screen + (int) round(breite * 62.7), 5, (int) round(gp.w_TileSize * 0.6), (int) round(gp.h_TileSize * 0.6), null);
            g2.drawString(String.valueOf(leben), gp.screen + (int) round(breite * 75.7), 50);
            //Goldstücke
            g2.drawImage(sidebar[1].image, gp.screen + (int) round(breite * 5), 5, (int) round(gp.w_TileSize * 0.6), (int) round(gp.h_TileSize * 0.6), null);
            g2.drawString(String.valueOf(coins), gp.screen + (int) round(breite * 18.3), 50);
        }
    }

    //Hier werden die Bilder geladen
    public void getImageTower() {

        try {
            //Fähigkeiten
            feahigkeiten[0] = new Tile();
            feahigkeiten[0].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/strike.png"));
            feahigkeiten[1] = new Tile();
            feahigkeiten[1].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/fire.png"));
            feahigkeiten[2] = new Tile();
            feahigkeiten[2].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/water.png"));
            //Türme
            tower[0] = new Tile();
            tower[0].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Tower1.png"));
            tower[1] = new Tile();
            tower[1].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Tower2.png"));
            tower[2] = new Tile();
            tower[2].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Tower3.png"));
            //Seitenleiste
            sidebar[0] = new Tile();
            sidebar[0].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Heart.png"));
            sidebar[1] = new Tile();
            sidebar[1].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Coin.png"));
            sidebar[2] = new Tile();
            sidebar[2].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/sell.png"));
            sidebar[3] = new Tile();
            sidebar[3].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/upgrade.png"));

        } catch (IOException e) {
            System.out.println("bild fehler");
            e.printStackTrace();
        }
    }

    //Hier wird die Schriftart geladen
    public void setFont() {

        try {
            InputStream is = getClass().getResourceAsStream("/font/SevenSegment.ttf");
            seven = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //Hier wird überprüft, ob eine Kollision besteht, wenn das der Fall ist, wird der Turm zurückgesetzt, wenn nicht, wird er in das nächste Kästchen gezogen und das Kästchen aus dem er stammt wird Markiert
    private void check(int index){
        collisionOn = false;
        gp.cc.check(this,index);
        //Aktuelle x Position
        int imx =(int)imageCorner[index].getX();
        //Aktuelle y Position
        int imy =(int)imageCorner[index].getY();
        double mx = imx % gp.w_TileSize;
        int my = imy % gp.h_TileSize;

        if(imx < gp.screen-50 && imy < gp.height && !collisionOn) {
            if(mx < gp.w_TileSize /2 && my < gp.h_TileSize /2){
                imageCorner[index].setLocation(imx - mx, imy - my);
                gp.cc.addPosition((int)(imx-mx)/gp.w_TileSize,(int)(imy-my)/gp.h_TileSize);
                //System.out.println((int)(imx-mx)/gp.w_tileSize+"-|-"+(int)(imy-my)/gp.h_tileSize);
            } else if (mx > gp.w_TileSize /2 && my < gp.h_TileSize /2) {
                imageCorner[index].setLocation(imx + (gp.w_TileSize -mx), imy - my);
                gp.cc.addPosition((int)(imx + (gp.w_TileSize -mx))/gp.w_TileSize,(int)(imy-my)/gp.h_TileSize);
                //System.out.println((int)(imx + (gp.w_tileSize-mx))/gp.w_tileSize+"+|-"+(int)(imy-my)/gp.h_tileSize);
            } else if (mx < gp.w_TileSize /2 && my > gp.h_TileSize /2) {
                imageCorner[index].setLocation(imx - mx, imy + (gp.h_TileSize -my));
                gp.cc.addPosition((int)(imx-mx)/gp.w_TileSize,(int)(imy + (gp.h_TileSize -my))/gp.h_TileSize);
                //System.out.println((int)(imx-mx)/gp.w_tileSize+"-|+"+(int)(imy + (gp.h_tileSize-my))/gp.h_tileSize);
            }else {
                imageCorner[index].setLocation(imx + (gp.w_TileSize -mx), imy + (gp.h_TileSize -my));
                gp.cc.addPosition((int)((imx + (gp.w_TileSize -mx))/gp.w_TileSize),(int)(imy + (gp.h_TileSize -my))/gp.h_TileSize);
                //System.out.println((int)((imx + (gp.w_tileSize-mx))/gp.w_tileSize)+"+|+"+(int)(imy + (gp.h_tileSize-my))/gp.h_tileSize);
            }
        }else if(collisionOn && dragValid[index]){
            imageCorner[index].setLocation(prevImageCorner[index].getX(),prevImageCorner[index].getY());;
        }
    }

    public void drag(int index,Point currentPt){

        //Checkt für jedes Bild, ob es bewegt werden darf
        if (dragValid[index]) {
            imageCorner[index].translate(
                    (int) (currentPt.getX() - prevPt.getX()),
                    (int) (currentPt.getY() - prevPt.getY())
            );
            collisionOn = false;
            gp.cc.check(this,index);

            gp.grid = true;
        }

    }

    public void setDragValid(MouseEvent e,int index){
        dragValid[index] = (e.getPoint().getX() > imageCorner[index].getX()) &&
                (e.getPoint().getX() < (imageCorner[index].getX() + (int) round(gp.w_TileSize * 1.0))) &&
                (e.getPoint().getY() > imageCorner[index].getY()) &&
                (e.getPoint().getY() < (imageCorner[index].getY() + (int) round(gp.w_TileSize * 1.0)));
    }
    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            prevPt = e.getPoint();
            //Damit nur wenn auf das bild geklickt wird sich das bild bewegt

            setDragValid(e,0);
            setDragValid(e,1);
            setDragValid(e,2);
        }

        @Override
        public void mouseReleased(MouseEvent e) {
            gp.grid = false;
            //checkt, ob das bild richtig sitz
            check(0);
            check(1);
            check(2);
            pressed = true;
        }
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            Point currentPt = e.getPoint();
            //Ermöglicht das Bewegen der Türme
            drag(0,currentPt);
            drag(1,currentPt);
            drag(2,currentPt);

            prevPt = currentPt;
        }
    }
}

