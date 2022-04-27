import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.io.*;

import static java.lang.Math.round;

public class UI {
    private int coins = 10;
    private int leben = 150;
    private String system = System.getProperty("os.name").toLowerCase();
    UI_gamepanel gp;
    Font Seven;
    Tile[] tower;
    //dragpanel
    ImageIcon tower1 = new ImageIcon(this.getClass().getResource("/pictures_map/fire.png"));
    Image tower2 = tower1.getImage();
    final int width = tower1.getIconWidth();
    final int height = tower1.getIconHeight();

    Point imageCorner;
    Point prevPt;
    public UI(UI_gamepanel gp){
        tower = new Tile[20];
        this.gp = gp;
        setFont();
        getImageTower();
        //Dragpanel
        imageCorner = new Point(0, 0);
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        gp.addMouseListener(clickListener);
        gp.addMouseMotionListener(dragListener);
    }

    public void draw(Graphics2D g2){
        //Schriftart wird gesetzt
        g2.setFont(Seven);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,60));

        //zeichnet ein rechteck
        Color c = new Color(196, 157, 73);
        g2.setColor(c);
        g2.fillRect(gp.screen,0,gp.width-gp.screen,gp.height);
        g2.setColor(Color.BLACK);
        //zeichnet einen schwarzen Strich um Map und Shop zu trennen
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(gp.screen,0,gp.screen,gp.height);

        //Breite wird defeniert damit alles dynamisch angepasst werden kann
        double breite = (gp.width-gp.screen)/100;

        //zeichnet die Towers
        //1 reihe
        g2.drawImage(tower[0].image,gp.screen+(int) round(breite*10),50,(int) round(gp.w_tileSize*1.2),(int) round(gp.h_tileSize*1.2),null);
        g2.drawImage(tower[1].image,gp.screen+(int) round(breite*10)+(int) round(gp.w_tileSize*1.2),50,(int) round(gp.w_tileSize*1.2),(int) round(gp.h_tileSize*1.2),null);
        g2.drawImage(tower[2].image,gp.screen+(int) round(breite*10)+(int) round(gp.w_tileSize*1.2)*2,50,(int) round(gp.w_tileSize*1.2),(int) round(gp.h_tileSize*1.2),null);
        //2 reihe
        g2.drawImage(tower[3].image,gp.screen+(int) round(breite*10)+(int) imageCorner.getX(),(int) round(gp.h_tileSize*1.2)+50+(int) imageCorner.getY(),(int) round(gp.w_tileSize*1.2),(int) round(gp.h_tileSize*1.2),null);
        g2.drawImage(tower[6].image,gp.screen+(int) round(breite*10)+(int) round(gp.w_tileSize*1.2),(int) round(gp.h_tileSize*1.2)+50,(int) round(gp.w_tileSize*1.2),(int) round(gp.h_tileSize*1.2),null);

        //Da die Darstellung auf Linux, Windows und Mac unterschiedlich ist musste hier gecheckt werden welches System verwendet wird damit die Anzeige richitg ist
        if(system.contains("nix") || system.contains("nux")){
            //Leben
            g2.drawImage(tower[4].image,gp.screen+(int) round(breite*81.7),5,(int) round(gp.w_tileSize*0.6),(int) round(gp.h_tileSize*0.6),null);
            g2.drawString(String.valueOf(leben),gp.screen+(int) round(breite*101.7),50);
            //coins
            g2.drawImage(tower[5].image,gp.screen+(int) round(breite*5),5,(int) round(gp.w_tileSize*0.6),(int) round(gp.h_tileSize*0.6),null);
            g2.drawString(String.valueOf(coins),gp.screen+(int) round(breite*23.3),50);
        }

        else{
            //leben
            g2.drawImage(tower[4].image,gp.screen+(int) round(breite*62.7),5,(int) round(gp.w_tileSize*0.6),(int) round(gp.h_tileSize*0.6),null);
            g2.drawString(String.valueOf(leben),gp.screen+(int) round(breite*75.7),50);
            //coins
            g2.drawImage(tower[5].image,gp.screen+(int) round(breite*5),5,(int) round(gp.w_tileSize*0.6),(int) round(gp.h_tileSize*0.6),null);
            g2.drawString(String.valueOf(coins),gp.screen+(int) round(breite*18.3),50);
        }

    }

    //Hier werden die Bilder geladen
    public void getImageTower(){

        try{
            tower[0] = new Tile();
            tower[0].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/tower1.png"));
            tower[1] = new Tile();
            tower[1].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/fire.png"));
            tower[2] = new Tile();
            tower[2].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/water.png"));
            tower[3] = new Tile();
            tower[3].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Tower2.png"));
            tower[6] = new Tile();
            tower[6].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Tower3.png"));
            tower[4] = new Tile();
            tower[4].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Heart.png"));
            tower[5] = new Tile();
            tower[5].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Coin.png"));
        } catch (IOException e) {
            System.out.println("bild fehler");
            e.printStackTrace();
        }
    }

    //hier wird die Schriftart geladen
    public void setFont(){

        try {
            InputStream is = getClass().getResourceAsStream("/font/SevenSegment.ttf");
            Seven = Font.createFont(Font.TRUETYPE_FONT,is);
        }catch (FontFormatException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            prevPt = e.getPoint();
        }
    }

    private class DragListener extends MouseMotionAdapter {
        public void mouseDragged(MouseEvent e) {
            Point currentPt = e.getPoint();
            imageCorner.translate(
                    (int) (currentPt.getX() - prevPt.getX()),
                    (int) (currentPt.getY() - prevPt.getY())
            );
            prevPt = currentPt;
            //repaint();

        }
    }
}

