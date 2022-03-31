import javax.swing.*;
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

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Start");
            }
        });
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
}
