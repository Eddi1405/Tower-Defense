import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Textures {
//Grober Aufbau (Idee)

    //Bilder Hinterlegen für Variabeln
    public BufferedImage Tower, EnemyYellow, EnemyRed, EnemyBlue;
    private Graphics2D g2;


    public Textures(UI_gamepanel gp) {
        //Enemy = new BufferedImage(gp.screenWidth,gp.screenHeight,BufferedImage.TYPE_INT_RGB);
        //Tower = new BufferedImage(gp.screenWidth,gp.screenHeight,BufferedImage.TYPE_INT_RGB);
        //g2 = (Graphics2D) Enemy.getGraphics();

        getTextures();
    }

    public void getTextures() {

        try {

            EnemyYellow = ImageIO.read(getClass().getResourceAsStream("/pictures_map/yellow.png"));

            EnemyRed = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/pictures_map/red.png")));

            EnemyBlue = ImageIO.read(getClass().getResourceAsStream("/pictures_map/blue.png"));


        } catch (IOException e) {
            e.printStackTrace();

        }
    }
}

