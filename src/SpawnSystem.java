import java.awt.*;

public class SpawnSystem {

    UI_gamepanel gp;

    private int wave, waveCounter;

    private Gegner[] mobs = new Gegner[5];
    private int j=0;
    private int i;
    private boolean irgendeinevariable;


    public SpawnSystem(UI_gamepanel gp) {
        this.gp = gp;
        irgendeinevariable = true;

    }

    public void start() {
        i = 0;
        setMobs();
    }

    public int spawnTime = 500, spawnFrame = 0;

    public void setMobs() {
        for (int i = 0; i < mobs.length; i++) {
            mobs[i] = new Gegner(gp);
            System.out.println("KOTS" + i);
        }
    }

    public void spawn(Graphics2D g2) {
        if (spawnFrame >= spawnTime && j < mobs.length) {
                    mobs[j].valid = true;
                    spawnFrame = 0;
                    System.out.println("Spawn" + j);
                    j++;
        } else {
            spawnFrame++;
        }

        for (int i = 0; i < mobs.length; i++) {
            if(mobs[i].valid){
                mobs[i].draw(g2);
                mobs[i].move();
                //System.out.println(mobs[i].valid+" "+i);
            }
        }
    }



}