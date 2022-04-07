import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class main {
    //Main wie aus C, der Start eines Programms der die erste Klasse aufruft
    JFrame window = new JFrame();

    Mapmenu mm = new Mapmenu();

    private JButton Map1;
    private JButton Map2;
    private JButton Map3;
    public JPanel panel2;

    public JPanel panel3;
    private JButton start_button;
    private JButton exit_button;

    public main(){

        keyListener kl = new keyListener();

        //window eigenschaften
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(true);
        window.setTitle("Tower Defense");
        window.setLocationRelativeTo(null);
        window.setFocusable(true);
        window.requestFocusInWindow();
        window.setSize(768, 576);
        panel3.setPreferredSize(new Dimension(768, 576));
        window.setContentPane(panel3);
        window.addKeyListener(kl);

        //window.pack();//

        window.setVisible(true);


        //reinladen der Bilder für icon
        try {
            Image img2 = ImageIO.read(getClass().getResource("Bilder/icon.png"));
            window.setIconImage(img2);
        } catch (IOException e) {
            e.printStackTrace();
        }

        start_button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start");
                GamePanel gamePanel = new GamePanel();
                panel3.setVisible(false);
                window.setContentPane(gamePanel);

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

        main Frame = new main();

        System.out.println("-----Beginn-----");
    }
}
