import javax.swing.*;
import java.awt.*;

import static java.lang.Math.round;

public class UI_gamepanel extends JPanel implements Runnable {
    //TODO Get Res vom Monitor und dann Dynamisch anpassen.
    //TODO den Müll aufräumen, unused Variablen, Alte Mains etc, THREAD FREIGEBEN
    //Screen Settings
    final int originalTileSize = 16; //16x16
    int scale = 6;
    int tileSize = originalTileSize * scale; //48x48
    final int maxScreenCol = 17; //17
    final int maxScreenRow = 12; //12
    final int screenWidth = tileSize * maxScreenCol; // 768
    final int screenHeight = tileSize * maxScreenRow; // 576
    int w_tileSize;
    int h_tileSize;

    DragPanel dp =new DragPanel();
    Gegner gg = new Gegner(w_tileSize,h_tileSize);
    //Gamethread für Performance
    Thread gameThread;

    int map;
    //IngameShop is = new IngameShop();


    mapGen tileM = new mapGen(this);

    //Panel definieren
    public UI_gamepanel(int map,int width,int height) {
        this.setDoubleBuffered(true);
        tileM.loadMap(map);
        this.setOpaque(true);
        w_tileSize = round((width/10*8)/maxScreenCol);
        h_tileSize = round(height/maxScreenRow);


    }

    //Erzeugung einer neuen Map


    public void startGameThread() {

        if(gameThread != null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }


    //Schleife
    @Override
    public void run() {
        while (gameThread != null) {
            gg.move();
            update();


            //repaint();
        }

    }

    public void update() {

    }
    //TODO Unused Müll
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        gg.draw(g2);
        g2.dispose();


    }
}
