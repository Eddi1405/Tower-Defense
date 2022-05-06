import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.*;
import java.util.Objects;

import static java.lang.Math.round;

public class UI_shop {
    private int leben = 150;
    private String system = System.getProperty("os.name").toLowerCase();
    boolean[] dragValid;
    double breite;
    boolean ab = true;
    UI_gamepanel gp;
    Font Seven;
    Tile[] tower;
    Tile[] feahigkeiten;
    Tile[] sidebar;
    Point[] imageCorner;
    Point prevPt;
    //TODO mehre tower von einer sorte auf dem
    //TODO Collison mit weg
    public UI_shop(UI_gamepanel gp) {
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
    public void drawMenu(Graphics2D g2){

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
        g2.drawImage(tower[0].image,gp.screen + (int) round(breite * 13) , ((int) round(gp.h_tileSize * 1.2) + 50), (int) round(gp.w_tileSize * 1.0), (int) round(gp.h_tileSize * 1.0), null);
        g2.drawImage(tower[1].image,(int) imageCorner[1].getX(), (int) imageCorner[1].getY(), (int) round(gp.w_tileSize * 1.0), (int) round(gp.h_tileSize * 1.0), null);
        g2.drawImage(tower[2].image, (int) imageCorner[2].getX(), (int) imageCorner[2].getY(), (int) round(gp.w_tileSize * 1.0), (int) round(gp.h_tileSize * 1.0), null);

        Color c1 = new Color(93, 93, 93, 61);
        g2.setColor(c1);
        if (dragValid[1]){
            g2.fillOval((int) imageCorner[1].getX()+UI_gamepanel.w_tileSize/2-250, (int) imageCorner[1].getY()+UI_gamepanel.h_tileSize/2-250,500,500);
        }
        if (dragValid[2]){
            g2.fillOval((int) imageCorner[2].getX()+UI_gamepanel.w_tileSize/2-150, (int) imageCorner[2].getY()+UI_gamepanel.h_tileSize/2-150,300,300);
        }
        g2.setColor(Color.BLACK);
        //Da die Darstellung auf Linux, Windows und Mac unterschiedlich ist musste hier gecheckt werden welches System verwendet wird damit die Anzeige richitg ist
        //TODO Coinsmenge muss spaeter angepasst werden und Incre/decrementiert werden
        int coins = 10;
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
    public void newTower(Graphics2D g2,int index){

        g2.drawImage(tower[0].image, (int) imageCorner[index].getX(), (int) imageCorner[index].getY(), (int) round(gp.w_tileSize * 1.0), (int) round(gp.h_tileSize * 1.0), null);

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

    private void check(int index){
        int imx =(int)imageCorner[index].getX();
        int imy =(int)imageCorner[index].getY();
        double mx = imx % gp.w_tileSize;
        int my = imy % gp.h_tileSize;

        if(imx < gp.screen-50 && imy < gp.height) {
            if(mx < gp.w_tileSize/2 && my < gp.h_tileSize/2){
                imageCorner[index].setLocation(imx - mx, imy - my);
            } else if (mx > gp.w_tileSize/2 && my < gp.h_tileSize/2) {
                imageCorner[index].setLocation(imx + (gp.w_tileSize-mx), imy - my);
            } else if (mx < gp.w_tileSize/2 && my > gp.h_tileSize/2) {
                imageCorner[index].setLocation(imx - mx, imy + (gp.h_tileSize-my));
            }else {
                imageCorner[index].setLocation(imx + (gp.w_tileSize-mx), imy + (gp.h_tileSize-my));
            }
        }
    }

    public void drag(int index,Point currentPt){

        //Checkt für jedes bild ob es bewegt werden darf
        if (dragValid[index]) {
            imageCorner[index].translate(
                    (int) (currentPt.getX() - prevPt.getX()),
                    (int) (currentPt.getY() - prevPt.getY())
            );
            gp.rel = true;
        }

    }

    public void setDragValid(MouseEvent e,int index){

        dragValid[index] = (e.getPoint().getX() > imageCorner[index].getX()) &&
                (e.getPoint().getX() < (imageCorner[index].getX() + (int) round(gp.w_tileSize * 1.0))) &&
                (e.getPoint().getY() > imageCorner[index].getY()) &&
                (e.getPoint().getY() < (imageCorner[index].getY() + (int) round(gp.w_tileSize * 1.0)));

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
            gp.rel = false;
            //checkt, ob das bild richtig sitz
            check(0);
            check(1);
            check(2);
        }
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            Point currentPt = e.getPoint();
            //ermöglicht das bewegen der Türme
            drag(0,currentPt);
            drag(1,currentPt);
            drag(2,currentPt);

            prevPt = currentPt;
        }
    }
}

