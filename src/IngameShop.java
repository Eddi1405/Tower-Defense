import javax.swing.*;

public class IngameShop extends JPanel {


    JPanel overlay = new JPanel();


    private JButton tower1Button;
    private JButton tower2Button;
    private JButton extraHPButton;
    private JButton schadensBonusButton;
    private JButton tower3Button;
    private JButton tower4Button;


    public IngameShop() {
        overlay.add(tower1Button);
        overlay.add(tower2Button);
        overlay.add(tower3Button);
        overlay.add(tower4Button);
        overlay.add(schadensBonusButton);
        overlay.add(extraHPButton);
    }

}
