import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class startUI extends JFrame {

    private JPanel panel1;
    private JButton startButton;
    private JButton exitButton;



    public startUI(){

        setContentPane(panel1);
        setTitle("Start");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        startButton.setFocusPainted(false);
        startButton.setBorderPainted(false);
        startButton.setContentAreaFilled(false);

        exitButton.setFocusPainted(false);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);


        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start");

                try {
                    Image img = ImageIO.read(getClass().getResource("Bilder/button_start_2.png"));
                    startButton.setPressedIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
            }
        });


        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("Exit");

                try {
                    Image img = ImageIO.read(getClass().getResource("Bilder/button_exit_2.png"));
                    exitButton.setPressedIcon(new ImageIcon(img));
                } catch (Exception ex) {
                    System.out.println(ex);
                }
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
