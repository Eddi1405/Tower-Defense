import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.*;

import static java.lang.Math.round;

public class UI_shop extends Entity {
    //Variablen
    public String system = System.getProperty("os.name").toLowerCase();
    boolean[] dragValid;
    double breite;
    boolean ab = true;
    boolean tw1Stop,tw2Stop,tw3Stop;

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

    public UI_shop(UI_gamepanel gp) {
        tower = new Tile[20];
        feahigkeiten = new Tile[3];
        sidebar = new Tile[4];
        imageCorner = new Point[10];
        dragValid = new boolean[10];
        this.gp = gp;

        setFont();
        getImageTower();

        //Drag and Drop
        //TODO auslagern in ne eigene Klasse wenn es geht
        ClickListener clickListener = new ClickListener();
        //DragListener dragListener = new DragListener();
        gp.addMouseListener(clickListener);
        //gp.addMouseMotionListener(dragListener);
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
            ab = false;
        }

    }


    public void draw(Graphics2D g2) {
        //Schriftart wird gesetzt
        g2.setFont(seven);

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
        if (system.contains("nix") || system.contains("nux")) {
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, (int) breite * 8));
        }
        else{

        }

        g2.drawImage(tower[0].image,(int) imageCorner[0].getX() , (int) imageCorner[0].getY(), (int) round(gp.w_TileSize * 1.0), (int) round(gp.h_TileSize * 1.0), null);
        if(gp.ueberT1 == 0){
            Color c2 = new Color(232, 25, 25, 111);
            g2.setColor(c2);
        }else{
            g2.setColor(Color.BLACK);
        }
        g2.drawString((String.valueOf(gp.ueberT1)),(int) imageCorner[0].getX()+10 , (int) imageCorner[0].getY()+5);

        g2.drawImage(tower[1].image,(int) imageCorner[1].getX(), (int) imageCorner[1].getY(), (int) round(gp.w_TileSize * 1.0), (int) round(gp.h_TileSize * 1.0), null);
        if(gp.ueberT2 == 0){
            Color c2 = new Color(232, 25, 25, 111);
            g2.setColor(c2);
        }else{
            g2.setColor(Color.BLACK);
        }
        g2.drawString((String.valueOf(gp.ueberT2)),(int) imageCorner[1].getX()+10 , (int) imageCorner[1].getY()+5);

        g2.drawImage(tower[2].image, (int) imageCorner[2].getX(), (int) imageCorner[2].getY(), (int) round(gp.w_TileSize * 1.0), (int) round(gp.h_TileSize * 1.0), null);
        if(gp.ueberT3 == 0){
            Color c2 = new Color(232, 25, 25, 111);
            g2.setColor(c2);
        }else{
            g2.setColor(Color.BLACK);
        }
        g2.drawString((String.valueOf(gp.ueberT3)),(int) imageCorner[2].getX()+10 , (int) imageCorner[2].getY()+5);

        g2.setColor(Color.BLACK);
        //Da die Darstellung auf Linux, Windows und Mac unterschiedlich ist musste hier gecheckt werden welches System verwendet wird damit die Anzeige richitg ist
        //TODO Coinsmenge muss spaeter angepasst werden und Incre/decrementiert werden

        if (system.contains("nix") || system.contains("nux")) {
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, (int) breite * 18));
            //Leben
            g2.drawImage(sidebar[0].image, gp.screen + (int) round(breite * 81.7), 5, (int) round(gp.w_TileSize * 0.6), (int) round(gp.h_TileSize * 0.6), null);
            g2.drawString(String.valueOf(gp.leben), gp.screen + (int) round(breite * 101.7), gp.height/100*5);
            //Goldstücke
            g2.drawImage(sidebar[1].image, gp.screen + (int) round(breite * 5), 5, (int) round(gp.w_TileSize * 0.6), (int) round(gp.h_TileSize * 0.6), null);
            g2.drawString(String.valueOf(gp.coins), gp.screen + (int) round(breite * 23.3), gp.height/100*5);
        } else {
            g2.setFont(g2.getFont().deriveFont(Font.PLAIN, (int) breite * 12));
            //Leben
            g2.drawImage(sidebar[0].image, gp.screen + (int) round(breite * 62.7), 5, (int) round(gp.w_TileSize * 0.6), (int) round(gp.h_TileSize * 0.6), null);
            g2.drawString(String.valueOf(gp.leben), gp.screen + (int) round(breite * 75.7), 50);
            //Goldstücke
            g2.drawImage(sidebar[1].image, gp.screen + (int) round(breite * 5), 5, (int) round(gp.w_TileSize * 0.6), (int) round(gp.h_TileSize * 0.6), null);
            g2.drawString(String.valueOf(gp.coins), gp.screen + (int) round(breite * 18.3), 50);
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

    public void setDragValid(MouseEvent e,int index){
        dragValid[index] = (e.getPoint().getX() > imageCorner[index].getX()) &&
                (e.getPoint().getX() < (imageCorner[index].getX() + (int) round(gp.w_TileSize * 1.0))) &&
                (e.getPoint().getY() > imageCorner[index].getY()) &&
                (e.getPoint().getY() < (imageCorner[index].getY() + (int) round(gp.w_TileSize * 1.0)));
        if(dragValid[index]){
            switch (index){
                case 0:
                    if(gp.moved && gp.anzT1 < gp.tw.tower1.length){
                        gp.anzT1++;
                        gp.ueberT1--;
                        gp.moved = false;
                    }
                    break;
                case 1:
                    if(gp.moved && gp.anzT2 < gp.tw.tower2.length){
                        gp.anzT2++;
                        gp.ueberT2--;
                        gp.moved = false;
                    }
                    break;
                case 2:
                    if(gp.moved && gp.anzT3 < gp.tw.tower3.length){
                        gp.anzT3++;
                        gp.ueberT3--;
                        gp.moved = false;
                    }
                    break;
            }
        }
    }

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            prevPt = e.getPoint();
            //Damit nur wenn auf das bild geklickt wird sich das bild bewegt

            setDragValid(e, 0);
            setDragValid(e, 1);
            setDragValid(e, 2);
        }
    }
    }

