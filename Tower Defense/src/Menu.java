import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JPanel {

    private JPanel panel1;
    private JButton exitButton;
    private JButton weiterButton;

    public Menu(){

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
        Menu myFrame = new Menu();
    }
}
