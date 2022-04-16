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
    double StartX, StartY;
    DragPanel dp =new DragPanel();
    //Gamethread für Performance
    Thread gameThread;

    int map;
    //IngameShop is = new IngameShop();
    //Enemy em = new Enemy(StartX,StartY);


    mapGen tileM = new mapGen(this);

    //Panel definieren
    public UI_gamepanel(int map) {
        this.setDoubleBuffered(true);
        tileM.loadMap(map);

    }

    //Erzeugung einer neuen Map


    public void startGameThread() {
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

    public void update() {

    }
    //TODO Unused Müll
    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileM.draw(g2);
        g2.dispose();
        //em.render(g);
        //System.out.println("Bild");
        this.add(dp);
        this.dp.setVisible(true);
        //this.dp.setSize(300,300);
        this.dp.setBounds(1625,0,300,1100);

        //this.dp.setBackground(Color.blue);
        this.setVisible(true);
    }
}
