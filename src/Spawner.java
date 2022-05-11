import java.awt.*;

public class Spawner {
    private int GelbAnzahl, RotAnzahl, BlauAnzahl, waveCount;
    private boolean zielWelle, mapEnde;
    UI_gamepanel gp;

    public Spawner(UI_gamepanel gp) {
        this.gp = gp;
        zielWelle = false;
        mapEnde = false;


    }

    public void setBlauAnzahl(int blauAnzahl) {
        BlauAnzahl = blauAnzahl;
    }

    public int getBlauAnzahl() {
        return BlauAnzahl;
    }

    public void setGelbAnzahl(int gelbAnzahl) {
        GelbAnzahl = gelbAnzahl;
    }

    public int getGelbAnzahl() {
        return GelbAnzahl;
    }

    public void setRotAnzahl(int rotAnzahl) {
        RotAnzahl = rotAnzahl;
    }

    public int getRotAnzahl() {
        return RotAnzahl;
    }

    public void start(Graphics2D g2) {
        setGelbAnzahl(2);
        while (!zielWelle) {

            for (int i = 0; i < getGelbAnzahl(); ) {
                Gegner gg = new Gegner(gp);
                spawn(g2,gg);

                while (!mapEnde) {

                    gg.move();
                    //gy.move();
                    //gr.move();

                    if (mapEnde) {
                        waveCount++;
                        setRotAnzahl(+1);
                        setBlauAnzahl(+3);
                        setGelbAnzahl(+5);
                    }
                }
                /*
                for (int j = 0; j < getGelbAnzahl(); ) {
                    //GegnerY gy = new GegnerY(gp);
                    for (int k = 0; k < getRotAnzahl(); ) {
                        //GegnerR gr = new GegnerR(gp);
                    }
*/
                }




            }
        }

        public void spawn(Graphics2D g2,Gegner g){
            g.draw(g2);
            g.move();
        }
    }

