import java.awt.*;
import java.awt.image.BufferedImage;

public class scaling {
    // Das Image wird genommen und auf den gegebenen Screen angepasst.
    public BufferedImage scale(BufferedImage orignial,int breite, int hoehe){

        BufferedImage scale = new BufferedImage(breite,hoehe,orignial.getType());
        Graphics2D g2 = scale.createGraphics();
        g2.drawImage(orignial,0,0,breite,hoehe,null);

        return scale;

    }
}
