import javax.swing.*;
import java.awt.*;

public class UI_gamepanel extends JPanel implements Runnable {
    //TODO Get Res vom Monitor und dann Dynamisch anpassen.
    //TODO den Müll aufräumen, unused Variablen, Alte Mains etc, THREAD FREIGEBEN
    //Screen Settings
    final int originalTileSize = 16; //16x16
    final int scale = 6;
    final int tileSize = originalTileSize * scale; //48x48
    final int maxScreenCol = 17; //17
    final int maxScreenRow = 12; //12
    final int screenWidth = tileSize * maxScreenCol; // 768
    final int screenHeight = tileSize * maxScreenRow; // 576

    DragPanel dp =new DragPanel();
    Gegner gg = new Gegner();
    //Gamethread für Performance
    Thread gameThread;

    int map;
    //IngameShop is = new IngameShop();


    mapGen tileM = new mapGen(this);

    //Panel definieren
    public UI_gamepanel(int map) {
        this.setDoubleBuffered(true);
        tileM.loadMap(map);

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
