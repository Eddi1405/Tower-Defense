import java.awt.*;

public class SpawnSystem {
    UI_gamepanel gp;
    private int wave, waveCounter;
    private Gegner[] mobs = new Gegner[5];
    private int j = 0;

    public SpawnSystem(UI_gamepanel gp) {
        this.gp = gp;
    }

    public void start() {
        setMobs();
    }

    public void setMobs() {
        for (int i = 0; i < mobs.length; i++) {
            mobs[i] = new Gegner(gp);
        }
    }

    public int spawnTime = 500, spawnFrame = 0;

    public void spawn(Graphics2D g2) {
        if (spawnFrame >= spawnTime && j < mobs.length) {
            mobs[j].valid = true;
            spawnFrame = 0;
            j++;
        } else {
            spawnFrame++;
        }
        for (int i = 0; i < mobs.length; i++) {
            if (mobs[i].valid) {
                mobs[i].draw(g2);
                mobs[i].move();
            }
        }
    }


    public void spawner(Graphics2D g2) {
        setMobs();
        for (int i = 0; i < mobs.length; i++) {
            mobs[i].draw(g2);
            mobs[i].move();
        }
    }
}