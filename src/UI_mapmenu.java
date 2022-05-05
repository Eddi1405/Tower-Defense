import javax.swing.*;
import java.awt.*;

/**
 * Ist nur da damit die Form benutzt werden kann.
 */
public class UI_mapmenu {

    public JButton Map1;
    public JButton Map2;
    public JButton Map3;
    public JPanel menu_panel;

    public UI_mapmenu() {
        //Map Preview Bilder werden scaliert
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/pictures_ui/map1.png"));
        Image image = icon.getImage();
        Image sImage = image.getScaledInstance(300,200, Image.SCALE_SMOOTH);
        icon = new ImageIcon(sImage);
        Map1.setIcon(icon);

        icon = new ImageIcon(this.getClass().getResource("/pictures_ui/map2.png"));
        image = icon.getImage();
        sImage = image.getScaledInstance(300,200, Image.SCALE_SMOOTH);
        icon = new ImageIcon(sImage);
        Map2.setIcon(icon);

        icon = new ImageIcon(this.getClass().getResource("/pictures_ui/map3.png"));
        image = icon.getImage();
        sImage = image.getScaledInstance(300,200, Image.SCALE_SMOOTH);
        icon = new ImageIcon(sImage);
        Map3.setIcon(icon);
    }
}

