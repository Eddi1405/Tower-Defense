import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.*;

import static java.lang.Math.round;

public class UI {
    //TODO Coinsmenge muss spaeter angepasst werden und Incre/decrementiert werden
    private int coins = 10;
    private int leben = 150;
    private String system = System.getProperty("os.name").toLowerCase();
    boolean[] dragValid;
    double breite;
    static boolean ab = true;
    UI_gamepanel gp;
    Font Seven;
    Tile[] tower;
    Tile[] feahigkeiten;
    Tile[] sidebar;
    Point[] imageCorner;
    Point prevPt;
    //TODO Grid
    //TODO mehre tower von einer sorte auf dem bildschirm
    public UI(UI_gamepanel gp) {
        tower = new Tile[20];
        feahigkeiten = new Tile[3];
        sidebar = new Tile[2];
        imageCorner = new Point[10];
        dragValid = new boolean[10];
        this.gp = gp;
        setFont();
        getImageTower();
        //Dragpanel
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        gp.addMouseListener(clickListener);
        gp.addMouseMotionListener(dragListener);

    }

    public void setPoint(){
        if(ab){
            breite = (gp.width - gp.screen) / 100;
            imageCorner[0] = new Point(gp.screen + (int) round(breite * 13), (int) round(gp.h_tileSize * 1.2) + 50);
            imageCorner[1] = new Point(gp.screen + (int) round(breite * 13) + (int) round(gp.w_tileSize * 1.2), (int) round(gp.h_tileSize * 1.2) + 50);
            imageCorner[2] = new Point(gp.screen + (int) round(breite * 13) + (int) round(gp.w_tileSize * 1.2) * 2, (int) round(gp.h_tileSize * 1.2) + 50);
            ab = false;
        }

    }

