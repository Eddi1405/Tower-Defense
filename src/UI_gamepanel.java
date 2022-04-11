import javax.swing.*;
import java.awt.*;

public class UI_gamepanel extends JPanel implements Runnable{

    //Screen Settings
    final int originalTileSize = 16; //16x16
    final int scale = 6;
    final int tileSize = originalTileSize * scale; //48x48
    final int maxScreenCol = 17;
    final int maxScreenRow = 11;
    final int screenWidth = tileSize * maxScreenCol; // 768
    final int screenHeight = tileSize * maxScreenRow; // 576

    //Erzeugung einer neuen Map
    mapGen tileM = new mapGen(this);
    //Gamethread für Performance
    Thread gameThread;

    //Panel definieren
    public UI_gamepanel(){
        this.setDoubleBuffered(true);
    }

    public void startGameThread(){
        gameThread = new Thread(this);
        gameThread.start();
    }


    //Schleife
    @Override
    public void run() {
        while (gameThread != null) {
            update();

            //repaint();
        }

    }

    public void update(){



    }

    public void paintComponent (Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        g2.dispose();
    }
}
