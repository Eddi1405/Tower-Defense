import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class startUI extends JFrame {
    //Deklaration der Buttons
    private JPanel panel1;
    private JButton startButton;
    private JButton exitButton;
    private JButton aufloesungButton;


    public startUI(){
        //Festlegen der Fenster Eigenschaften: Hoehe, Breite, On close Action
        setContentPane(panel1);
        setTitle("Tower Defense");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

//sad
        try {
           Image img2 = ImageIO.read(getClass().getResource("Bilder/icon.png"));
            setIconImage(img2);
        } catch (IOException e) {
            e.printStackTrace();
        }





        //Startbutton design
        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);
        //ändert das icon wenn gedrückt
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

            }
        });

        //Exitbutton design
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
