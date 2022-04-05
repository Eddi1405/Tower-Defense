import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;


import javax.swing.JFrame;


public class startUI {
    //Deklaration der Buttons
    private JPanel panel1;
    private JButton startButton;
    private JButton exitButton;
    private JButton aufloesungButton;


    public startUI(){
        JFrame window = new JFrame();
        //Festlegen der Fenster Eigenschaften: Hoehe, Breite, On close Action
        window.setContentPane(panel1);
        window.setTitle("Tower Defense");
        window.setSize(768, 576);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setVisible(true);
        window.setLocationRelativeTo(null);

        //reinladen der Bilder
        try {
           Image img2 = ImageIO.read(getClass().getResource("Bilder/icon.png"));
            window.setIconImage(img2);
        } catch (IOException e) {
            e.printStackTrace();
        }





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
                        //window.add(gamepanel);

                        //window.pack();
                        window.setContentPane(gamepanel);

                        window.setVisible(true);

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

        //aufloesungButton
        aufloesungButton.setFocusPainted(false);
        aufloesungButton.setBorderPainted(false);
        aufloesungButton.setContentAreaFilled(false);

        try {
            Image img = ImageIO.read(getClass().getResource("Bilder/button_aufloesung.1.png"));
            aufloesungButton.setPressedIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        aufloesungButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


            }
        });
    }



    public static void main(String[] args) {
        startUI myFrame = new startUI();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
