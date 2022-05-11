import java.awt.*;

public class SpawnSystem {

    UI_gamepanel gp;

    private int wave, waveCounter;

    private Gegner[] mobs = new Gegner[5];
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

    public int spawnTime = 1000, spawnFrame = 0;

    public void setMobs() {
        for (int i = 0; i < mobs.length; i++) {
            mobs[i] = new Gegner(gp);
            System.out.println("KOTS" + i);

        }
    }

    public void spawn(Graphics2D g2) {
        for (int i = 0; i < mobs.length; i++) {
            if (spawnFrame >= spawnTime && irgendeinevariable) {
                if(mobs[i].valid){
                    mobs[i].draw(g2);
                    mobs[i].move();
                    System.out.println("Valid"+i);
                    irgendeinevariable = false;
                }
                spawnFrame = 0;
            } else {
                spawnFrame += 1;
                if(mobs[i].valid){
                    mobs[i].draw(g2);
                    mobs[i].move();
                    System.out.println("Valid"+i);
                }
            }
        }
    }


    public void spawner(Graphics2D g2){
        //Gegner[] mobs = new Gegner[5];
        setMobs();
        for(int i=0; i< mobs.length; i++){
            //mobs[i] = new Gegner(gp);
            mobs[i].draw(g2);
            mobs[i].move();

        }
    }

}