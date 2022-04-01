import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startUI extends JFrame {
    //Deklaration der Buttons
    private JPanel panel1;
    private JButton startButton;
    private JButton exitButton;



    public startUI(){
        //Festlegen der Fenster Eigenschaften: Hoehe, Breite, On close Action
        setContentPane(panel1);
        setTitle("Start");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);



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
    }



    public static void main(String[] args) {
        startUI myFrame = new startUI();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
