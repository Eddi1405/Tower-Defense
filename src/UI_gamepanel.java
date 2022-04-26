import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import static java.lang.Math.round;

public class UI_gamepanel extends JPanel implements Runnable {
    //TODO Get Res vom Monitor und dann Dynamisch anpassen.
    //TODO den Müll aufräumen, unused Variablen, Alte Mains etc, THREAD FREIGEBEN
    //Screen Settings
    final int maxScreenCol = 17; //17
    final int maxScreenRow = 12; //12
    int w_tileSize;
    int h_tileSize;
    Gegner gg = new Gegner(this);
    //Gamethread für Performance
    Thread gameThread;
    mapGen tileM = new mapGen(this);
    public UI ui = new UI(this);

    public int width;
    public int height;

    public int screen;

    //Panel definieren
    public UI_gamepanel(int map,int width,int height) {
        this.setDoubleBuffered(true);
        tileM.loadMap(map);
        /**80% des screens wird für die map verwendet deswegen /10+8.
        Es wird durch die Rows der Map geteilt hat deswegen /maxScreenRow, das gleiche bei height*/
        w_tileSize = round((width/10*8)/maxScreenCol);
        h_tileSize = round(height/maxScreenRow)+1;

        screen = w_tileSize*maxScreenCol;

        this.width = width;
        this.height = height;
    }

    //ist wie eine Schleife
    public void startGameThread() {

        if(gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }


    //Schleife
    @Override
    public void run() {
        while (gameThread != null) {
            try {
                gameThread.sleep(2000);
                System.out.println("sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            gg.move();
            //paintComponent(Graphics g)
            repaint();
        }

    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //Map
        tileM.draw(g2);

        //Gegner
        gg.draw(g2);

        //UI
        ui.draw(g2);

        g2.dispose();


    }
}