    public void draw(Graphics2D g2) {
        setPoint();
        //Schriftart wird gesetzt
        g2.setFont(Seven);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN, 60));

        //zeichnet ein rechteck
        Color c = new Color(196, 157, 73);
        g2.setColor(c);
        g2.fillRect(gp.screen, 0, gp.width - gp.screen, gp.height);
        g2.setColor(Color.BLACK);
        //zeichnet einen schwarzen Strich um Map und Shop zu trennen
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(gp.screen, 0, gp.screen, gp.height);

        //Breite wird defeniert damit alles dynamisch angepasst werden kann

        //zeichnet die Towers
        //1 reihe feahigkeiten
        g2.drawImage(feahigkeiten[0].image, gp.screen + (int) round(breite * 10), 50, (int) round(gp.w_tileSize * 1.2), (int) round(gp.h_tileSize * 1.2), null);
        g2.drawImage(feahigkeiten[1].image, gp.screen + (int) round(breite * 10) + (int) round(gp.w_tileSize * 1.2), 50, (int) round(gp.w_tileSize * 1.2), (int) round(gp.h_tileSize * 1.2), null);
        g2.drawImage(feahigkeiten[2].image, gp.screen + (int) round(breite * 10) + (int) round(gp.w_tileSize * 1.2) * 2, 50, (int) round(gp.w_tileSize * 1.2), (int) round(gp.h_tileSize * 1.2), null);
        //2 reihe
        g2.drawImage(tower[0].image, (int) imageCorner[0].getX(), (int) imageCorner[0].getY(), (int) round(gp.w_tileSize * 1.0), (int) round(gp.h_tileSize * 1.2), null);
        g2.drawImage(tower[1].image,(int) imageCorner[1].getX(), (int) imageCorner[1].getY(), (int) round(gp.w_tileSize * 1.0), (int) round(gp.h_tileSize * 1.2), null);
        g2.drawImage(tower[2].image, (int) imageCorner[2].getX(), (int) imageCorner[2].getY(), (int) round(gp.w_tileSize * 1.0), (int) round(gp.h_tileSize * 1.2), null);

        //Da die Darstellung auf Linux, Windows und Mac unterschiedlich ist musste hier gecheckt werden welches System verwendet wird damit die Anzeige richitg ist
        if (system.contains("nix") || system.contains("nux")) {
            //Leben
            g2.drawImage(sidebar[0].image, gp.screen + (int) round(breite * 81.7), 5, (int) round(gp.w_tileSize * 0.6), (int) round(gp.h_tileSize * 0.6), null);
            g2.drawString(String.valueOf(leben), gp.screen + (int) round(breite * 101.7), 50);
            //coins
            g2.drawImage(sidebar[1].image, gp.screen + (int) round(breite * 5), 5, (int) round(gp.w_tileSize * 0.6), (int) round(gp.h_tileSize * 0.6), null);
            g2.drawString(String.valueOf(coins), gp.screen + (int) round(breite * 23.3), 50);
        } else {
            //leben
            g2.drawImage(sidebar[0].image, gp.screen + (int) round(breite * 62.7), 5, (int) round(gp.w_tileSize * 0.6), (int) round(gp.h_tileSize * 0.6), null);
            g2.drawString(String.valueOf(leben), gp.screen + (int) round(breite * 75.7), 50);
            //coins
            g2.drawImage(sidebar[1].image, gp.screen + (int) round(breite * 5), 5, (int) round(gp.w_tileSize * 0.6), (int) round(gp.h_tileSize * 0.6), null);
            g2.drawString(String.valueOf(coins), gp.screen + (int) round(breite * 18.3), 50);
        }

    }

    //Hier werden die Bilder geladen
    public void getImageTower() {

        try {
            //feahigkeiten
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
            //sidebar
            sidebar[0] = new Tile();
            sidebar[0].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Heart.png"));
            sidebar[1] = new Tile();
            sidebar[1].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Coin.png"));


        } catch (IOException e) {
            System.out.println("bild fehler");
            e.printStackTrace();
        }
    }

    //hier wird die Schriftart geladen
    public void setFont() {

        try {
            InputStream is = getClass().getResourceAsStream("/font/SevenSegment.ttf");
            Seven = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void check(){

        int imx =(int)imageCorner[0].getX();
        int imy =(int)imageCorner[0].getY();
        double mx = imx % gp.w_tileSize;
        int my = imy % gp.h_tileSize;

        imageCorner[0].setLocation(imx-mx,imy-my);
    }
    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            prevPt = e.getPoint();
            //Damit nur wenn auf das bild geklickt wird sich das bild bewegt
            dragValid[0] = (e.getPoint().getX() > imageCorner[0].getX()) &&
                    (e.getPoint().getX() < (imageCorner[0].getX() + (int) round(gp.w_tileSize * 1.2))) &&
                    (e.getPoint().getY() > imageCorner[0].getY()) &&
                    (e.getPoint().getY() < (imageCorner[0].getY() + (int) round(gp.w_tileSize * 1.2)));

            dragValid[1] = (e.getPoint().getX() > imageCorner[1].getX()) &&
                    (e.getPoint().getX() < (imageCorner[1].getX() + (int) round(gp.w_tileSize * 1.2))) &&
                    (e.getPoint().getY() > imageCorner[1].getY()) &&
                    (e.getPoint().getY() < (imageCorner[1].getY() + (int) round(gp.w_tileSize * 1.2)));

            dragValid[2] = (e.getPoint().getX() > imageCorner[2].getX()) &&
                    (e.getPoint().getX() < (imageCorner[2].getX() + (int) round(gp.w_tileSize * 1.2))) &&
                    (e.getPoint().getY() > imageCorner[2].getY()) &&
                    (e.getPoint().getY() < (imageCorner[2].getY() + (int) round(gp.w_tileSize * 1.2)));

        }

        @Override
        public void mouseReleased(MouseEvent e) {
            gp.rel = false;
            check();
        }
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            Point currentPt = e.getPoint();
            //Checkt für jedes bild ob es bewegt werden darf
            if (dragValid[0]) {
                imageCorner[0].translate(
                        (int) (currentPt.getX() - prevPt.getX()),
                        (int) (currentPt.getY() - prevPt.getY())
                );
                gp.rel = true;
            } else if (dragValid[1]) {
                imageCorner[1].translate(
                        (int) (currentPt.getX() - prevPt.getX()),
                        (int) (currentPt.getY() - prevPt.getY())
                );
                gp.rel = true;
            }else if (dragValid[2]) {
                imageCorner[2].translate(
                        (int) (currentPt.getX() - prevPt.getX()),
                        (int) (currentPt.getY() - prevPt.getY())
                );
                gp.rel = true;
            }
            prevPt = currentPt;
        }
    }
}

