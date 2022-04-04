import javax.swing.*;

public class aufloesung extends JFrame{
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel panel2;

    public aufloesung(){


        setTitle("Tower Defense");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }

    public static void main(String[] args) {
        aufloesung myFrame = new aufloesung();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
