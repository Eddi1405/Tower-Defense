/*Tower1, die Ramme, ID: 0*/

import java.awt.*;

import static java.lang.Math.round;





public class Tower1 extends Tower {

    Point start[];
    public Tower1(UI_gamepanel gp) {
        super(gp);
        start = new Point[2];
    }

    public void Tower1(int kosten, int schaden)
    {
        this.kosten = 10;
        this.schaden = 5;
    }

    public void setpoint(){
        for(int i = 0;i <= start.length;i++){
        start[i] = new Point(gp.screen + (int) round(breite * 13), (int) round(gp.h_TileSize * 1.2) + 50);
    }}


}
