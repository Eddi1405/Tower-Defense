import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Textures {
//Grober Aufbau (Idee)

    //Bilder Hinterlegen für Variabeln
public BufferedImage Tower, Enemy;
private Graphics2D g2;


    public Textures(UI_gamepanel gp){
        Enemy = new BufferedImage(gp.screenWidth,gp.screenHeight,BufferedImage.TYPE_INT_RGB);
        Tower = new BufferedImage(gp.screenWidth,gp.screenHeight,BufferedImage.TYPE_INT_RGB);
        g2 = (Graphics2D) Enemy.getGraphics();

        getTextures();
    }

    private void getTextures(){



    }
}

