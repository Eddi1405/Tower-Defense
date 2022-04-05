import javax.swing.*;
    /*Deklarierung der genutzten Buttons Aufruf der nötigen Ressource,
    Window via Swing, Swing wurde gewählt weil es standard ist für Intellij*/
public class aufloesung extends JFrame{
    private JButton button1;
    private JButton button2;
    private JButton button3;
    private JButton button4;
    private JPanel panel2;


    /*Window mit Titel standard Groeße von 500*300, default schließ Verhalten */
    public aufloesung(){


        setTitle("Tower Defense");
        setSize(500, 300);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

    }//

    /*Erstellen eines neuen Turmes*/
    public static void main(String[] args) {
        aufloesung myFrame = new aufloesung();
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
