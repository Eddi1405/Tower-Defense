import java.awt.*;

public class SpawnSystem {

    UI_gamepanel gp;

    private int wave, waveCounter;

    private Gegner[] mobs = new Gegner[3];
    private int i;


    public SpawnSystem(UI_gamepanel gp) {
        this.gp = gp;

    }

    public void start() {
        i = 0;
        setMobs();
    }

    public int spawnTime = 1000, spawnFrame = 0;

    public void setMobs() {
        for (int i = 0; i < mobs.length; i++) {
            mobs[i] = new Gegner(gp);
            System.out.println("KOTS" + i);

            if (spawnFrame >= spawnTime) {

                spawnFrame = 0;
            } else {
                spawnFrame += 1;
            }
        }
    }

    public void spawn(Graphics2D g2) {
        for (int i = 0; i < mobs.length; i++) {
            if(mobs[i].valid){
                mobs[i].draw(g2);
                mobs[i].move();
            }


        }
    }



}