import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;

import static java.lang.Math.round;

public class UI {
    private int coins = 10;
    private int leben = 150;
    UI_gamepanel gp;
    Font Seven;
    Tile[] tower;
    public UI(UI_gamepanel gp){

        this.gp = gp;
        setFont();

        tower = new Tile[20];

        getImageTower();

    }

    public void draw(Graphics2D g2){
        System.out.println(gp.width+"asd"+gp.height);
        g2.setFont(Seven);
        g2.setFont(g2.getFont().deriveFont(Font.PLAIN,60));

        //zeichnet ein rechteck
        Color c = new Color(196, 157, 73);
        g2.setColor(c);
        g2.fillRect(gp.screen,0,gp.width-gp.screen,gp.height);
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(gp.screen,0,gp.screen,gp.height);

        double breite = (gp.width-gp.screen)/100;
        //zeichnet den tower
        //1 reihe
        g2.drawImage(tower[0].image,gp.screen+(int) round(breite*10),50,(int) round(gp.w_tileSize*1.2),(int) round(gp.h_tileSize*1.2),null);
        g2.drawImage(tower[1].image,gp.screen+(int) round(breite*10)+(int) round(gp.w_tileSize*1.2),50,(int) round(gp.w_tileSize*1.2),(int) round(gp.h_tileSize*1.2),null);
        g2.drawImage(tower[2].image,gp.screen+(int) round(breite*10)+(int) round(gp.w_tileSize*1.2)*2,50,(int) round(gp.w_tileSize*1.2),(int) round(gp.h_tileSize*1.2),null);
        //2 reihe
        g2.drawImage(tower[3].image,gp.screen+(int) round(breite*10),(int) round(gp.h_tileSize*1.2)+50,(int) round(gp.w_tileSize*1.2),(int) round(gp.h_tileSize*1.2),null);

        //leben
        g2.drawImage(tower[4].image,gp.screen+(int) round(breite*81.7),5,(int) round(gp.w_tileSize*0.6),(int) round(gp.h_tileSize*0.6),null);
        g2.drawString(String.valueOf(leben),gp.screen+(int) round(breite*101.7),50);
        //coins
        g2.drawImage(tower[5].image,gp.screen+(int) round(breite*5),5,(int) round(gp.w_tileSize*0.6),(int) round(gp.h_tileSize*0.6),null);
        g2.drawString(String.valueOf(coins),gp.screen+(int) round(breite*23.3),50);


    }

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
            tower[4] = new Tile();
            tower[4].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Heart.png"));
            tower[5] = new Tile();
            tower[5].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/Coin.png"));
        } catch (IOException e) {
            System.out.println("bild fehler");
            e.printStackTrace();
        }
    }

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
}

