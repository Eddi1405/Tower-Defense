import java.awt.*;

public class Enemy {

    private double x, y;
    private Textures tex;

    private int Leben, Resistenz;
    String Typ;

    public Enemy(double x, double y) {
        //Position
        this.x = x;
        this.y = y;
    }

    //Geschwindigkeit
    void tick() {
        y -= 5;
    }

    public void render(Graphics g) {
        g.drawImage(tex.Enemy, (int) x, (int) y, null);
    }
}
