import java.awt.*;
import java.awt.image.BufferedImage;

public class scaling {

    public BufferedImage scale(BufferedImage orignial,int breite, int hoehe){

        BufferedImage scale = new BufferedImage(breite,hoehe,orignial.getType());
        Graphics2D g2 = scale.createGraphics();
        g2.drawImage(orignial,0,0,breite,hoehe,null);
        g2.dispose();

        return scale;

    }
}
