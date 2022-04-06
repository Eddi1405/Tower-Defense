import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.JFrame;

public class Mapmenu {

    private JButton Map1;
    private JButton Map2;
    private JButton Map3;
    public JPanel panel2;


        Map1.setFocusPainted(false);
        Map1.setBorderPainted(false);
        Map1.setContentAreaFilled(false);

        Map2.setFocusPainted(false);
        Map2.setBorderPainted(false);
        Map2.setContentAreaFilled(false);

        Map3.setFocusPainted(false);
        Map3.setBorderPainted(false);
        Map3.setContentAreaFilled(false);


    public Mapmenu() {
        Map1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Map2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        Map3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }
}

