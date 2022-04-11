import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

public class BaloonsBewegen extends JPanel implements ActionListener, KeyListener { //TODO  Sinnhaftigkeit diese Klasse überprüfen !

//TODO Möglicher denkfehler bei der Erstellung der "Gegner"

    Tile[] baloon;

    Timer timer = new Timer(5,this);
    //Koordinaten
    int x = 0 , y = 0, velX = 0, velY = 0;
    //UI_gamepanel gp = new UI_gamepanel();

    public BaloonsBewegen(){

        timer.start();
        //ZUm Window focussen
        addKeyListener(this);//KeyListner auf den Jframe zugewiesen
        setFocusable(true);
        setRequestFocusEnabled(false);

    }

    public static void main(String[] args) {
        //Windos size zum Testen wird nacher mit der Map Kombiniert damit sich das Objekt auf der Map bewegt
        JFrame frame = new JFrame();
        frame.setSize(768,576);
        BaloonsBewegen bb = new BaloonsBewegen();
        frame.add(bb);
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g){
        //Zeichnet den Würfel 32 x 32
        super.paintComponent(g);
        g.fillRect(x,y,32,32);
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {

        if(x < 0){
            velX = 0;
            x = 0;
        }
        if(x > 335){
            velX = 0;
            x = 335;
        }

        if(y < 0){
            velX = 0;
            x = 0;
        }
        if(y > 312){
            velY = 0;
            y = 312;
        }

        x = x + velX;
        y = y + velY;
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) {

    }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        //Hört auf Tasten druck und ändert auf trigger die Koordinaten
        if(keyEvent.getKeyCode() == KeyEvent.VK_W){
            velX = 0;
            velY = -1;
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_S){
            velX = 0;
            velY = 1;
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_A){
            velX = -1;
            velY = 0;
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_D){
            velX = 1;
            velY = 0;
        }
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) {
        //Setzt Variabeln auf Null  um die Bewegung zu Stoppen.
        velX = 0;
        velY = 0;
    }

    public void getBaloonImage(){

        try {
            //Baloons werden hier geladen
            baloon[0] = new Tile();
            baloon[0].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/baloon.png"));//TODO Baloons.png erstellen

            baloon[1] = new Tile();
            baloon[1].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/baloon2.png"));

            baloon[2] = new Tile();
            baloon[2].image = ImageIO.read(getClass().getResourceAsStream("/pictures_map/baloon3.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
