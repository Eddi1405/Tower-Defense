

import javax.swing.JPanel;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable{

    //Screen Settings
    final int originalTileSize = 16; //16x16
    final int scale = 3;

    final int tileSize = originalTileSize * scale; //48x48
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768
    final int screenHeight = tileSize * maxScreenRow; // 576

    //Erzeugung einer neuen Map
    mapGen tileM = new mapGen(this);

    //Gamethread für Performance
    Thread gameThread;

    //Panel definieren
    public GamePanel(){

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.BLACK);
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

            repaint();
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
