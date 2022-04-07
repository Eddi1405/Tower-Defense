import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import javax.swing.JFrame;

public class startUI extends JPanel{
    //Deklaration der Buttons
    public JPanel panel1;
    private JButton startButton;
    private JButton exitButton;
    //JFrame window = new JFrame();
    GamePanel gp = new GamePanel();
    keyListener kl = new keyListener();


    public startUI(){
/**
        //Festlegen der Fenster Eigenschaften: Hoehe, Breite, On close Action
        window.setContentPane(panel1);
        window.setTitle("Tower Defense");
        window.setSize(gp.screenWidth, gp.screenHeight);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(false);
        window.setLocationRelativeTo(null);
        window.addKeyListener(kl);
        window.setFocusable(true);
        window.requestFocusInWindow();*/

        this.setPreferredSize(new Dimension(gp.screenWidth, gp.screenHeight));
        //Startbutton Design
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        //Ändert das Icon wenn gedrückt
        try {
            Image img = ImageIO.read(getClass().getResource("Bilder/button_start.1.png"));
            startButton.setPressedIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        //Es wird geprüft ob der Button start gedrückt wurde
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    System.out.println("Start");
                    GamePanel gamepanel = new GamePanel();
                    //mn.window.setContentPane(gamepanel);
                    //mn.window.setVisible(true);
                    gamepanel.startGameThread();
            }
        });

        //Exitbutton Design
        exitButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        //ändert das icon wenn gedrückt
        try {
            Image img = ImageIO.read(getClass().getResource("Bilder/button_exit.1.png"));
            exitButton.setPressedIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }
        //Es wird geprüft ob der Button exit gedrückt wurde
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit");
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        startUI myFrame = new startUI();

        //if (event. getKeyCode() == KeyEvent. VK_ESCAPE){

        }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
