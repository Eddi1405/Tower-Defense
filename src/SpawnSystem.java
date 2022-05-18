import java.awt.*;

public class SpawnSystem {
    //Instanzen
    UI_gamepanel gp;
    path ph;
    private Gegner[] mobs = new Gegner[1];

    //Variabeln
    private int wave, waveCounter;
    private int j;
    public int spawnTime = 50, spawnFrame = 0;

    //Konstruktor mit Übergabe des GamePanels
    public SpawnSystem(UI_gamepanel gp) {
        this.gp = gp;
        j = 0;
    }
    //Die Methode start() ruft setMobs auf welche die Gegner reinlädt
    public void start() {
        setMobs();
    }
    //Lädt Gegner bzw füllt ein Array mit Gegnern
    public void setMobs() {
        for (int i = 0; i < mobs.length; i++) {
            mobs[i] = new Gegner(gp);
        }
    }
// Die Methode spawn() Spawnt unter Berücksichtigung des SpawnTimers die geladenen Gegner und bewegt diese
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
}