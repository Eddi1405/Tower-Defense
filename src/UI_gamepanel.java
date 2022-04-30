import javax.swing.*;
import java.awt.*;
import static java.lang.Math.round;

public class UI_gamepanel extends JPanel implements Runnable {
    //Screen Settings
    final int maxScreenCol = 17; //17
    final int maxScreenRow = 12; //12
    protected int w_tileSize;
    protected int h_tileSize;
    int mapCache;
    int map;
    boolean rel = false;
    protected int width;
    protected int height;

    protected int screen;

    //Gamethread für Performance
    Thread gameThread;
    //neue Instanzen
    Gegner gg = new Gegner(this);
    mapGen tileM = new mapGen(this);
    UI ui = new UI(this);
    //Panel definieren
    public UI_gamepanel(int map,int width,int height) {
        this.setDoubleBuffered(true);
        tileM.loadMap(map);
        //80% des screens wird für die map verwendet deswegen /10+8.
        //Es wird durch die Rows der Map geteilt hat deswegen /maxScreenRow, das gleiche bei height
        w_tileSize = (int) round((width/10*8)/maxScreenCol);
        h_tileSize = (int) round(height/maxScreenRow)+1;

        screen = w_tileSize*maxScreenCol;
        this.map = map;
        this.width = width;
        this.height = height;
    }

    //GameThread wird gestartet
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
            /**try {
                gameThread.sleep(1000);
                //System.out.println("sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }//TODO delay ohne thread.sleep
*/

            gg.move();

            repaint();
        }

    }

    //Map wird gezeichnent
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //Map wird gezeichnet wenn nötig
        if(mapCache != map || rel)  {
            tileM.getTileimage();
            tileM.draw(g2);
        }
        //Gegner
        gg.draw(g2);

        //UI
        ui.draw(g2);

        g2.dispose();


    }
}
