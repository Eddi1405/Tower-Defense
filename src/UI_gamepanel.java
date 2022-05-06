import javax.swing.*;
import java.awt.*;
import static java.lang.Math.round;

public class UI_gamepanel extends JPanel implements Runnable {
    //Screen Settings
    final int maxScreenCol = 17; //17
    final int maxScreenRow = 12; //12
    static int w_tileSize;
    static int h_tileSize;
    Boolean mapCache;
    boolean rel = false;
    protected int width;
    protected int height;

    protected int screen;

    //neue Instanzen
    Thread gameThread;
    mapGen tileM;
    Gegner gg;
    UI_shop ui;
    CollisionChecker cc;
    SpawnSystem ss;

    //Panel definieren
    public UI_gamepanel(int width,int height) {
        this.setDoubleBuffered(true);

        //80% des screens wird für die map verwendet deswegen /10+8.
        //Es wird durch die Rows der Map geteilt hat deswegen /maxScreenRow, das gleiche bei height
        w_tileSize = round(width/10*8/maxScreenCol);
        h_tileSize = round(height/maxScreenRow)+1;
        screen = w_tileSize*maxScreenCol;
        this.width = width;
        this.height = height;
        if(tileM == null){
            tileM = new mapGen(this);
        }
        if(gg==null){
            gg = new Gegner(this);
        }
        if(ui == null){
            ui = new UI_shop(this);
        }
        if(cc == null){
            cc = new CollisionChecker(this);
        }
        if(ss == null){
            ss = new SpawnSystem(this);
            ss.start();
        }
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
            try {
                gameThread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            //gg.move();

            repaint();
        }

    }

    //Map wird gezeichnent
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //Map wird gezeichnet wenn nötig
        if(mapCache || rel)  {
            tileM.getTileimage();
            tileM.draw(g2);
            mapCache = true;
        }

        //UI
        ui.draw(g2);

        ss.spawn(g2);

        g2.dispose();


    }
    public void newMap(int map){

        tileM.loadMap(map);

    }
}
