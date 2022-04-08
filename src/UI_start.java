import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class UI_start {
    //Main wie aus C, der Start eines Programms der die erste Klasse aufruft
    JFrame window = new JFrame();
    UI_menu mm = new UI_menu();

    public JPanel start_panel;
    private JButton start_button;
    private JButton exit_button;
    int map = 0;

    public UI_start(){

        keyListener kl = new keyListener();

        //window eigenschaften
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Tower Defense");
        window.setLocationRelativeTo(null);
        window.setFocusable(true);
        window.requestFocusInWindow();
        window.setSize(768, 576);
        start_panel.setPreferredSize(new Dimension(768, 576));
        window.setContentPane(start_panel);
        window.addKeyListener(kl);
        window.setVisible(true);


        //Buttons
        mm.Map1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map = 1;
                System.out.println(map);
                mapAuswahl();
            }
        });
        mm.Map2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map = 2;
                mapAuswahl();
            }
        });
        mm.Map3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                map = 3;
                mapAuswahl();
            }
        });
        start_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start");
                start_panel.setVisible(false);
                window.setContentPane(mm.panel2);
            }
        });
        exit_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Exit");
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        UI_start Frame = new UI_start();
        System.out.println("-----Beginn-----");
    }

    public void mapAuswahl(){
        UI_gamepanel gamePanel = new UI_gamepanel();
        mm.panel2.setVisible(false);
        window.setContentPane(gamePanel);
        gamePanel.startGameThread();
        gamePanel.repaint();
    }

    public int getMap(){

        return map;

    }
}
