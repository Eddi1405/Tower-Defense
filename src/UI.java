import java.awt.*;

public class UI {

    UI_gamepanel gp;
    Font arial_40;
    public UI(UI_gamepanel gp){

        this.gp = gp;

        arial_40 = new Font("Arial",Font.PLAIN,40);

    }

    public void draw(Graphics2D g2){

        g2.setFont(arial_40);
        g2.setColor(Color.BLACK);
        g2.fillRect(gp.width/10*8,0,gp.width/10*2,gp.height);
    }
}
