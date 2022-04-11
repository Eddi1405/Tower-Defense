import javax.swing.*;
import java.awt.*;

public class UI_gamepanel extends JPanel implements Runnable{

    //Screen Settings
    final int originalTileSize = 16; //16x16
    final int scale = 6;
    final int tileSize = originalTileSize * scale; //48x48
    final int maxScreenCol = 17;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768
    final int screenHeight = tileSize * maxScreenRow; // 576

    //Gamethread für Performance
    Thread gameThread;

    int map;

    //Panel definieren
    public UI_gamepanel(int map1){
        this.setDoubleBuffered(true);
        map = map1;
        System.out.println("Es ist map:::"+map);
    }

    //Erzeugung einer neuen Map


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
        mapGen tileM = new mapGen(this);
        System.out.println("teszt");
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D)g;
        tileM.draw(g2);
        g2.dispose();
    }
}
